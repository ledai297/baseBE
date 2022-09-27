package vn.sapo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.IEDemand;

import java.util.List;

/**
 * Spring Data  repository for the ProductFile entity.
 */
@Repository
public interface IEDemandRepository extends JpaRepository<IEDemand, Long> {
    List<IEDemand> findAllByOrderByCreatedDateDesc();

    Page<IEDemand> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
