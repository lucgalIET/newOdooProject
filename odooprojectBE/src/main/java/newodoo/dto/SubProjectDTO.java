package newodoo.dto;

import lombok.*;
import newodoo.Country;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubProjectDTO {
    private Long id;
    private Country country;
    private String projectType;
    private String projectName;
    private Date presumedStartingMonth;
    private Date presumedEndMonth;
    private String linkDirBusinessOnedrive;
    private String linkDirOperativeOnedrive;
}