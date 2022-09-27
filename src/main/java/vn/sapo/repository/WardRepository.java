package vn.sapo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.sapo.domain.Ward;

import java.util.Collection;
import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Long> {
    List<Ward> findByIdIn(Collection<Long> ids);
}
