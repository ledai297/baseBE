package vn.sapo.domain.abstractions;

import vn.sapo.domain.abstractions.event.DomainEvent;

import java.util.List;

public interface Entity<TId> {
    TId getId();
    List<DomainEvent> getEvents();
    void addEvent(DomainEvent event);
}
