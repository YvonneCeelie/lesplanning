package nl.lisaveldhuisen.les.command;

import nl.lisaveldhuisen.les.coreapi.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;
@Aggregate
public class Locatie {
    @AggregateIdentifier
    private UUID locatieId;

    public Locatie() {
    }
    @CommandHandler
    public Locatie(RegistreerLocatie command) {
        AggregateLifecycle.apply(new LocatieGeregistreerd(command.getLocatieId(), command.getNaam(), command.getStraat(), command.getPostCode(), command.getWoonplaats(), command.getLatitude(), command.getLongitude()));
    }
    @CommandHandler
    public void handle(WijzigKlant command){
        AggregateLifecycle.apply(new KlantGewijzigd(command.getKlantId(), command.getNaam(), command.getStraat(), command.getPostCode(), command.getWoonplaats(), command.getEmail()));
    }

    @CommandHandler
    public void handle(VerwijderLocatie command){
        AggregateLifecycle.apply(new LocatieVerwijderd(command.getLocatieId()));
    }

    @EventSourcingHandler
    public void on(LocatieGeregistreerd event){
        this.locatieId = event.getLocatieId();
    }
}
