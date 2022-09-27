package vn.sapo.vm.report.export;

public class SignboardContractBySaleExcelModel {
    private Integer index;
    private String signboardContractCode;
    private String startedDate;
    private String username;
    private String address;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getSignboardContractCode() {
        return signboardContractCode;
    }

    public void setSignboardContractCode(String signboardContractCode) {
        this.signboardContractCode = signboardContractCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(String startedDate) {
        this.startedDate = startedDate;
    }
}
