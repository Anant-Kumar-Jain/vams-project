package com.vams.security;
import com.vams.model.User;
import com.vams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired private UserRepository userRepository;
    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseGet(() -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username)));
        return new CustomUserDetails(user);
    }
}
