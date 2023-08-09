package newodoo.dto;

import lombok.*;
import newodoo.entity.ProgramEntity;
import newodoo.entity.SubProjectEntity;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectDTO {
    private Long id;
    private String country;
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

    private ProgramEntity idProgram;

    private SubProjectEntity subProject;
}
