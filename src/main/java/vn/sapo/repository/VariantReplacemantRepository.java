package vn.sapo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.VariantReplacement;

import java.util.List;

@Repository
public interface VariantReplacemantRepository extends JpaRepository<VariantReplacement, Long> {
    List<VariantReplacement> findAllByReplacementIdIn(List<Long> replacementIds);
}
