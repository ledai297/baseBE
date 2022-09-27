package vn.sapo.data.memory.dto;

import vn.sapo.domain.vendor.VendorCommodity;
import vn.sapo.domain.vendor.VendorDailyQuota;
import vn.sapo.domain.vendor.VendorDailyQuotaTransaction;
import vn.sapo.domain.vendor.VendorQuotaCurrentTransaction;
import vn.sapo.utils.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class VendorQuotaStorage {
    private String timeZoneId;
    private int currentDateKey;
    private Map<Integer, LinkedBlockingQueue<VendorDailyQuotaTransaction>> dateTransactionQueueMap;
    private LinkedBlockingQueue<VendorDailyQuotaTransaction> currentTransactionQueue;
    private List<VendorCommodity> vendorCommodities;
    private ConcurrentHashMap<VendorQuotaKey, VendorDailyQuota> vendorQuotas;
    private VendorQuotaCurrentTransaction quotaCurrentTransaction;


    public VendorQuotaStorage(String timeZoneId){
        this.timeZoneId = timeZoneId;
        this.currentDateKey = DateUtils.getDateKey(new Date(), timeZoneId);
        this.dateTransactionQueueMap = new HashMap<>();
        this.vendorQuotas = new ConcurrentHashMap<>();
        this.currentTransactionQueue = new LinkedBlockingQueue<>();

        addNewDateQueue(this.currentDateKey);
        changeWorkingQueue(this.currentDateKey);
    }

    public synchronized void addTransaction(VendorDailyQuotaTransaction transaction){
        int dateKey = DateUtils.getDateKey(transaction.getCreatedAt(), this.timeZoneId);
        if(dateKey == this.currentDateKey)
            this.currentTransactionQueue.add(transaction);
        else{
            LinkedBlockingQueue<VendorDailyQuotaTransaction> dateQueue = this.getQueue(dateKey);
            dateQueue.add(transaction);
        }
    }

    public void switchDate(int dateKey){
        this.currentDateKey = dateKey;
        this.vendorQuotas = new ConcurrentHashMap<>();
        this.changeWorkingQueue(dateKey);
        this.dateTransactionQueueMap.clear();
    }

    private void changeWorkingQueue(int dateKey){
        if(this.dateTransactionQueueMap.get(dateKey) == null){
            this.dateTransactionQueueMap.put(this.currentDateKey, new LinkedBlockingQueue<>());
        }
        this.currentTransactionQueue.addAll(this.dateTransactionQueueMap.get(dateKey));
    }

    public LinkedBlockingQueue<VendorDailyQuotaTransaction> getQueue(int dateKey){
        if(!dateTransactionQueueMap.containsKey(dateKey)){
            addNewDateQueue(dateKey);
        }
        return this.dateTransactionQueueMap.get(dateKey);
    }
    public void addNewDateQueue(int dateKey){
        this.dateTransactionQueueMap.put(dateKey, new LinkedBlockingQueue<>());
    }

    public int getCurrentDateKey() {
        return currentDateKey;
    }

    public void setCurrentDateKey(int currentDateKey) {
        this.currentDateKey = currentDateKey;
    }

    public Map<Integer, LinkedBlockingQueue<VendorDailyQuotaTransaction>> getDateTransactionQueueMap() {
        return dateTransactionQueueMap;
    }

    public void setDateTransactionQueueMap(Map<Integer, LinkedBlockingQueue<VendorDailyQuotaTransaction>> dateTransactionQueueMap) {
        this.dateTransactionQueueMap = dateTransactionQueueMap;
    }

    public LinkedBlockingQueue<VendorDailyQuotaTransaction> getCurrentTransactionQueue() {
        return currentTransactionQueue;
    }

    public void setCurrentTransactionQueue(LinkedBlockingQueue<VendorDailyQuotaTransaction> currentTransactionQueue) {
        this.currentTransactionQueue = currentTransactionQueue;
    }

    public List<VendorCommodity> getVendorCommodities() {
        return vendorCommodities;
    }

    public void setVendorCommodities(List<VendorCommodity> vendorCommodities) {
        this.vendorCommodities = vendorCommodities;
    }

    public ConcurrentHashMap<VendorQuotaKey, VendorDailyQuota> getVendorQuotas() {
        return vendorQuotas;
    }

    public void setVendorQuotas(ConcurrentHashMap<VendorQuotaKey, VendorDailyQuota> vendorQuotas) {
        this.vendorQuotas = vendorQuotas;
    }

    public VendorQuotaCurrentTransaction getQuotaCurrentTransaction() {
        return quotaCurrentTransaction;
    }

    public void setQuotaCurrentTransaction(VendorQuotaCurrentTransaction quotaCurrentTransaction) {
        this.quotaCurrentTransaction = quotaCurrentTransaction;
    }
}
