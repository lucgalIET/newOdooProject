package newodoo.Repository;

import newodoo.entity.SubProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubProjectRepository extends JpaRepository<SubProjectEntity, Long> {
}
