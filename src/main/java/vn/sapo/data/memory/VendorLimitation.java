package vn.sapo.data.memory;

import vn.sapo.domain.vendor.VendorDailyQuota;
import vn.sapo.domain.vendor.VendorDailyQuotaTransaction;

import java.util.Collection;
import java.util.Date;

public interface VendorLimitation {
    void addInventoryTransaction(VendorDailyQuotaTransaction transaction);
    Collection<VendorDailyQuota> getAllQuota();
    void changeDailyQuota(Date date, long vendorId, long variantId, long quota);
    void addDailyQuota(Date date, long vendorId, long variantId, long quota);
    void persist();
    void handleQueue() throws InterruptedException;
}
