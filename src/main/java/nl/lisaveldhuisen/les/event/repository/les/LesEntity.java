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
    private String klantNaam;
    private String locatieNaam;
}
