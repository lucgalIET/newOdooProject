package iet.internal.new_odoo.service;

import iet.internal.new_odoo.Country;
import iet.internal.new_odoo.dto.ProjectDTO;
import iet.internal.new_odoo.entity.ProjectEntity;
import iet.internal.new_odoo.exceptions.ProjectNotFoundException;
import iet.internal.new_odoo.repository.ProjectRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmailService emailService;

    public ProjectEntity saveProject(ProjectEntity projectEntity) {
        List<ProjectEntity> projectEntities = projectRepository.findAll();

        Optional<ProjectEntity> projectEntityOptional = projectRepository.findById(projectEntity.getId());

        /**
         * MODIFICARE IF STATEMENT
         */

        if (projectEntityOptional.isPresent()) return projectRepository.save(projectEntity);
//        //CONTROLLO PER VEDERE SE GIA ESISTE NEL DB
//        for (ProjectEntity project : projectEntities) {
//            if (projectEntity.getId() == project.getId()) {
//                return projectRepository.save(projectEntity);
//            }
//        }

        //SE NON ESISTE CONTROLLO PRIMA LA COUNTRY CORRETTA E POI INVIO L'EMAIL DAL COO AL PM
        for (Country country : Country.values()) {
            if (country.equals(projectEntity.getCountry())) {
                projectRepository.save(projectEntity);
                try {//IMPOSTARE EMAIL PM, ACCESSO ANAGRAFICA E IL TEMPLATE DEL LOGBOOK
                    String toPmMail = "http://localhost:8080/api/project/" + projectEntity.getId();//ID DEL PROGETTO
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

    public ProjectEntity updateProject(Long id, ProjectDTO projectDTO, boolean isFirst) {

        ProjectEntity projectEntity = findById(id);
        projectDTO.setId(projectEntity.getId());
        BeanUtils.copyProperties(projectDTO, projectEntity, getNullPropertyNames(projectDTO));
        if(isFirst){
            try{//IMPOSTARE EMAIL PM, ACCESSO ANAGRAFICA E IL TEMPLATE DEL LOGBOOK
                String toPmMail = "http://localhost:8080/api/project/" + projectEntity.getId();//INVIO DEL TEMPLATE CAPIRE COME
                String subject = "Oggetto PM to TL";
                emailService.sendFromPmToTl("giuseppesorbello98.ct@gmail.com", toPmMail, subject);
            }catch (Exception e){e.printStackTrace();}
        }

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

/**

 EDIT BY 47

*/
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// ...

private static final Logger logger = LoggerFactory.getLogger(TuaClasse.class);

// ...

public ProjectEntity saveProject(ProjectEntity projectEntity) {
    try {
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
                String toPmMail = "http://localhost:8080/api/project/" + projectEntity.getId();//ID DEL PROGETTO
                String subject = "Oggetto";
                emailService.sendFromCooToPm("giuseppesorbello98.ct@gmail.com", toPmMail, subject);
            }
        }
        return projectEntity;
    } catch (MessagingException | IllegalArgumentException e) {
        logger.error("Errore durante l'invio dell'email o errore nei dati", e);
        // Puoi eventualmente gestire ulteriori azioni in base all'errore qui, se necessario.
        throw e; // Rilancia l'eccezione se necessario
    }
}

 */