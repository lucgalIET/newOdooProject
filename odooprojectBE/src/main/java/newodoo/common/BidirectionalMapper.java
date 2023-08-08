package newodoo.common;

import org.springframework.stereotype.Component;

@Component
public interface BidirectionalMapper<D extends BaseDto, E extends BaseEntity> {

    D toDto(E entity);

    E toEntity(D dto);

    E toUpdateEntity(D dto, E entity);
}