package vn.sapo.vm.user;

import javax.validation.constraints.NotNull;

/**
 * View Model object for storing the user's refresh token.
 */
public class RefreshTokenVM {

    @NotNull
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
