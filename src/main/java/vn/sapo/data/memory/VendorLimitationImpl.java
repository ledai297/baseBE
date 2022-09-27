package vn.sapo.data.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.data.memory.dto.VendorQuotaKey;
import vn.sapo.data.memory.dto.VendorQuotaStorage;
import vn.sapo.domain.vendor.VendorCommodity;
import vn.sapo.domain.vendor.VendorDailyQuota;
import vn.sapo.domain.vendor.VendorDailyQuotaTransaction;
import vn.sapo.domain.vendor.VendorQuotaCurrentTransaction;
import vn.sapo.repository.vendor.VendorCommodityRepository;
import vn.sapo.repository.vendor.VendorDailyQuotaRepository;
import vn.sapo.repository.vendor.VendorDailyQuotaTransactionRepository;
import vn.sapo.repository.vendor.VendorQuotaCurrentTransactionRepository;
import vn.sapo.utils.DateUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@Component
public class VendorLimitationImpl implements VendorLimitation{
    @Value("${application.time-zone}")
    private String timeZoneId;
    @Autowired
    private VendorDailyQuotaRepository vendorDailyQuotaRepository;
    @Autowired
    private VendorCommodityRepository vendorCommodityRepository;
    @Autowired
    private VendorQuotaCurrentTransactionRepository quotaCurrentTransactionRepository;
    @Autowired
    private VendorDailyQuotaTransactionRepository quotaTransactionRepository;

    private VendorQuotaStorage storage;

    @PostConstruct
    public void postLoading(){
        this.storage = new VendorQuotaStorage(timeZoneId);

        loadAllCommodity();
        loadTodayQuota();
        loadQuotaLastProcessedTransaction();
        loadUnprocessedQuotaTransaction();
    }

    private void loadAllCommodity(){
        List<VendorCommodity> commodities = vendorCommodityRepository.findAll();
        this.storage.setVendorCommodities(commodities);
    }

    private void loadUnprocessedQuotaTransaction() {
        Long todayMaxTransactionId = quotaTransactionRepository.getMaxIdByDateKey(this.storage.getCurrentDateKey());
        Long todayMaxProcessedTransactionId = this.storage.getQuotaCurrentTransaction().getTransactionId();

        if(todayMaxProcessedTransactionId < todayMaxTransactionId){
            List<VendorDailyQuotaTransaction> missingInventoryTransactions = quotaTransactionRepository
                .findByIdGreaterThanAndIdLessThanEqual(
                    todayMaxProcessedTransactionId,
                    todayMaxTransactionId
                );
            for(VendorDailyQuotaTransaction missingInventoryTransaction : missingInventoryTransactions)
                addInventoryTransaction(missingInventoryTransaction);
        }
    }

    private void loadQuotaLastProcessedTransaction() {
        int currentDateKey = this.storage.getCurrentDateKey();
         VendorQuotaCurrentTransaction quotaCurrentTransaction = quotaCurrentTransactionRepository
            .findById(currentDateKey)
            .orElse(null);

        if(quotaCurrentTransaction == null){
            quotaCurrentTransaction = new VendorQuotaCurrentTransaction(currentDateKey, 0L);
            quotaCurrentTransactionRepository.save(quotaCurrentTransaction);
        }
        this.storage.setQuotaCurrentTransaction(quotaCurrentTransaction);
    }

    private void loadTodayQuota() {
        int currentDateKey = this.storage.getCurrentDateKey();
        List<VendorDailyQuota> vendorDailyQuotas = vendorDailyQuotaRepository.findByDateKey(currentDateKey);
        for(VendorDailyQuota vendorDailyQuota : vendorDailyQuotas){
            VendorQuotaKey vendorQuotaKey = new VendorQuotaKey(
                vendorDailyQuota.getVendorId(),
                vendorDailyQuota.getVariantId()
            );
            this.storage.getVendorQuotas().put(vendorQuotaKey, vendorDailyQuota);
        }
    }

    public void addInventoryTransaction(VendorDailyQuotaTransaction transaction){
        this.storage.addTransaction(transaction);
    }
    public void handleQueue() throws InterruptedException {
        while(true){
            VendorDailyQuotaTransaction transaction = this.storage.getCurrentTransactionQueue().take();
            handleEventForVendorQuota(transaction);
        }
    }

    @Transactional
    public void persist(){
        List<VendorDailyQuota> changedEntities = this.storage.getVendorQuotas().values().stream()
            .filter(f -> f.isCreated() || f.isModified())
            .collect(Collectors.toList());
        if(changedEntities.size() == 0)
            return;

        vendorDailyQuotaRepository.saveAll(changedEntities);
        quotaCurrentTransactionRepository.save(this.storage.getQuotaCurrentTransaction());
        for(VendorDailyQuota changedEntity : changedEntities)
            changedEntity.resetChanges();
    }
    private void handleEventForVendorQuota(VendorDailyQuotaTransaction transaction){
        int currentDateKey = this.storage.getCurrentDateKey();
        ConcurrentHashMap<VendorQuotaKey, VendorDailyQuota> vendorQuotas = this.storage.getVendorQuotas();
        long vendorId = transaction.getVendorId();
        long variantId = transaction.getVariantId();

        VendorQuotaKey key = new VendorQuotaKey(vendorId, variantId);
        VendorDailyQuota quota = vendorQuotas.get(key);

        VendorCommodity commodity = this.storage.getVendorCommodities().stream()
            .filter(f -> f.getVariantId() == variantId && f.getVendorId() == vendorId)
            .findFirst()
            .orElse(null);

        if(commodity == null)
            throw new RuntimeException("commodity == null; transaction_id = " + transaction.getId());

        if(quota == null){
            quota = new VendorDailyQuota(
                currentDateKey,
                vendorId,
                variantId,
                commodity.getDailyQuota()
            );
            vendorQuotas.put(key, quota);
        }
        quota.increaseUsed(transaction.getIncrement());
        this.storage.getQuotaCurrentTransaction().setTransactionId(transaction.getId());
    }

    public void addDailyQuota(Date date, long vendorId, long variantId, long quota){
        int dateKey = DateUtils.getDateKey(date, timeZoneId);
        int currentDateKey = this.storage.getCurrentDateKey();
        if(dateKey != currentDateKey)
            return;

        VendorQuotaKey quotaKey = new VendorQuotaKey(vendorId, quota);
        VendorDailyQuota vendorQuota = new VendorDailyQuota(
            dateKey,
            vendorId,
            variantId,
            quota
        );
        this.storage.getVendorQuotas().put(quotaKey, vendorQuota);
    }

    public void changeDailyQuota(Date date, long vendorId, long variantId, long quota){
        int dateKey = DateUtils.getDateKey(date, timeZoneId);
        int currentDateKey = this.storage.getCurrentDateKey();
        VendorCommodity vendorCommodity = this.storage.getVendorCommodities().stream()
            .filter(f -> f.getVendorId() == vendorId && f.getVariantId() == variantId)
            .findFirst()
            .orElseThrow(() -> new RuntimeException(
                String.format(
                    "cannot find vendorCommodity in VendorLimitation memory: \n" +
                        "   dateKey: %d \n" +
                        "   vendorId: %d \n" +
                        "   variantId: %d \n" +
                        "   quota: %d",
                    dateKey, vendorId, variantId, quota
                )
            ));
        vendorCommodity.setDailyQuota(quota);

        VendorQuotaKey quotaKey = new VendorQuotaKey(vendorId, variantId);
        VendorDailyQuota vendorQuota = this.storage.getVendorQuotas().get(quotaKey);
        if(vendorQuota == null){
            vendorQuota = new VendorDailyQuota(
                currentDateKey,
                vendorId,
                variantId,
                quota
            );
            this.storage.getVendorQuotas().put(quotaKey, vendorQuota);
        }
        else{
            vendorQuota.changeQuota(quota);
        }
    }

    @Scheduled(fixedDelay = 5000)
    public void resetQuotaDaily() throws InterruptedException {
        int dateKey = DateUtils.getDateKey(new Date(), timeZoneId);
        if(dateKey == this.storage.getCurrentDateKey())
            return;

        while(this.storage.getCurrentTransactionQueue().size() > 0){
            Thread.sleep(1000);
        }

        this.storage.switchDate(dateKey);
        loadQuotaLastProcessedTransaction();
    }

    public Collection<VendorDailyQuota> getAllQuota(){
        return this.storage.getVendorQuotas().values();
    }
}
