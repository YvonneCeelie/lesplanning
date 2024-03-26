package nl.lisaveldhuisen.les.command;

import nl.lisaveldhuisen.les.coreapi.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class LocatieTest {
    private AggregateTestFixture<Locatie> fixture = new AggregateTestFixture<>(Locatie.class);

    @Test
    void registreerLocatie() {
        UUID locatieId = UUID.randomUUID();
        String locatieNaam = "Stal Welgelegen";
        String straat = "Damstraat 7";
        String postCode = "1000AZ";
        String woonplaats = "Amsterdam";
        Double longitude = 41.40338;
        Double latitude =  2.17403;
        fixture.givenNoPriorActivity()
                .when(new RegistreerLocatie(locatieId, locatieNaam, straat, postCode, woonplaats, latitude, longitude))
                .expectEvents(new LocatieGeregistreerd(locatieId, locatieNaam, straat, postCode, woonplaats, latitude, longitude));
    }
    @Test
    void verwijderLocatie() {
        UUID locatieId = UUID.randomUUID();
        String locatieNaam = "Stal Welgelegen";
        String straat = "Damstraat 7";
        String postCode = "1000AZ";
        String woonplaats = "Amsterdam";
        Double longitude = 41.40338;
        Double latitude =  2.17403;
        fixture.given(new LocatieGeregistreerd(locatieId, locatieNaam, straat,postCode,woonplaats,longitude, latitude))
                .when(new VerwijderLocatie(locatieId))
                .expectEvents(new LocatieVerwijderd(locatieId));
    }
}
