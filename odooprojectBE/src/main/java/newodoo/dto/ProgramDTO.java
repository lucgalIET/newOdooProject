package newodoo.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProgramDTO {
    private Long id;
    private String name;
    private String program_manager;
    private Long id_coo;
}