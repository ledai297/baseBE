package vn.sapo.repository.vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.vendor.VendorQuotaCurrentTransaction;

@Repository
public interface VendorQuotaCurrentTransactionRepository extends JpaRepository<VendorQuotaCurrentTransaction, Integer> {
}
