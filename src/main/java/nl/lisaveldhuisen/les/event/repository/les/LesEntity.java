package nl.lisaveldhuisen.les.event.repository.les;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.UUID;

@Entity
public class LesEntity {
    @Id
    @GeneratedValue
    UUID id;
    private YearMonth maand;
    private LocalDateTime startTijd;
    private LocalDateTime eindTijd;
    private UUID klantId;
    private UUID locatieId;

    public LesEntity(UUID id, YearMonth maand, LocalDateTime startTijd, LocalDateTime eindTijd, UUID klantId, UUID locatieId) {
        this.id = id;
        this.maand = maand;
        this.startTijd = startTijd;
        this.eindTijd = eindTijd;
        this.klantId = klantId;
        this.klantId = locatieId;
    }

    public LesEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public YearMonth getMaand() {
        return maand;
    }

    public void setMaand(YearMonth maand) {
        this.maand = maand;
    }

    public LocalDateTime getStartTijd() {
        return startTijd;
    }

    public void setStartTijd(LocalDateTime startTijd) {
        this.startTijd = startTijd;
    }

    public LocalDateTime getEindTijd() {
        return eindTijd;
    }

    public void setEindTijd(LocalDateTime eindTijd) {
        this.eindTijd = eindTijd;
    }

    public UUID getKlantId() {
        return klantId;
    }

    public void setKlantId(UUID klantId) {
        this.klantId = klantId;
    }

    public UUID getLocatieId() {
        return locatieId;
    }

    public void setLocatieId(UUID locatieId) {
        this.locatieId = locatieId;
    }
}
