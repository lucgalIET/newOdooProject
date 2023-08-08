package newodoo.service;

import newodoo.Repository.SubProjectRepository;
import newodoo.entity.ProjectEntity;
import newodoo.entity.SubProjectEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

public class SubProjectService {
    @Autowired
    private SubProjectRepository subProjectRepository;

    public SubProjectEntity saveSubProject(SubProjectEntity subprojectEntity){return subProjectRepository.save(subprojectEntity);}

    public List<SubProjectEntity> getAllSubProject(){return subProjectRepository.findAll();}

    public SubProjectEntity findById(Long id){
        return projectRepository.findById(id).orElseThrow();
    }

    public ProjectEntity updateProject(Long id, ProjectEntity projectEntityUpdate){
        ProjectEntity projectEntity = findById(id);
        BeanUtils.copyProperties(projectEntityUpdate, projectEntity, getNullPropertyNames(projectEntityUpdate));
        return projectRepository.save(projectEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteProject(Long id){
        ProjectEntity projectEntity = projectRepository.findById(id).orElseThrow();
        projectEntity.setDeleted(true);
        projectRepository.save(projectEntity);
    }
}
