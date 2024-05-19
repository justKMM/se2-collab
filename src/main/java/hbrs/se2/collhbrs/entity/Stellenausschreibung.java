package hbrs.se2.collhbrs.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "stellenausschreibung", schema = "public")
public class Stellenausschreibung {
    private long stellenausschreibungID;
    private Unternehmen unternehmen;
    private String titel;
    private String beschreibung;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stellenausschreibungid", length = 64, nullable = false)
    public long getStellenausschreibungID() {
        return stellenausschreibungID;
    }

    public void setStellenausschreibungID(long stellenausschreibungID) {
        this.stellenausschreibungID = stellenausschreibungID;
    }

    @ManyToOne
    @JoinColumn(name = "unternehmenid", nullable = false)
    public Unternehmen getUnternehmen() {
        return unternehmen;
    }

    public void setUnternehmen(Unternehmen unternehmen) {
        this.unternehmen = unternehmen;
    }

    @Basic
    @Column(name = "titel", length = 256, nullable = false)
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    @Basic
    @Column(name = "beschreibung", length = 6400, nullable = false)
    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stellenausschreibung that = (Stellenausschreibung) o;
        return stellenausschreibungID == that.stellenausschreibungID &&
                Objects.equals(unternehmen, that.unternehmen) &&
                Objects.equals(titel, that.titel) &&
                Objects.equals(beschreibung, that.beschreibung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stellenausschreibungID, unternehmen, titel, beschreibung);
    }
}
