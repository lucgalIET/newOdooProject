package newodoo.service;

import newodoo.repository.ProgramRepository;
import newodoo.entity.ProgramEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ProgramService {
    @Autowired
    private ProgramRepository programRepository;

    //aggiungi un programma (C)
    //trova un programma (R)
    //aggiorna un programma (U)
    //cancella un programma (D)
    public ProgramEntity saveProgram(ProgramEntity program) {
        return programRepository.save(program);
    }

    public List<ProgramEntity> getAllPrograms() {
        return programRepository.findAll();
    }

    public ProgramEntity findById(Long id) {
        return programRepository.findById(id).orElseThrow();
    }

    public ProgramEntity updateProgram(Long id, ProgramEntity programEntityUpdate) {
        ProgramEntity programEntity = findById(id);
        BeanUtils.copyProperties(programEntityUpdate, programEntity, getNullPropertyNames(programEntityUpdate));
        return programRepository.save(programEntity);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public void deleteProgram(Long id) {
        ProgramEntity programEntity = programRepository.findById(id).orElseThrow();
        programEntity.setDeleted(true);
        programRepository.save(programEntity);
    }
}