package nl.lisaveldhuisen.les.event.repository.locatie;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class LocatieEntity {
    @Id
    private UUID locatieId;
    private String naam;
    private String straat;
    private String postCode;
    private String woonplaats;
    private String latitude;
    private String longitude;
}