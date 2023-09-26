package iet.internal.new_odoo.repository;

import iet.internal.new_odoo.entity.SubTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTaskEntity, Long> {
}
