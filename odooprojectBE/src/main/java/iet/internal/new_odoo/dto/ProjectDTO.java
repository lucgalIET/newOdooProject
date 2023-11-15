package iet.internal.new_odoo.dto;

import iet.internal.new_odoo.Country;
import iet.internal.new_odoo.ProjectType;
import lombok.*;

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
    private ProjectType projectType;
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

    private List<SubProjectDTO> subProjects;
}
