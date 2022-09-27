package vn.sapo.domain.abstractions.event;

import java.io.Serializable;
import java.time.Instant;

public abstract class DomainEvent implements Serializable {
    private String name;
    private Object payload;
    private Instant createdAt;

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public Object getPayload() {
        return payload;
    }

    protected void setPayload(Object payload) {
        this.payload = payload;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    protected void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
