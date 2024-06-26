package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.UserRepository;
import hbrs.se2.collhbrs.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ImplUserDetailsService implements UserDetailsService {
    private UserDetails userDetails;
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return UserToUserDetails(user);
    }

    private UserDetails UserToUserDetails(User user) {
        if (userDetails == null) {
            userDetails = new ImplUserDetails(user);
        }
        return userDetails;
    }

    class ImplUserDetails implements UserDetails {
        String username;
        String password;
        List<GrantedAuthority> authorities;
        boolean accountNonExpired;
        boolean accountNonLocked;
        boolean credentialsNonExpired;
        boolean enabled;

        public ImplUserDetails(User user) {
            this.username = user.getUsername();
            this.password = user.getPassword();
            // this.authorities = user.getAuthorities();
            this.authorities = new ArrayList<GrantedAuthority>() {
                {
                    add(new SimpleGrantedAuthority(Globals.Roles.USER));
                }
            };
        }
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return username;
        }

        @Override
        public String getUsername() {
            return password;
        }

        @Override
        public boolean isAccountNonExpired() {
            return accountNonExpired;
        }

        @Override
        public boolean isAccountNonLocked() {
            return accountNonLocked;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return credentialsNonExpired;
        }

        @Override
        public boolean isEnabled() {
            return enabled;
        }
    }
}
