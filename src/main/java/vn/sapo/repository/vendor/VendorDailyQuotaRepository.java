package vn.sapo.repository.vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.vendor.VendorDailyQuota;

import java.util.List;

@Repository
public interface VendorDailyQuotaRepository extends JpaRepository<VendorDailyQuota, Long> {
    List<VendorDailyQuota> findByDateKey(int dateKey);
}
