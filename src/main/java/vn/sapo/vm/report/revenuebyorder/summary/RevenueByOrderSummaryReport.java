package vn.sapo.vm.report.revenuebyorder.summary;


public class RevenueByOrderSummaryReport {
    private RevenueByOrderSummary completedOrder;
    private RevenueByOrderSummary processingOrder;
    private RevenueByOrderSummary cancelledOrder;

    public RevenueByOrderSummary getCompletedOrder() {
        return completedOrder;
    }

    public void setCompletedOrder(RevenueByOrderSummary completedOrder) {
        this.completedOrder = completedOrder;
    }

    public RevenueByOrderSummary getProcessingOrder() {
        return processingOrder;
    }

    public void setProcessingOrder(RevenueByOrderSummary processingOrder) {
        this.processingOrder = processingOrder;
    }

    public RevenueByOrderSummary getCancelledOrder() {
        return cancelledOrder;
    }

    public void setCancelledOrder(RevenueByOrderSummary cancelledOrder) {
        this.cancelledOrder = cancelledOrder;
    }
}
