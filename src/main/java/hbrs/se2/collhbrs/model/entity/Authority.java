package hbrs.se2.collhbrs.model.entity;

import com.vaadin.pro.licensechecker.Product;
import hbrs.se2.collhbrs.util.Globals;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
@Entity
@Table(name = "authority", schema = "public")
public class Authority extends Globals.Roles implements GrantedAuthority, Serializable {
    @ManyToOne
    @JoinColumn(name="benutzerid")
    private User user;
    private String role;
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Authority(String role) {
        this.role = role;
    }

    public Authority() {

    }

    @Override
    public String getAuthority() {
        return role;
    }

}
