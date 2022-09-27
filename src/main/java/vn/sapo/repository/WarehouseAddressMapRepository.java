package vn.sapo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.sapo.domain.WarehouseAddressMap;

import java.util.List;
import java.util.Optional;

public interface WarehouseAddressMapRepository extends JpaRepository<WarehouseAddressMap, Long> {
    List<WarehouseAddressMap> findByDistrictIdIn(List<Long> districtIds);
}
