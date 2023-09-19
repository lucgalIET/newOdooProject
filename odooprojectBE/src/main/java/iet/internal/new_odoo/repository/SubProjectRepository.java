package iet.internal.new_odoo.repository;

import iet.internal.new_odoo.entity.SubProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubProjectRepository extends JpaRepository<SubProjectEntity, Long> {
}
