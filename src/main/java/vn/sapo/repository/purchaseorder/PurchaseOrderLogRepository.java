package vn.sapo.repository.purchaseorder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.purchaseorder.PurchaseOrderLog;

import java.util.List;

@Repository
public interface PurchaseOrderLogRepository extends JpaRepository<PurchaseOrderLog, Long> {
    List<PurchaseOrderLog> findAllByPurchaseOrderIdOrderByIdAsc(Long purchaseOrderId);
}
