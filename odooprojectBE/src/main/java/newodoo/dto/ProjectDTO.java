package newodoo.dto;

import lombok.*;
import newodoo.Country;
import newodoo.entity.ProgramEntity;
import newodoo.entity.SubProjectEntity;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectDTO {
    private Long id;
    //private String country;
    private Country country;
    private String projectType;
    private String projectName;
    private LocalDate presumedStartingMonth;
    private LocalDate presumedEndMonth;
    private Long idAllocatedResources;
    private String linkDirBusinessOnedrive;
    private String linkDirOperativeOnedrive;
    private String linkTrello;
    private String linkGitlab;
    private String linkLogbook;

    //private ProgramEntity idProgram;

    private List<SubProjectEntity> subProjects;
}
