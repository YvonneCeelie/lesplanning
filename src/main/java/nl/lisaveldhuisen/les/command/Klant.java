package nl.lisaveldhuisen.les.command;

import nl.lisaveldhuisen.les.coreapi.KlantGeregistreerd;
import nl.lisaveldhuisen.les.coreapi.KlantVerwijderd;
import nl.lisaveldhuisen.les.coreapi.RegistreerKlant;
import nl.lisaveldhuisen.les.coreapi.VerwijderKlant;
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

    @CommandHandler
    public Klant(RegistreerKlant command) {
        apply(new KlantGeregistreerd(command.getKlantId(), command.getNaam(), command.getStraat(), command.getPostCode(), command.getWoonplaats()));
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
