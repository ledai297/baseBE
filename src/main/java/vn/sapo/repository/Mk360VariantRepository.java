package vn.sapo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.Mk360Variant;

import java.util.List;
import java.util.Optional;

@Repository
public interface Mk360VariantRepository extends JpaRepository<Mk360Variant, Long> {
    Optional<List<Mk360Variant>> findByMkVariantIdIn(List<Long> variantIds);
}
