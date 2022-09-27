package vn.sapo.vm.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class SaleAssignmentRequest {
    @NotEmpty(message = "UserIds không được bỏ trống!")
    private List<Long> userIds;

    @NotBlank(message = "Email của nhân viên kinh doanh không được bỏ trống!")
    private String saleEmployeeEmail;

    public SaleAssignmentRequest() {
    }

    public SaleAssignmentRequest(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public String getSaleEmployeeEmail() {
        return saleEmployeeEmail;
    }

    public void setSaleEmployeeEmail(String saleEmployeeEmail) {
        this.saleEmployeeEmail = saleEmployeeEmail;
    }
}
