package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.BusinessRepository;
import hbrs.se2.collhbrs.repository.StudentRepository;
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
public class SecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CostumUserDetails(userRepository.findByUsername(username));
    }

    class CostumUserDetails implements UserDetails {

        private String username;
        private String password;
        private List<GrantedAuthority> authorities;

        public CostumUserDetails(User user) {
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.authorities = new ArrayList<>();

            if (studentRepository.existsByUser_UserID(user.getUserID())) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + Globals.Roles.STUDENT));
            } else if (businessRepository.existsByUser_UserID(user.getUserID())) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + Globals.Roles.BUSINESS));
            }
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}


