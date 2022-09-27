package vn.sapo.domain;

import vn.sapo.statics.TenantStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "sapo_tenant")
public class SapoTenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @NotNull
    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;
    @Email
//    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;
    @NotBlank
    @Column(name = "domain_alias", nullable = false)
    private String domainAlias;
    @NotBlank
    @Column(name = "domain", nullable = false)
    private String domain;
    @NotBlank
    @Column(name = "access_token", nullable = false)
    private String accessToken;
    @NotNull
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TenantStatus status;
    @NotNull
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    @NotNull
    @Column(name = "modified_at", nullable = false)
    private Date modifiedAt;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "avatar_uri", nullable = false)
    private String avatarUri;
    private String name;
    @Column(name = "city_id", nullable = false)
    private Long cityId;
    @Column(name = "district_id", nullable = false)
    private Long districtId;
    @Column(name = "ward_id", nullable = false)
    private Long wardId;
    @Column(name = "address_line1", nullable = false)
    private String addressLine1;
    @Column(name = "last_pull_at", nullable = false)
    private Date lastPullAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomainAlias() {
        return domainAlias;
    }

    public void setDomainAlias(String domainAlias) {
        this.domainAlias = domainAlias;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domainUri) {
        this.domain = domainUri;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public TenantStatus getStatus() {
        return status;
    }

    public void setStatus(TenantStatus status) {
        this.status = status;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public Date getLastPullAt() {
        return lastPullAt;
    }

    public void setLastPullAt(Date lastPullAt) {
        this.lastPullAt = lastPullAt;
    }
}
