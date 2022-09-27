package vn.sapo.domain.vendor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendor_quota_current_transaction")
public class VendorQuotaCurrentTransaction {
    @Id
    private Integer id;
    private Long transactionId;

    public VendorQuotaCurrentTransaction(){}
    public VendorQuotaCurrentTransaction(int id, long transactionId){
        this.id = id;
        this.transactionId = transactionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long inventoryTransactionId) {
        this.transactionId = inventoryTransactionId;
    }
}
