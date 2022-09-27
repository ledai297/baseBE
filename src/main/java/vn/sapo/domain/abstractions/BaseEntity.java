package vn.sapo.domain.abstractions;

import vn.sapo.config.Constants;
import vn.sapo.domain.abstractions.event.DomainEvent;
import vn.sapo.security.SecurityUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseEntity<TId> implements Entity<TId>{
    protected TId id;
    private List<DomainEvent> events = new ArrayList<>();
    private Integer version;
    private Long createdBy;
    private Instant createdDate;
    private Instant modifiedDate;
    private boolean created;
    private boolean modified;

    protected void create(){
        this.created = true;
        this.createdBy = SecurityUtils.getCurrentUserId().orElse(Constants.SYSTEM_ACCOUNT_ID);
        this.createdDate = Instant.now();
        this.modifiedDate = Instant.now();
    }
    protected void modify(){
        this.modified = true;
        this.modifiedDate = Instant.now();
    }

    @Override
    public void addEvent(DomainEvent event) {
        this.events = this.events == null ? new ArrayList<>() : this.events;
        this.events.add(event);
    }

    @Override
    public TId getId() {
        return id;
    }
    @Override
    public List<DomainEvent> getEvents() {
        return events;
    }

    public Integer getVersion() {
        return version;
    }

    public boolean isCreated() {
        return created;
    }

    public boolean isModified() {
        return modified;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }
}
