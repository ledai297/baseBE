package vn.sapo.vm.salequota;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class SaleQuotaDailyTransactionDTO {
    @NotNull
    private Long orderId;
    @NotNull
    private Long eventId;
    @NotEmpty
    private List<@Valid SaleQuotaDailyTransactionLineItemDTO> lineItems;
    @NotNull
    private Date calculateDate;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<SaleQuotaDailyTransactionLineItemDTO> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<SaleQuotaDailyTransactionLineItemDTO> lineItems) {
        this.lineItems = lineItems;
    }

    public Date getCalculateDate() {
        return calculateDate;
    }

    public void setCalculateDate(Date calculateDate) {
        this.calculateDate = calculateDate;
    }
}
