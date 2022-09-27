package vn.sapo.statics.forwardercrosschecking;

public class ForwarderCrossCheckingStatus {
    public enum Status {
        DRAFT,
        CONFIRMED,
        CANCELLED
    }
    public enum CodStatus {
        MATCHED,
        UNMATCHED
    }
    public enum ShippingCostStatus {
        MATCHED,
        UNMATCHED
    }

    public static class Item {
        public enum Status {
            MATCHED,
            UNMATCHED
        }
        public enum CodStatus {
            MATCHED,
            UNMATCHED
        }
        public enum ShippingCostStatus {
            MATCHED,
            UNMATCHED
        }
    }
}
