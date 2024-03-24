package nl.lisaveldhuisen.les.event.repository.klant;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class KlantEntity {
    @Id
    private UUID klantId;
    private String naam;
    private String straat;
    private String woonplaats;
    private String email;
}
