package vn.sapo.repository.purchaseorder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.purchaseorder.PurchaseOrder;

import java.util.List;

@Repository
public interface PurchaseOrderIdProvider extends JpaRepository<PurchaseOrder, Long> {
    @Query(value = "SELECT nextval('purchase_order_id_seq')", nativeQuery = true)
    Long getNextPurchaseOrderId();

    @Query(value = "select nextval('purchase_order_line_item_id_seq') from generate_series(1, :number)", nativeQuery = true)
    List<Long> getNextPurchaseOrderLineItemIds(@Param("number") int number);
}
