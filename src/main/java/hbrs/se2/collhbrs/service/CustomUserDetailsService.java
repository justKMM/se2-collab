package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.BusinessRepository;
import hbrs.se2.collhbrs.repository.StudentRepository;
import hbrs.se2.collhbrs.repository.UserRepository;
import hbrs.se2.collhbrs.util.Globals;
import lombok.Setter;
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
public class CustomUserDetailsService implements UserDetailsService {
    private UserDetails userDetails;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BusinessRepository businessRepository;
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
            userDetails = new CustomUserDetails(user);
        }
        return userDetails;
    }

    class CustomUserDetails implements UserDetails {
        Long userID;
        String username;
        String password;
        List<GrantedAuthority> authorities;
        @Setter
        boolean accountNonExpired = true;
        @Setter
        boolean accountNonLocked = true;
        @Setter
        boolean credentialsNonExpired = true;
        @Setter
        boolean enabled = true;

        public CustomUserDetails(User user) {
            this.userID = user.getUserID();
            this.username = user.getUsername();
            this.password = user.getPassword();
            // this.authorities = user.getAuthorities();
            this.authorities = new ArrayList<>() {
                {
                    add(new SimpleGrantedAuthority("ROLE_" + Globals.Roles.USER));
                }
            };
            if (studentRepository.existsByUser_UserID(userID)) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + Globals.Roles.STUDENT));
            }
            else if (businessRepository.existsByUser_UserID(userID)) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + Globals.Roles.BUSINESS));
            }
            else System.out.println("User neither student nor business");
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
