package nl.lisaveldhuisen.les.command;

import nl.lisaveldhuisen.les.coreapi.LocatieGeregistreerd;
import nl.lisaveldhuisen.les.coreapi.LocatieVerwijderd;
import nl.lisaveldhuisen.les.coreapi.RegistreerLocatie;
import nl.lisaveldhuisen.les.coreapi.VerwijderLocatie;
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

    @CommandHandler
    public Locatie(RegistreerLocatie command) {
        AggregateLifecycle.apply(new LocatieGeregistreerd(command.getLocatieId(), command.getNaam(), command.getStraat(), command.getPostCode(), command.getWoonplaats()));
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
