package vn.sapo.repository.purchaseorder;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.purchaseorder.PurchaseOrder;
import vn.sapo.projection.purchaseOrder.PurchaseOrderDto;
import vn.sapo.statics.purchaseorder.PurchaseOrderStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Query(value = "SELECT * FROM purchase_order_filter_count(:ids, :userIds, :vendorIds, :warehouseIds, :keyword, " +
        ":createdDateMin, :createdDateMax, :purchaseDateMin, :purchaseDateMax, :statuses )", nativeQuery = true)
    Integer countPurchaseOrders(
        @Param("ids") String ids,
        @Param("userIds") String userIds,
        @Param("vendorIds") String vendorIds,
        @Param("warehouseIds") String warehouseIds,
        @Param("keyword") String keyword,
        @Param("createdDateMin") String createdDateMin,
        @Param("createdDateMax") String createdDateMax,
        @Param("purchaseDateMin") String purchaseDateMin,
        @Param("purchaseDateMax") String purchaseDateMax,
        @Param("statuses") String statuses
    );

    @Query(value = "SELECT * FROM purchase_order_filter(:ids, :userIds, :vendorIds, :warehouseIds, :keyword, " +
        ":createdDateMin, :createdDateMax, :purchaseDateMin, :purchaseDateMax, :statuses )", nativeQuery = true)
    List<PurchaseOrder> filterPurchaseOrders(
        @Param("ids") String ids,
        @Param("userIds") String userIds,
        @Param("vendorIds") String vendorIds,
        @Param("warehouseIds") String warehouseIds,
        @Param("keyword") String keyword,
        @Param("createdDateMin") String createdDateMin,
        @Param("createdDateMax") String createdDateMax,
        @Param("purchaseDateMin") String purchaseDateMin,
        @Param("purchaseDateMax") String purchaseDateMax,
        @Param("statuses") String statuses,
        Pageable pageable
    );
    long countByVendorId(long vendorId);

    Optional<PurchaseOrder> findByIdAndDeletedFalse(long id);
    List<PurchaseOrder> findByIdInAndDeletedFalse(List<Long> ids);
}
