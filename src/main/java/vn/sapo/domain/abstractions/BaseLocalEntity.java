package vn.sapo.domain.abstractions;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class BaseLocalEntity<TRoot, ID> extends BaseEntity<ID> implements LocalEntity<TRoot, ID>{
    @JsonIgnore
    protected TRoot aggRoot;

    public TRoot getAggRoot(){
        return aggRoot;
    }
}
