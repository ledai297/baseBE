package vn.sapo.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class SimpleEntity {
    public abstract Long getId();

    public abstract void setId(Long id);
}
