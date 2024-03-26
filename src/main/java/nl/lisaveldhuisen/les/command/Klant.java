package nl.lisaveldhuisen.les.command;

import nl.lisaveldhuisen.les.coreapi.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Aggregate
public class Klant {
    @AggregateIdentifier
    private UUID klantId;

    public Klant() {
        // Needed for Axon
    }

    @CommandHandler
    public Klant(RegistreerKlant command) {
        apply(new KlantGeregistreerd(command.getKlantId(), command.getNaam(), command.getStraat(), command.getPostCode(), command.getWoonplaats(), command.getEmail()));
    }
    @CommandHandler
    public void handle(WijzigKlant command) {
        apply(new KlantGewijzigd(command.getKlantId(), command.getNaam(), command.getStraat(), command.getPostCode(), command.getWoonplaats(), command.getEmail()));
    }
    @CommandHandler
    public void handle(VerwijderKlant command) {
        apply(new KlantVerwijderd(command.getKlantId()));
    }

    @EventSourcingHandler
    public void on(KlantGeregistreerd event) {
        this.klantId = event.getKlantId();
    }

    @EventSourcingHandler
    public void on(KlantVerwijderd event) {
        AggregateLifecycle.markDeleted();
    }
}
