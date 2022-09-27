package vn.sapo.repository.vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.vendor.VendorDailyQuotaTransaction;

import java.util.Date;
import java.util.List;

@Repository
public interface VendorDailyQuotaTransactionRepository extends JpaRepository<VendorDailyQuotaTransaction, Long> {
    @Query("select coalesce(max(it.id), 0) from VendorDailyQuotaTransaction it where it.dateKey = :datekey")
    Long getMaxIdByDateKey(
        @Param("datekey") Integer dateKey
    );
    List<VendorDailyQuotaTransaction> findByIdGreaterThanAndIdLessThanEqual(long minId, long maxId);
}
