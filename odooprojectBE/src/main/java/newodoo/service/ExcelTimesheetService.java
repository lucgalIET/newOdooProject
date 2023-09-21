package newodoo.service;

import jxl.*;
import jxl.read.biff.BiffException;
import newodoo.TimeSheet;
import newodoo.TimeSheetRow;
import newodoo.exceptions.ExcelFileProblemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
@Service
public class ExcelTimesheetService {
    @Value("${excelfiles.upload.dir}") // Configura questa property nel tuo application.properties o application.yml
    private String uploadDir; // Percorso della cartella di caricamento delle immagini

    public boolean uploadExcelFileToServer(MultipartFile file) {
        try {
            String filePath = Paths.get(uploadDir, file.getOriginalFilename()).toString();
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Restituisce il timesheet leggendo il file excel caricato dall'utente
     * @param filename Il nome del file che l'utente ha caricato
     * @return Il timesheet se il file è valido e correttamente compilato, NULL altrimenti
     */
    public TimeSheet extractTimeSheetFromFile(String filename) throws ExcelFileProblemException {
        String fileNameOnly = noExtension(filename);
        filename = Paths.get(uploadDir, filename).toString();
        try {
            HashMap<Integer, List<String>> fileMap = (HashMap) readJExcel(filename);
            //la chiave di questa mappa è il numero della riga
            String nameSurname1 = fileMap.get(2).get(1).toLowerCase();
            String dateStrings[] = fileMap.get(2).get(3).split("/");
            int year1 = 0;
            try{
                //System.out.println("dateStrings[1]: " + dateStrings[1] + " ; tokens[4]: " + tokens[4]);
                year1 = Integer.parseInt(dateStrings[1]);
            }catch(NumberFormatException e){
                throw new ExcelFileProblemException("Can't parse the written year");
            }
            int month1 = 0;
            try{
                month1 = Integer.parseInt(dateStrings[0]);
            }catch(NumberFormatException e){
                throw new ExcelFileProblemException("Can't parse the written month");
            }
            //mese e anno del timesheet sono validi! puoi creare l'oggetto Timesheet!!


            //qui controlla che tra name, surname, month e year ci sia il match!
            if(!fileNameAndContentMatch(fileNameOnly, nameSurname1, month1, year1)){
                throw new ExcelFileProblemException("Name, surname, year and month don't match between the file name and the file content");
            }
            String[] nameAndSurnameArray = extractNameAndSurname(fileNameOnly, nameSurname1);
            TimeSheet ts = new TimeSheet(nameAndSurnameArray[0],nameAndSurnameArray[1],month1,year1);
            double hoursTotal = 0.0;
            int daysTotal = 0;
            //timesheet creato. è ora di riempirlo!
            boolean finished = false;
            int row = 4;
            while(!finished){
                try {
                    if(noMoreRows(fileMap.get(row))){
                        finished = true;
                        break;
                    }
                    String order = fileMap.get(row).get(0);
                    String fare = fileMap.get(row).get(1);
                    LocalDate date = parseDate(fileMap.get(row).get(2));
                    double hours = Double.parseDouble(fileMap.get(row).get(3).replace(',', '.'));
                    if(hours <= 0){
                        throw new ExcelFileProblemException("One or more rows have a zero or negative hours number");
                    }
                    String customer = fileMap.get(row).get(4);
                    if(order.equals("") || fare.equals("") || customer.equals("")){
                        throw new ExcelFileProblemException("One or more rows are missing some information!");
                    }
                    if(date == null){
                        throw new ExcelFileProblemException("One or more rows have a non-valid date");
                    }
                    //tutti i dati sono validi. Inserisci la timesheetrow
                    TimeSheetRow tsRow = new TimeSheetRow(order,fare,date,hours,customer);
                    if(!(ts.dayAlreadyInserted(date))){
                        daysTotal++;
                    }
                    ts.getTimeSheetRowList().add(tsRow);

                    hoursTotal = hoursTotal + hours;
                }catch(NumberFormatException e){
                    throw new ExcelFileProblemException("There is a row where hours can't be read!");
                }
                row++;
            }
            //qui controlla che le ore totali siano quelle giuste e anche i giorni totali
            int daysTotalField = 0;
            double hoursTotalField = 0;
            // cerca il campo dei giorni totali e quello delle ore totali nel file excel
            int rowIndex = 0;
            boolean found = false;
            while(!found){
                if(fileMap.get(rowIndex).get(0).equals("TOTALI MESE CORRENTE") && fileMap.get(rowIndex).get(1).equals("TOTALE ORE") && fileMap.get(rowIndex).get(2).equals("TOTALE GIORNI") && fileMap.get(rowIndex+1).get(0).equals("LAVORO")){
                    found = true;
                    try{
                        hoursTotalField = Double.parseDouble(fileMap.get(rowIndex+1).get(1).replace(',', '.'));
                        daysTotalField = (int)(Double.parseDouble(fileMap.get(rowIndex+1).get(2).replace(',', '.')));
                        if(hoursTotalField != hoursTotal){
                            throw new ExcelFileProblemException("You have inserted "+ hoursTotalField + " hours in the total hours field, but the sum of the hours you inserted is " + hoursTotal);
                        }
                        if(daysTotalField != daysTotal){
                            throw new ExcelFileProblemException("You have inserted "+ daysTotalField + " days in the total days field, but the sum of the days you inserted is " + daysTotal);
                        }
                    }catch(NumberFormatException e){
                        throw new ExcelFileProblemException("You have to fill correctly both the total days field and the total hours field!");
                    }
                }
                rowIndex++;
            }

            return ts;
        } catch (IOException e) {
            throw new ExcelFileProblemException("Cannot read excel file. An error occurred (IOException)");
        } catch (BiffException e) {
            throw new ExcelFileProblemException("Cannot read excel file. An error occurred (BiffException)");
        }catch(IndexOutOfBoundsException e){
            throw new ExcelFileProblemException("The file hasn't been compiled properly. Please check it");
        }
    }

    /**
     * Funzione chiamata solo quando fileNameAndContentMatch restituisce true!
     */
    public String[] extractNameAndSurname(String fileNameOnly, String nameSurname) {
        int spaceDivisorPosition = 0;
        String[] tokens = fileNameOnly.split("_");
        String names = tokens[1];
        String surnames = tokens[2];
        int fileNameIndex = 3;
        int nameSurnameIndex = 0;
        while(nameSurname.charAt(nameSurnameIndex) == ' '){
            nameSurnameIndex++;
        }
        boolean found = false;
        while(!found){
            if(fileNameOnly.charAt(fileNameIndex+1) == '_'){
                nameSurnameIndex++;
                found = true;
            }else {
                //manda avanti i due cursori
                fileNameIndex++;
                nameSurnameIndex++;
                while (nameSurname.charAt(nameSurnameIndex) == ' ') {
                    nameSurnameIndex++;
                }
            }
        }
        spaceDivisorPosition = nameSurnameIndex;
        String[] nameAndSurnameArray = new String[2];
        nameAndSurnameArray[0] = nameSurname.substring(0,spaceDivisorPosition);
        nameAndSurnameArray[1] = nameSurname.substring(spaceDivisorPosition+1,nameSurname.length());
        return nameAndSurnameArray;
    }

    private boolean fileNameAndContentMatch(String fileNameOnly, String nameSurname1, int month, int year) {
        try {
            String[] tokens = fileNameOnly.split("_");
            if(!(toMonthNumber(tokens[tokens.length-2]) == month)){
                return false;
            }
            if(!(Integer.parseInt(tokens[tokens.length-1]) == year)){
                return false;
            }
            nameSurname1 = noSpaces(nameSurname1);
            nameSurname1 = nameSurname1.toLowerCase();
            String nameSurname2 = "";
            for(int i = 1;i < tokens.length-2;i++){
                nameSurname2 = nameSurname2 + tokens[i];
            }
            nameSurname2 = nameSurname2.toLowerCase();
            if(!(nameSurname1.equals(nameSurname2))){
                return false;
            }
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    private String noSpaces(String nameSurname1) {
        String toReturn = "";
        for(int i = 0;i < nameSurname1.length();i++){
            if(nameSurname1.charAt(i) != ' '){
                toReturn += nameSurname1.charAt(i);
            }
        }
        return toReturn;
    }

    /**
     * Rimuove l'estensione dal nome del file, se presente
     * @param filename
     * @return Il nome del file senza estensione
     */
    private String noExtension(String filename) {
        int i = filename.length()-1;
        while(i >= 0){
            if(filename.charAt(i) == '.'){
                return filename.substring(0,i);
            }
            i--;
        }
        return filename;
    }

    /**
     *
     * @param strings
     * @return TRUE se questa riga non è più una riga della tabella del timesheet
     */
    private boolean noMoreRows(List<String> strings) {
        boolean empty = true;
        for(int i = 0;i < 5;i++){
            if(!strings.get(i).equals("")){
                empty = false;
                break;
            }
        }
        if(empty){
            return true;
        }
        if(strings.get(0).equals("TOTALI MESE CORRENTE") && strings.get(1).equals("TOTALE ORE") && strings.get(2).equals("TOTALE GIORNI")){
            return true;
        }
        return false;
    }

    /**
     *
     * @param dateString
     * @return NULL se la data non è valida oppure è futura
     */
    private LocalDate parseDate(String dateString) {
        System.out.println("La data che passo a parseDate è " + dateString);
        String[] dateTokens = dateString.split("/");
        try{
            Month month = Month.of(Integer.parseInt(dateTokens[1]));
            LocalDate date = LocalDate.of(Integer.parseInt(dateTokens[2]), month, Integer.parseInt(dateTokens[0]));
            if(date.isAfter(LocalDate.now())){
                return null;
            }
            return date;
        }catch(NumberFormatException e){
            return null;
        }catch(DateTimeException e){
            return null;
        }
    }

    private int toMonthNumber(String token) {
        token = token.toLowerCase();
        String[] mesi = {
                "gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno",
                "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre"
        };

        // Converto il nome del mese in minuscolo per evitare problemi di maiuscole/minuscole
        for (int i = 0; i < mesi.length; i++) {
            if (mesi[i].equals(token)) {
                // Aggiungo 1 al risultato perché i mesi sono numerati da 1 a 12
                return i + 1;
            }
        }
        // Restituisco -1 se il nome del mese non è valido
        return -1;
    }

    private Map<Integer, List<String>> readJExcel(String fileLocation)
            throws IOException, BiffException {

        Map<Integer, List<String>> data = new HashMap<>();

        Workbook workbook = Workbook.getWorkbook(new File(fileLocation));
        Sheet sheet = workbook.getSheet(0);
        int rows = sheet.getRows();
        int columns = sheet.getColumns();

        for (int i = 0; i < rows; i++) {
            data.put(i, new ArrayList<String>());
            for (int j = 0; j < columns; j++) {
                if(sheet.getCell(j,i).getType().equals(CellType.DATE)){
                    Cell cell = sheet.getCell(j,i);
                    Date date = ((DateCell) cell).getDate();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String formattedDate = dateFormat.format(date);
                    data.get(i).add(formattedDate);
                }else{
                    data.get(i).add(sheet.getCell(j, i).getContents());
                }

            }
        }
        return data;
    }


}
