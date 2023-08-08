package newodoo.service;

import newodoo.repository.SubProjectRepository;
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
        return subProjectRepository.findById(id).orElseThrow();
    }

    public SubProjectEntity updateSubProject(Long id, SubProjectEntity subProjectEntityUpdate){
        SubProjectEntity subProjectEntity = findById(id);
        BeanUtils.copyProperties(subProjectEntityUpdate, subProjectEntity, getNullPropertyNames(subProjectEntityUpdate));
        return subProjectRepository.save(subProjectEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteProject(Long id){
        SubProjectEntity subProjectEntity = subProjectRepository.findById(id).orElseThrow();
        subProjectEntity.setDeleted(true);
        subProjectRepository.save(subProjectEntity);
    }
}
