package newodoo.Repository;

import newodoo.entity.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<ProgramEntity, Long> {
}
