package vn.sapo.domain;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "role")
@TypeDef(
    name = "list-array",
    typeClass = ListArrayType.class
)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "(?=.*[A-Z])[\\p{Punct}A-Z0-9 ]{1,50}", message = "Role name không đúng định dạng")
    @Column(unique = true, nullable = false)
    private String name;

    @Type(type = "list-array")
    @NotNull
    private List<@Size(max = 50) String> authorities = new ArrayList<>();

    @Type(type = "list-array")
    @NotNull
    private List<@Size(max = 50) @Pattern(regexp = "(?=.*[A-Z])[\\p{Punct}A-Z0-9 ]{1,50}", message = "Role name không đúng định dạng") String> inheritFrom = new ArrayList<>();

    private Date createdAt;
    private Date modifiedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<String> getInheritFrom() {
        return inheritFrom;
    }

    public void setInheritFrom(List<String> inheritFrom) {
        this.inheritFrom = inheritFrom;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
