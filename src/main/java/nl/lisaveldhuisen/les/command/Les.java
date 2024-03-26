package nl.lisaveldhuisen.les.command;

import nl.lisaveldhuisen.les.coreapi.LesVerplaatst;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.EntityId;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

public class Les {
    @EntityId
    private UUID lesId;
    private UUID klantId;
    private LocalDateTime startTijd;
    private Duration duration;

    public Les(UUID lesId, UUID klantId, LocalDateTime startTijd, Duration duration) {
        this.lesId = lesId;
        this.klantId = klantId;
        this.startTijd = startTijd;
        this.duration = duration;
    }

    boolean overlaps(LocalDateTime start, Duration duration) {
        LocalDateTime thisEindTijd = startTijd.plus(this.duration.toMinutes(), ChronoUnit.MINUTES);
        LocalDateTime gewijzigdeEindTijd = start.plus(duration.toMinutes(), ChronoUnit.MINUTES);
        return start.isBefore(thisEindTijd) && gewijzigdeEindTijd.isAfter(startTijd);
    }

    @EventSourcingHandler
    public void on(LesVerplaatst event) {
        this.startTijd = event.getDatumTijdStart();
        this.duration = event.getDuur();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Les les = (Les) o;
        return Objects.equals(lesId, les.lesId) && Objects.equals(klantId, les.klantId) && Objects.equals(startTijd, les.startTijd) && Objects.equals(duration, les.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lesId, klantId, startTijd, duration);
    }
}
