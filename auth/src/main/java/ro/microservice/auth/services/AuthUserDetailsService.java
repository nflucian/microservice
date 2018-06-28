package ro.microservice.auth.services;


import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.microservice.auth.models.UserModel;
import ro.microservice.auth.repositories.UserRepository;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(username)
                .map(u -> new UserModel(u.getUsername(),u.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER"))))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
