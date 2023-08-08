package newodoo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Slf4j
public abstract class ServiceTemplate<
        E extends BaseEntity,
        D extends BaseDto,
        C extends BaseCriteria,
        S extends SpecificationBuilder<E, C>,
        M extends BidirectionalMapper<D, E>,
        R extends BaseJpaRepository<E>> {

    protected final R repository;
    protected final M mapper;
    protected final S specificationBuilder;


    protected ServiceTemplate(
            @NonNull R repository,
            @NonNull M mapper,
            @NonNull S specificationBuilder) {
        this.repository = repository;
        this.mapper = mapper;
        this.specificationBuilder = specificationBuilder;
    }

    public R getRepository() {
        return repository;
    }

    public M getMapper() {
        return mapper;
    }

    public Page<? extends BaseDto> filter(@Nullable C criteria) {
        return getEntities(criteria).map(mapper::toDto);
    }

    protected Page<E> getEntities(@Nullable C criteria) {
        Pageable pageable;
        if (criteria != null) {
            pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize(),
                    Sort.Direction.valueOf(criteria.getSortDirection()), criteria.getOrderBy());
        } else {
            pageable = PageRequest.of(0, 20, Sort.Direction.ASC, "id");
        }

        return (criteria != null)
                ? repository.findAll(specificationBuilder.filter(criteria), pageable)
                : repository.findAll(pageable);

    }

    protected List<E> getExportList(@Nullable C criteria) {
        if (criteria != null) {
            criteria.setOrderBy(null);
            criteria.setSortDirection(null);
        }

        return (criteria != null)
                ? repository.findAll(specificationBuilder.filter(criteria))
                : repository.findAll();

    }

    protected List<E> getListByCriteria(@Nullable C criteria) {
        return repository.findAll(specificationBuilder.filter(criteria));
    }

    protected abstract boolean eligibleToDelete(Long id);

    public abstract String getEntityName();

}
