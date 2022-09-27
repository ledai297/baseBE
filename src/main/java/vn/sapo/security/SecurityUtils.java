package vn.sapo.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import vn.sapo.domain.User;
import vn.sapo.security.authentication.GeneralAuthentication;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null &&
            getAuthorities(authentication).noneMatch(AuthoritiesConstants.ANONYMOUS::equals);
    }

    /**
     * If the current user has a specific authority (security role).
     * <p>
     * The name of this method comes from the {@code isUserInRole()} method in the Servlet API.
     *
     * @param authority the authority to check.
     * @return true if the current user has the authority, false otherwise.
     */
    public static boolean isCurrentUserInRole(String authority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null &&
            getAuthorities(authentication).anyMatch(authority::equals);
    }

    public static List<String> getCurrentAuthorities(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
    }

    private static Stream<String> getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority);
    }

    public static Optional<Long> getCurrentUserId() throws RuntimeException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null)
            return Optional.empty();

        if(authentication instanceof AnonymousAuthenticationToken)
            return Optional.empty();

        if(authentication instanceof UsernamePasswordAuthenticationToken) {
            BasicUser principal = (BasicUser) authentication.getPrincipal();

            return Optional.of(principal.getId());
        }

        if(!(authentication instanceof GeneralAuthentication))
            throw new RuntimeException("Authentication must be instance of GeneralAuthentication");

        return Optional.ofNullable(((GeneralAuthentication) authentication).getUserId());
    }
    public static String getCurrentClientId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null)
            return null;

        if(authentication instanceof AnonymousAuthenticationToken)
            return null;

        if(authentication instanceof UsernamePasswordAuthenticationToken){
            return null;
        }

        if(!(authentication instanceof GeneralAuthentication))
            throw new RuntimeException("Authentication must be instance of GeneralAuthentication");
        GeneralAuthentication generalAuthentication = (GeneralAuthentication) authentication;

        return generalAuthentication.getClientId();
    }

    public static boolean isUserInRole(User user, String authority) {
        return user.getAuthorities().stream().anyMatch(a -> a.getName().equals(authority));
    }
}
