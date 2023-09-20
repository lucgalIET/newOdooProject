package newodoo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class TimeSheetRow {
    private String order;
    private String fare;
    private LocalDate date;
    private double hours;
    private String customer;
}
