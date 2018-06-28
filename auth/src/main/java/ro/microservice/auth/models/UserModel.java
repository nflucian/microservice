package ro.microservice.auth.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserModel extends User {
    public UserModel(final String username, final String password, final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
