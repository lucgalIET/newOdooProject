package newodoo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubProjectRepository extends JpaRepository<SubProjectRepository, Long> {
}
