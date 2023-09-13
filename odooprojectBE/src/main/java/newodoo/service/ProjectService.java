package newodoo.service;

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
import java.util.List;
import java.util.stream.Stream;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectEntity saveProject(ProjectEntity projectEntity) {
        for (Country country : Country.values()){
            if (country.equals(projectEntity.getCountry()))
                return projectRepository.save(projectEntity);
        }
        throw new IllegalArgumentException("Countr not valid");
        //throw new NullPointerException("Country not valid");
    }

    public List<ProjectEntity> getAllProject() {
        return projectRepository.findAll();
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
