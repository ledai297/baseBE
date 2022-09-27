package vn.sapo.vm.user;

import vn.sapo.vm.pagination.Pagination;

import java.util.List;

public class UserFilterResponse {
    private List<UserResponse> users;
    private Pagination pagination;

    public UserFilterResponse(List<UserResponse> users, Pagination pagination){
        this.users = users;
        this.pagination = pagination;
    }

    public List<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
