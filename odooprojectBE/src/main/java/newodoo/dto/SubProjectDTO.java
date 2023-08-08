package newodoo.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubProjectDTO {
    private Long id;
    private String country;
    private String project_type;
    private String project_name;
    private Date presumed_starting_month;
    private Date presumed_end_month;
    private String link_dir_business_onedrive;
    private String link_dir_operative_onedrive;
}