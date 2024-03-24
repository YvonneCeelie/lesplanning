package nl.lisaveldhuisen.les.event.repository.klant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class KlantEntity {
    @Id
    private UUID id;
    private String naam;
    private String straat;
    private String woonplaats;
    private String email;
}
