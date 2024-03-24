package nl.lisaveldhuisen.les.event.repository.locatie;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class LocatieEntity {
    @Id
    private UUID id;
    private String naam;
    private String straat;
    private String postCode;
    private String woonplaats;
    private String latitude;
    private String longitude;
}