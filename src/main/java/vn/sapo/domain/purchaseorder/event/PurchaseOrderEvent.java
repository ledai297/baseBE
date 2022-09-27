package vn.sapo.domain.purchaseorder.event;

import vn.sapo.domain.abstractions.event.DomainEvent;

import java.time.Instant;

public class PurchaseOrderEvent extends DomainEvent {
    public PurchaseOrderEvent(PurchaseOrderEventName eventName) {
        this.setName(eventName.name());
        this.setCreatedAt(Instant.now());
    }

    public PurchaseOrderEvent(PurchaseOrderEventName eventName, Object payload) {
        this(eventName);
        this.setPayload(payload);
    }
}
