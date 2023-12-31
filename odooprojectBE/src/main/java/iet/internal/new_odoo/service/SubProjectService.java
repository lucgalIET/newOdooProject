package iet.internal.new_odoo.service;

import iet.internal.new_odoo.Country;
import iet.internal.new_odoo.entity.SubProjectEntity;
import iet.internal.new_odoo.exceptions.SubProjectNotFoundException;
import iet.internal.new_odoo.repository.SubProjectRepository;
import iet.internal.new_odoo.dto.SubProjectDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SubProjectService {

    @Autowired
    private SubProjectRepository subProjectRepository;

    public SubProjectEntity saveSubProject(SubProjectEntity subprojectEntity) {
        for (Country country : Country.values()) {
            if (country.equals(subprojectEntity.getCountry()))
                return subProjectRepository.save(subprojectEntity);
        }
        throw new NullPointerException("Country not valid");
    }

    public List<SubProjectEntity> getAllSubProjects() {
        return subProjectRepository.findAll();
    }

    public SubProjectEntity findById(Long id) {
        return subProjectRepository.findById(id).orElseThrow(SubProjectNotFoundException::new);
    }

    public SubProjectEntity updateSubProject(Long id, SubProjectDTO subProjectDTO) {
        SubProjectEntity subProjectEntity = findById(id);
        subProjectDTO.setId(subProjectEntity.getId());

        BeanUtils.copyProperties(subProjectDTO, subProjectEntity, getNullPropertyNames(subProjectDTO));
        return subProjectRepository.save(subProjectEntity);

    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteSubProject(Long id) {
        SubProjectEntity subProjectEntity = subProjectRepository.findById(id).orElseThrow(SubProjectNotFoundException::new);
        subProjectEntity.setDeleted(true);
        subProjectRepository.save(subProjectEntity);
    }
}
