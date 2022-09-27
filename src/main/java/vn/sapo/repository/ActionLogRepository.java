package vn.sapo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.ActionLog;

@Repository
public interface ActionLogRepository extends JpaRepository<ActionLog, Long> {

}
