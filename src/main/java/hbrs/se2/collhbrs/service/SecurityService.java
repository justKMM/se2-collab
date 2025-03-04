package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.repository.BusinessRepository;
import hbrs.se2.collhbrs.repository.StudentRepository;
import hbrs.se2.collhbrs.repository.UserRepository;
import hbrs.se2.collhbrs.util.Globals;
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

    private final UserRepository userRepository;

    private final StudentRepository studentRepository;

    private final BusinessRepository businessRepository;

    public SecurityService(UserRepository userRepository, StudentRepository studentRepository, BusinessRepository businessRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.businessRepository = businessRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetails(userRepository.findByUsername(username));
    }

    class CustomUserDetails implements UserDetails {

        private final String username;
        private final String password;
        private final List<GrantedAuthority> authorities;

        public CustomUserDetails(User user) {
            try {
                this.username = user.getUsername();
                this.password = user.getPassword();
                this.authorities = new ArrayList<>();

                if (studentRepository.existsByUserUserID(user.getUserID())) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + Globals.Roles.STUDENT));
                } else if (businessRepository.existsByUserUserID(user.getUserID())) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + Globals.Roles.BUSINESS));
                }
            } catch (Exception e) {
                throw new UsernameNotFoundException(e.getMessage());
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


