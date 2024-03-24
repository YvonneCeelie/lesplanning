package nl.lisaveldhuisen.les.command;

import nl.lisaveldhuisen.les.coreapi.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class LesMaandPlanningTest {
    private AggregateTestFixture<LesMaandPlanning> fixture = new AggregateTestFixture<>(LesMaandPlanning.class);

    @Test
    void planLesVoorNieuweMaand() {
        UUID lesId = UUID.randomUUID();
        UUID klantId = UUID.randomUUID();
        Duration halfUur = Duration.of(30, ChronoUnit.MINUTES);
        UUID locatieId = UUID.randomUUID();
        YearMonth yearMonth = YearMonth.now();
        LocalDateTime now = LocalDateTime.now();
        fixture.givenNoPriorActivity()
                .when(new PlanLes(yearMonth, lesId, klantId, now, halfUur, locatieId))
                .expectEvents(new LesGepland(yearMonth, lesId, klantId, now, halfUur, locatieId));
    }
    @Test
    void planLesVoorBestaandeMaand() {
        UUID bestaandeLesId = UUID.randomUUID();
        UUID bestaandeKlantId = UUID.randomUUID();
        Duration halfUur = Duration.of(30, ChronoUnit.MINUTES);
        UUID bestaandeLocatieId = UUID.randomUUID();
        YearMonth huidigeMaand = YearMonth.of(2024,2);
        LocalDateTime bestaandeStartTijd = LocalDateTime.of(2024,2,10, 21,0, 0);

        UUID nieuweLesId = UUID.randomUUID();
        UUID nieuweKlantId = UUID.randomUUID();
        Duration uur = Duration.of(60, ChronoUnit.MINUTES);
        UUID nieuweLocatieId = UUID.randomUUID();
        LocalDateTime nieuweStartTijd = LocalDateTime.of(2024,2,15, 21,0, 0);


        final LesGepland bestaandeLes = new LesGepland(huidigeMaand,bestaandeLesId,bestaandeKlantId,bestaandeStartTijd,halfUur,bestaandeLocatieId);
        final PlanLes planLes = new PlanLes(huidigeMaand,nieuweLesId, nieuweKlantId, nieuweStartTijd, uur, nieuweLocatieId);
        final LesGepland nieuweLesGepland = new LesGepland(huidigeMaand, nieuweLesId, nieuweKlantId, nieuweStartTijd, uur, nieuweLocatieId);
        fixture.given(bestaandeLes)
                .when(planLes)
                .expectEvents(nieuweLesGepland);
    }

    @Test
    void annuleerLes() {
        UUID bestaandeLesId = UUID.randomUUID();
        UUID bestaandeKlantId = UUID.randomUUID();
        Duration halfUur = Duration.of(30, ChronoUnit.MINUTES);
        UUID bestaandeLocatieId = UUID.randomUUID();
        YearMonth huidigeMaand = YearMonth.of(2024,2);
        LocalDateTime bestaandeStartTijd = LocalDateTime.of(2024,2,10, 21,0, 0);


        final LesGepland bestaandeLes = new LesGepland(huidigeMaand,bestaandeLesId,bestaandeKlantId,bestaandeStartTijd,halfUur,bestaandeLocatieId);
        final AnnuleerLes annuleerLes = new AnnuleerLes(huidigeMaand,bestaandeLesId);
        final LesGeannuleerd lesGeannuleerd = new LesGeannuleerd(huidigeMaand, bestaandeLesId);
        fixture.given(bestaandeLes)
                .when(annuleerLes)
                .expectEvents(lesGeannuleerd);
    }
    @Test
    void annuleerOnbekendeLes() {
        UUID bestaandeLesId = UUID.randomUUID();
        UUID bestaandeKlantId = UUID.randomUUID();
        Duration halfUur = Duration.of(30, ChronoUnit.MINUTES);
        UUID bestaandeLocatieId = UUID.randomUUID();
        YearMonth huidigeMaand = YearMonth.of(2024,2);
        LocalDateTime bestaandeStartTijd = LocalDateTime.of(2024,2,10, 21,0, 0);


        final LesGepland bestaandeLes = new LesGepland(huidigeMaand,bestaandeLesId,bestaandeKlantId,bestaandeStartTijd,halfUur,bestaandeLocatieId);
        final AnnuleerLes annuleerLes = new AnnuleerLes(huidigeMaand,UUID.randomUUID());
        final LesGeannuleerd lesGeannuleerd = new LesGeannuleerd(huidigeMaand, bestaandeLesId);
        fixture.given(bestaandeLes)
                .when(annuleerLes)
                .expectNoEvents();
    }
    @Test
    void verplaatsLes() {
        UUID bestaandeLesId = UUID.randomUUID();
        UUID bestaandeKlantId = UUID.randomUUID();
        Duration halfUur = Duration.of(30, ChronoUnit.MINUTES);
        UUID bestaandeLocatieId = UUID.randomUUID();
        YearMonth huidigeMaand = YearMonth.of(2024,2);
        LocalDateTime bestaandeStartTijd = LocalDateTime.of(2024,2,10, 21,0, 0);

        Duration uur = Duration.of(30, ChronoUnit.MINUTES);
        LocalDateTime nieuweStartTijd = LocalDateTime.of(2024,2,17, 20,0, 0);


        final LesGepland bestaandeLes = new LesGepland(huidigeMaand,bestaandeLesId,bestaandeKlantId,bestaandeStartTijd,halfUur,bestaandeLocatieId);
        final VerplaatsLes planLes = new VerplaatsLes(huidigeMaand,bestaandeLesId, bestaandeKlantId, bestaandeStartTijd, uur, nieuweStartTijd, halfUur, bestaandeLocatieId);
        final LesVerplaatst lesVerplaatst = new LesVerplaatst(huidigeMaand, bestaandeLesId, bestaandeKlantId, bestaandeStartTijd, uur, nieuweStartTijd, halfUur, bestaandeLocatieId);
        fixture.given(bestaandeLes)
                .when(planLes)
                .expectEvents(lesVerplaatst);
    }
    @Test
    void planOverlappendeLes() {
        UUID bestaandeLesId = UUID.randomUUID();
        UUID bestaandeKlantId = UUID.randomUUID();
        Duration halfUur = Duration.of(30, ChronoUnit.MINUTES);
        UUID bestaandeLocatieId = UUID.randomUUID();
        YearMonth huidigeMaand = YearMonth.of(2024,2);
        LocalDateTime bestaandeStartTijd = LocalDateTime.of(2024,2,10, 21,0, 0);

        UUID nieuweLesId = UUID.randomUUID();
        UUID nieuweKlantId = UUID.randomUUID();
        Duration uur = Duration.of(60, ChronoUnit.MINUTES);
        UUID nieuweLocatieId = UUID.randomUUID();
        LocalDateTime nieuweStartTijd = LocalDateTime.of(2024,2,10, 21,15, 0);


        final LesGepland bestaandeLes = new LesGepland(huidigeMaand,bestaandeLesId,bestaandeKlantId,bestaandeStartTijd,halfUur,bestaandeLocatieId);
        final PlanLes planLes = new PlanLes(huidigeMaand,nieuweLesId, nieuweKlantId, nieuweStartTijd, uur, nieuweLocatieId);
        fixture.given(bestaandeLes)
                .when(planLes)
                .expectNoEvents()
                .expectExceptionMessage("Deze les overlapt met een andere les");
    }
    @Test
    void verplaatsOverlappendeLes() {
        UUID bestaandeLesId = UUID.randomUUID();
        UUID bestaandeKlantId = UUID.randomUUID();
        Duration halfUur = Duration.of(30, ChronoUnit.MINUTES);
        UUID bestaandeLocatieId = UUID.randomUUID();
        YearMonth huidigeMaand = YearMonth.of(2024,2);
        LocalDateTime bestaandeStartTijd = LocalDateTime.of(2024,2,10, 21,0, 0);

        Duration uur = Duration.of(30, ChronoUnit.MINUTES);
        LocalDateTime nieuweStartTijd = LocalDateTime.of(2024,2,10, 21,20, 0);


        final LesGepland bestaandeLes = new LesGepland(huidigeMaand,bestaandeLesId,bestaandeKlantId,bestaandeStartTijd,halfUur,bestaandeLocatieId);
        final VerplaatsLes planLes = new VerplaatsLes(huidigeMaand,bestaandeLesId, bestaandeKlantId, bestaandeStartTijd, uur, nieuweStartTijd, halfUur, bestaandeLocatieId);
        final LesVerplaatst lesVerplaatst = new LesVerplaatst(huidigeMaand, bestaandeLesId, bestaandeKlantId, bestaandeStartTijd, uur, nieuweStartTijd, halfUur, bestaandeLocatieId);
        fixture.given(bestaandeLes)
                .when(planLes)
                .expectNoEvents()
                .expectExceptionMessage("Deze les overlapt met een andere les");
    }

}
