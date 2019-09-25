package ru.korablev.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.korablev.model.CurrentProfile;
import ru.korablev.model.User;

public class SecurityUtil {
    public static CurrentProfile getCurrentProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof CurrentProfile) {
            return ((CurrentProfile) principal);
        } else {
            return null;
        }
    }

    public static void authentificate(User user) {
        CurrentProfile currentProfile = new CurrentProfile(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                currentProfile, currentProfile.getPassword(), currentProfile.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static Long getCurrentIdProfile() {
        CurrentProfile currentProfile = getCurrentProfile();
        return currentProfile != null ? currentProfile.getId() : null;
    }

    public static boolean isCurrentProfileAuthentificated() {
        return getCurrentProfile() != null;
    }
}
