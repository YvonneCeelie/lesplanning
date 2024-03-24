package nl.lisaveldhuisen.les.command;

import nl.lisaveldhuisen.les.coreapi.AddMaandPlanning;
import nl.lisaveldhuisen.les.coreapi.LesGepland;
import nl.lisaveldhuisen.les.coreapi.MaandPlanningAdded;
import nl.lisaveldhuisen.les.coreapi.PlanLes;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class LesPlanningTest {
    private AggregateTestFixture<LesMaandPlanning> fixture = new AggregateTestFixture<>(LesMaandPlanning.class);

    @Test
    void planLesAppliesLesPlanned() {
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
}
