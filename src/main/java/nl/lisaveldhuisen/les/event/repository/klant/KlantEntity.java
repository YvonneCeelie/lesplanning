package nl.lisaveldhuisen.les.event.repository.klant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.UUID;

@Entity
public class KlantEntity {
    @Id
    private UUID id;
    private String naam;
    private String straat;
    private String postCode;
    private String woonplaats;
    private String email;

    public KlantEntity() {
    }
    public KlantEntity(UUID id, String naam, String straat, String postCode, String woonplaats, String email) {
        this.id = id;
        this.naam = naam;
        this.straat = straat;
        this.postCode = postCode;
        this.woonplaats = woonplaats;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KlantEntity that = (KlantEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(naam, that.naam) && Objects.equals(straat, that.straat) && Objects.equals(postCode, that.postCode) && Objects.equals(woonplaats, that.woonplaats) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naam, straat, postCode, woonplaats, email);
    }
}
