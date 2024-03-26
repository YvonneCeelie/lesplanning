package nl.lisaveldhuisen.les.event.repository.locatie;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.UUID;

@Entity
public class LocatieEntity {
    @Id
    private UUID id;
    private String naam;
    private String straat;
    private String postCode;
    private String woonplaats;
    private Double latitude;
    private Double longitude;

    public LocatieEntity(UUID id, String naam, String straat, String postCode, String woonplaats, Double latitude, Double longitude) {
        this.id = id;
        this.naam = naam;
        this.straat = straat;
        this.postCode = postCode;
        this.woonplaats = woonplaats;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocatieEntity() {
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocatieEntity that = (LocatieEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(naam, that.naam) && Objects.equals(straat, that.straat) && Objects.equals(postCode, that.postCode) && Objects.equals(woonplaats, that.woonplaats) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naam, straat, postCode, woonplaats, latitude, longitude);
    }
}