package nl.lisaveldhuisen.les.command;

import nl.lisaveldhuisen.les.coreapi.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class KlantTest {
    private AggregateTestFixture<Klant> fixture = new AggregateTestFixture<>(Klant.class);

    @Test
    void registreerKlant() {
        UUID klantId = UUID.randomUUID();
        String klantNaam = "Piet Puk";
        String straat = "Damstraat 7";
        String postCode = "1000AZ";
        String woonplaats = "Amsterdam";
        String email = "piet@gmail.com";
        fixture.givenNoPriorActivity()
                .when(new RegistreerKlant(klantId, klantNaam, straat, postCode, woonplaats, email))
                .expectEvents(new KlantGeregistreerd(klantId, klantNaam, straat, postCode, woonplaats, email));
    }
    @Test
    void verwijderKlant() {
        UUID klantId = UUID.randomUUID();
        String klantNaam = "Piet Puk";
        String straat = "Damstraat 7";
        String postCode = "1000AZ";
        String woonplaats = "Amsterdam";
        String email = "piet@gmail.com";
        fixture.given(new KlantGeregistreerd(klantId, klantNaam, straat,postCode,woonplaats,email))
                .when(new VerwijderKlant(klantId))
                .expectEvents(new KlantVerwijderd(klantId));
    }
}
