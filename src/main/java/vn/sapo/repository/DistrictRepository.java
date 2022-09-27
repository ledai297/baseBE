package vn.sapo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.District;

import java.util.Collection;
import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findByProvinceIdIn(Collection<Long> provinceIds);

    List<District> findByIdIn(Collection<Long> ids);
}
