package vn.sapo.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "country")
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "country_name", length = 100, nullable = false)
    private String countryName;

    @NotBlank
    @Size(max = 150)
    @Column(name = "official_state_name", length = 150, nullable = false)
    private String officialStateName;

    @NotBlank
    @Size(max = 10)
    @Column(name = "alpha_2_code", length = 10, nullable = false)
    private String alpha2Code;

    @NotBlank
    @Size(max = 10)
    @Column(name = "alpha_3_code", length = 10, nullable = false)
    private String alpha3Code;

    @NotBlank
    @Size(max = 10)
    @Column(name = "numeric_code", length = 10, nullable = false)
    private String numericCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getOfficialStateName() {
        return officialStateName;
    }

    public void setOfficialStateName(String officialStateName) {
        this.officialStateName = officialStateName;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }
        return id != null && id.equals(((Country) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Country{" +
            "id=" + id +
            ", countryName='" + countryName + '\'' +
            ", officialStateName='" + officialStateName + '\'' +
            ", alpha2Code='" + alpha2Code + '\'' +
            ", alpha3Code='" + alpha3Code + '\'' +
            ", numericCode='" + numericCode + '\'' +
            '}';
    }
}
