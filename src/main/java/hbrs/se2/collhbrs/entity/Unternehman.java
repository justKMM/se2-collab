package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "unternehmen")
public class Unternehman {
    @Id
    @Column(name = "unternehmenid", nullable = false, precision = 64)
    private BigDecimal id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "benutzerid", nullable = false)
    private Benutzer benutzerID;

    @Size(max = 128)
    @NotNull
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Benutzer getBenutzerID() {
        return benutzerID;
    }

    public void setBenutzerID(Benutzer benutzerID) {
        this.benutzerID = benutzerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}