package vn.sapo.vm.user;

import vn.sapo.vm.pagination.BasePaginationFilterModel;

import java.util.List;

public class UserFilter extends BasePaginationFilterModel {
    private List<String> authorities;
    private List<Long> userIds;
    private String keyword;
    private boolean activated = true;

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
