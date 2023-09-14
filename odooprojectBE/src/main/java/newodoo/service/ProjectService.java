package newodoo.service;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import newodoo.Country;
import newodoo.dto.ProjectDTO;
import newodoo.exceptions.ProjectNotFoundException;
import newodoo.repository.ProjectRepository;
import newodoo.entity.ProjectEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmailService emailService;

    public ProjectEntity saveProject(ProjectEntity projectEntity) {
        List<ProjectEntity> projectEntities = projectRepository.findAll();

        //CONTROLLO PER VEDERE SE GIA ESISTE NEL DB
        for (ProjectEntity project : projectEntities) {
            if (projectEntity.getId() == project.getId()) {
                return projectRepository.save(projectEntity);
            }
        }

        //SE NON ESISTE CONTROLLO PRIMA LA COUNTRY CORRETTA E POI INVIO L'EMAIL DAL COO AL PM
        for (Country country : Country.values()) {
            if (country.equals(projectEntity.getCountry())) {
                projectRepository.save(projectEntity);
                try {//IMPOSTARE EMAIL PM, ACCESSO ANAGRAFICA E IL TEMPLATE DEL LOGBOOK
                    String toPmMail = "http://localhost:8080/api/project/pm/" + projectEntity.getId();//ID DEL PROGETTO
                    String subject = "Oggetto";
                    emailService.sendFromCooToPm("giuseppesorbello98.ct@gmail.com", toPmMail, subject);
                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException a) {
                    a.printStackTrace();
                }
            }
        }
        return projectEntity;
        //throw new NullPointerException("Country not valid");
    }

    public List<ProjectEntity> getAllProject() {
        List<ProjectEntity> projectEntities = projectRepository.findAll();
        List<ProjectEntity> validProject = new ArrayList<>();
        for (ProjectEntity project : projectEntities) {
            try {
                if (isValid(project)) {
                    validProject.add(project);
                }
            } catch (IllegalArgumentException e) { //aggiungere varie exception
                e.getMessage();
            }
        }
        return validProject;
    }

    private boolean isValid(ProjectEntity project) { //controlli per la validità del progetto
        for (Country country : Country.values()) {
            if (country.equals(project.getCountry()))
                return true;
        }
        return false;
    }

    public ProjectEntity findById(Long id) {
        return projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
    }

    public ProjectEntity updateProject(Long id, ProjectDTO projectDTO) {
        ProjectEntity projectEntity = findById(id);
        projectDTO.setId(projectEntity.getId());

        BeanUtils.copyProperties(projectDTO, projectEntity, getNullPropertyNames(projectDTO));
        return projectRepository.save(projectEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteProject(Long id) {
        ProjectEntity projectEntity = projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
        projectEntity.setDeleted(true);
        projectRepository.save(projectEntity);
    }

}
