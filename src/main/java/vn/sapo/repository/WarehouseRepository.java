package vn.sapo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.sapo.domain.Warehouse;

import vn.sapo.statics.WarehouseStatus;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository  extends JpaRepository<Warehouse, Long> {
    @Query(value = "SELECT nextval('warehouse_id_seq')", nativeQuery = true)
    Long getNextId();

    List<Warehouse> findByIdIn(List<Long> ids);
    Optional<Warehouse> findByIdAndStatus(
        Long id,
        WarehouseStatus status
    );

    Optional<Warehouse> findByStatus(WarehouseStatus status);

    @Query(value = "select * from warehouse_filter(:ids, :deliveryAddressDistrictId, :keyword, :status)", nativeQuery = true)
    Page<Warehouse> filter(
        @Param("ids") String ids,
        @Param("deliveryAddressDistrictId") Long deliveryAddressDistrictId,
        @Param("keyword") String keyword,
        @Param("status") String status,
        Pageable pageable
    );
}
