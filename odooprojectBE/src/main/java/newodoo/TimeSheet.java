package newodoo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class TimeSheet {
    public TimeSheet(String name, String surname, int month, int year){
        this.name = name;
        this.surname = surname;
        this.month = month;
        this.year = year;
        this.timeSheetRowList = new ArrayList<TimeSheetRow>();
    }
    private String name;
    private String surname;
    private int year;
    private int month;
    private List<TimeSheetRow> timeSheetRowList;


}
