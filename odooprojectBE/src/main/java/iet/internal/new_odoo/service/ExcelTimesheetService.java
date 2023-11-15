package iet.internal.new_odoo.service;


import iet.internal.new_odoo.TimeSheet;
import iet.internal.new_odoo.TimeSheetRow;
import iet.internal.new_odoo.exceptions.ExcelFileProblemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileInputStream;
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
    @Value("${excel.files.upload.dir}")
    private String uploadDir;

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
     * Returns timesheet by reading the Excel file uploaded by the user
     * @param filename the name of the file uploaded by the user
     * @return The TimeSheet if the Excel file is valid and correctly filled, else null
     */
    public TimeSheet extractTimeSheetFromFile(String filename) throws ExcelFileProblemException {
        String fileNameOnly = noExtension(filename);
        filename = Paths.get(uploadDir, filename).toString();
        try {
            Map<Integer, List<String>> fileMap = readExcel(filename);
            //INIZIO CODICE DI STAMPA
            System.out.println("fileMap contiene queste cose");
            int fileMapIndex = 0;
            while(fileMapIndex < 20){
                if(fileMap.get(fileMapIndex) != null) {
                    List<String> strings = fileMap.get(fileMapIndex);
                    System.out.print("[");
                    for (int i = 0; i < strings.size(); i++) {
                        System.out.print(strings.get(i));
                        if (i < strings.size() - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println("]");
                }else{
                    System.out.println("-------------------------");
                }
                fileMapIndex++;
            }
            //FINE CODICE DI STAMPA

            //la chiave di questa mappa è il numero della riga
            String nameSurname1 = fileMap.get(2).get(1).toLowerCase();
            String[] dateStrings = fileMap.get(2).get(3).split("/");

            int year1;
            try{
                //System.out.println("dateStrings[1]: " + dateStrings[1] + " ; tokens[4]: " + tokens[4]);
                year1 = Integer.parseInt(dateStrings[1]);
            }catch(NumberFormatException e){
                throw new ExcelFileProblemException("Can't parse the written year"); //FUNZIONA
            }
            int month1;
            try{
                month1 = Integer.parseInt(dateStrings[0]);
            }catch(NumberFormatException e){
                throw new ExcelFileProblemException("Can't parse the written month"); //FUNZIONA
            }
            //month and year of timesheet are valid! you can create the Timesheet object!!

            //control that name, surname, month and year match!
            if(!fileNameAndContentMatch(fileNameOnly, nameSurname1, month1, year1)){
                throw new ExcelFileProblemException("Name, surname, year and month don't match between the file name and the file content"); //FUNZIONA
            }
            String[] nameAndSurnameArray = extractNameAndSurname(fileNameOnly, nameSurname1);
            TimeSheet ts = new TimeSheet(nameAndSurnameArray[0],nameAndSurnameArray[1],month1,year1);
            double hoursTotal = 0.0;
            int daysTotal = 0;
            //timesheet created. now fill it!
            int row = 4;
            while(true){
                if(fileMap.get(row) == null || fileMap.get(row).size() == 0){
                    row++;
                    continue;
                }
                try {
                    if(noMoreRows(fileMap.get(row))){
                        break;
                    }
                    String order = fileMap.get(row).get(0);
                    System.out.println(fileMap.get(row).get(1).replace(',', '.'));
                    double fare = Double.parseDouble(noEuroSymbol(fileMap.get(row).get(1).replace(',', '.')));
                    LocalDate date = parseDate(fileMap.get(row).get(2));
                    double hours = Double.parseDouble(fileMap.get(row).get(3).replace(',', '.'));
                    if(hours <= 0){
                        throw new ExcelFileProblemException("One or more rows have a zero or negative hours number"); //FUNZIONA
                    }
                    if(fare <= 0){
                        throw new ExcelFileProblemException("One or more rows have a zero or negative fare"); //FUNZIONA
                    }
                    String customer = fileMap.get(row).get(4);
                    if(order.equals("") || customer.equals("")){
                        throw new ExcelFileProblemException("One or more rows are missing some information!"); //FUNZIONA
                    }
                    if(date == null){
                        throw new ExcelFileProblemException("One or more rows have a non-valid date"); //FUNZIONA
                    }
                    //tutti i dati sono validi. Inserisci la timesheetrow
                    TimeSheetRow tsRow = new TimeSheetRow(order,fare,date,hours,customer);
                    if(!(ts.dayAlreadyInserted(date))){
                        daysTotal++;
                    }
                    ts.getTimeSheetRowList().add(tsRow);

                    hoursTotal = hoursTotal + hours;
                }catch(NumberFormatException e){
                    throw new ExcelFileProblemException("There is a row where hours or fare can't be read!"); //FUNZIONA
                }
                row++;
            }
            System.out.println("FINO A QUI CI SIAMO ARRIVATI");
            //qui controlla che le ore totali siano quelle giuste e anche i giorni totali
            int daysTotalField;
            double hoursTotalField;
            // cerca il campo dei giorni totali e quello delle ore totali nel file excel
            int rowIndex = 0;
            boolean found = false;
            while(!found){
                if(fileMap.get(rowIndex) ==  null  || fileMap.get(rowIndex).size() == 0){
                    rowIndex++;
                    continue;
                }
                if(fileMap.get(rowIndex).get(0).equals("TOTALI MESE CORRENTE") && fileMap.get(rowIndex).get(1).equals("TOTALE ORE") && fileMap.get(rowIndex).get(2).equals("TOTALE GIORNI") && fileMap.get(rowIndex+1).get(0).equals("LAVORO")){ // è qui che si blocca tutto
                    found = true;
                    try{
                        hoursTotalField = Double.parseDouble(fileMap.get(rowIndex+1).get(1).replace(',', '.'));
                        daysTotalField = (int)(Double.parseDouble(fileMap.get(rowIndex+1).get(2).replace(',', '.')));
                        if(hoursTotalField != hoursTotal){
                            throw new ExcelFileProblemException("You have inserted "+ hoursTotalField + " hours in the total hours field, but the sum of the hours you inserted is " + hoursTotal); //FUNZIONA
                        }
                        if(daysTotalField != daysTotal){
                            throw new ExcelFileProblemException("You have inserted "+ daysTotalField + " days in the total days field, but the sum of the days you inserted is " + daysTotal); //FUNZIONA
                        }
                    }catch(NumberFormatException e){
                        throw new ExcelFileProblemException("You have to fill correctly both the total days field and the total hours field!"); //FUNZIONA
                    }
                }
                rowIndex++;
            }

            return ts;
        } catch (IOException e) {
            throw new ExcelFileProblemException("Cannot read excel file. An error occurred (IOException)");
        }catch(IndexOutOfBoundsException | NullPointerException e){
            throw new ExcelFileProblemException("The file hasn't been compiled properly. Please check it"); //FUNZIONA
        }catch(OfficeXmlFileException e){
            throw new ExcelFileProblemException("This is not a .xls file (Excel 97 - Excel 2003)");
        }
    }

    private String noEuroSymbol(String str) {
        return str.replace("\"€\"","");
    }

    /**
     * Function called only when fileNameAndContentMatch returns true!
     */
    public String[] extractNameAndSurname(String fileNameOnly, String nameSurname) {
        int spaceDivisorPosition;
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
                //move the two indexes forwards
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
        nameAndSurnameArray[1] = nameSurname.substring(spaceDivisorPosition+1);
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
            StringBuilder nameSurname2 = new StringBuilder();
            for(int i = 1;i < tokens.length-2;i++){
                nameSurname2.append(tokens[i]);
            }
            return nameSurname1.equals(nameSurname2.toString().toLowerCase());
        }catch(NumberFormatException e){
            return false;
        }
    }

    private String noSpaces(String nameSurname1) {
        StringBuilder toReturn = new StringBuilder();
        for(int i = 0;i < nameSurname1.length();i++){
            if(nameSurname1.charAt(i) != ' '){
                toReturn.append(nameSurname1.charAt(i));
            }
        }
        return toReturn.toString();
    }

    /**
     * Removes file extension from the string, if present
     * @return The file name without extension
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
     *
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
        return strings.get(0).equals("TOTALI MESE CORRENTE") && strings.get(1).equals("TOTALE ORE") && strings.get(2).equals("TOTALE GIORNI");
    }

    /**
     *
     *
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
        }catch(NumberFormatException | DateTimeException e){
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

    public static Map<Integer, List<String>> readExcel(String excelFilePath) throws IOException, OfficeXmlFileException{ //al posto di string c'era object
        Map<Integer, List<String>> cellData = new HashMap<>(); //al posto di string c'era object
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
            Workbook workbook = new HSSFWorkbook(fileInputStream);

            int numberOfSheets = workbook.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);

                for (Row row : sheet) {
                    List<String> rowData = new ArrayList<>();

                    for (Cell cell : row) {
                        String cellValue;
                        if (cell.getCellType().equals(CellType.NUMERIC) && DateUtil.isCellDateFormatted(cell)) {
                            Date cellDate = cell.getDateCellValue();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            cellValue = dateFormat.format(cellDate);
                        } else {
                            cellValue = cell.toString();
                        }
                        rowData.add(cellValue);
                    }

                    cellData.put(row.getRowNum(), rowData);
                }
            }

            fileInputStream.close();


        return cellData;
    }


}
