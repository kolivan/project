package base.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by vadim on 24.04.15.
 */
public class CustomAuthenticationManager implements AuthenticationManager {

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthenticationProvider obj = new CustomAuthenticationProvider();
        Authentication auth = obj.authenticate(authentication);
        return auth;
    }
}
