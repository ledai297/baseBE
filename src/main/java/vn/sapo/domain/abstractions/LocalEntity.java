package vn.sapo.domain.abstractions;

public interface LocalEntity<TRoot, ID> extends Entity<ID>{
    TRoot getAggRoot();
}
