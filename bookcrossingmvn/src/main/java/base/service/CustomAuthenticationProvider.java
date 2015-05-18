package base.service;

import base.daos.OracleDBUserDAO;
import base.daos.UserDAO;
import base.entities.Role;
import base.entities.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadim on 23.04.15.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserDAO userDAO;

    public CustomAuthenticationProvider() {
        userDAO = new OracleDBUserDAO();
    }

    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {


            String name = authentication.getName();
            String pass = authentication.getCredentials().toString();

            List<User> userList = userDAO.getUserNameLike(name);
            User authenticatedUser = null;

            if ((userList == null) || (userList.isEmpty())) {
                throw new BadCredentialsException("Unable to auth against third party systems");
            }

            for (User user : userList) {
                if (user.getPass().equals(pass)) {
                    authenticatedUser = user;
                    break;
                }
            }
            if (authenticatedUser != null) {
                List<Role> grantedAuths = new ArrayList<Role>();
                try {
                    for (Role role : authenticatedUser.getRoles()) {
                        grantedAuths.add(role);
                    }
                } catch (Exception e) {
                    System.out.println("asdasd");
                }

                return new UsernamePasswordAuthenticationToken(name, pass, grantedAuths);
            } else {
                throw new BadCredentialsException("Unable to auth against third party systems");
            }

    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}