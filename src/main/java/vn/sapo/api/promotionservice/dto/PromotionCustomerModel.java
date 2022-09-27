package vn.sapo.api.promotionservice.dto;

import java.util.Date;

public class PromotionCustomerModel {
    private long id;
    private Date dob;
    private int dobDay;
    private int dobMonth;
    private int dboYear;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getDobDay() {
        return dobDay;
    }

    public void setDobDay(int dobDay) {
        this.dobDay = dobDay;
    }

    public int getDobMonth() {
        return dobMonth;
    }

    public void setDobMonth(int dobMonth) {
        this.dobMonth = dobMonth;
    }

    public int getDboYear() {
        return dboYear;
    }

    public void setDboYear(int dboYear) {
        this.dboYear = dboYear;
    }
}
