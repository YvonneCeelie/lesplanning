package nl.lisaveldhuisen.les.command;

import nl.lisaveldhuisen.les.coreapi.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.*;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class LesMaandPlanning {
    @AggregateIdentifier
    private YearMonth maand;
    @AggregateMember
    private Map<UUID, Les> lessen;

    public LesMaandPlanning() {
        // Needed for Axon
    }

    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    @CommandHandler
    public void handle(PlanLes command) {
        if (AggregateLifecycle.getVersion() != null) {
            checkOverlap(command.getDatumTijdStart(), command.getDuur());
        }
        apply(new LesGepland(command.getMaand(), command.getLesId(), command.getKlantId(), command.getDatumTijdStart(), command.getDuur(), command.getLocatieId()));
    }

    @CommandHandler
    public void handle(VerplaatsLes command) {
        checkOverlap(command.getDatumTijdStartNieuw(), command.getDuurNieuw());
        apply(new LesVerplaatst(command.getMaand(), command.getLesId(), command.getKlantId(), command.getDatumTijdStartOud(), command.getDuurOud(), command.getDatumTijdStartNieuw(), command.getDuurNieuw(), command.getLocatieId()));
    }

    @CommandHandler
    public void handle(AnnuleerLes command) {
        if (lessen.containsKey(command.getLesId())) {
            apply(new LesGeannuleerd(command.getMaand(), command.getLesId()));
        }
    }

    private void checkOverlap(LocalDateTime start, Duration duration) {
        Assert.isTrue(lessen.values().stream().noneMatch(les -> les.overlaps(start, duration)), () -> "Deze les overlapt met een andere les");
    }

    @EventSourcingHandler
    public void on(LesGepland event) {
        if (maand == null) {
            this.maand = event.getMaand();
            this.lessen = new HashMap<>();
        }
        Les les = new Les(event.getLesId(), event.getKlantId(), event.getDatumTijdStart(), event.getDuur());
        lessen.put(event.getLesId(), les);
    }

    @EventSourcingHandler
    public void on(LesVerplaatst event) {
        Les les = new Les(event.getLesId(), event.getKlantId(), event.getDatumTijdStart(), event.getDuur());
        lessen.put(event.getLesId(), les);
    }

    @EventSourcingHandler
    public void on(LesGeannuleerd event) {
        this.lessen.remove(event.getLesId());
    }
}
