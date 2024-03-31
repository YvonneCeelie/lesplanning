package nl.lisaveldhuisen.les.controller;

import nl.lisaveldhuisen.les.coreapi.LocatieGegevensRequest;
import nl.lisaveldhuisen.les.coreapi.RegistreerLocatie;
import nl.lisaveldhuisen.les.coreapi.WijzigLocatie;
import nl.lisaveldhuisen.les.coreapi.VerwijderLocatie;
import nl.lisaveldhuisen.les.event.repository.locatie.LocatieEntity;
import nl.lisaveldhuisen.les.event.repository.locatie.LocatieRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class LocatieController {
    final private CommandGateway commandGateway;
    final private LocatieRepository locatieRepository;

    public LocatieController(CommandGateway commandGateway, LocatieRepository locatieRepository) {
        this.commandGateway = commandGateway;
        this.locatieRepository = locatieRepository;
    }
    @PostMapping(value = "/api/v1/locatie/")
    public CompletableFuture<Void> registreerLocatie(@RequestBody LocatieGegevensRequest request) {
        RegistreerLocatie command = new RegistreerLocatie(UUID.randomUUID(), request.getNaam(), request.getStraat(), request.getPostCode(), request.getWoonplaats(), request.getLatitude(), request.getLongitude());
        return commandGateway.send(command);
    }
    @PutMapping("/api/v1/locatie/{locatieId}")
    public CompletableFuture<Void> updateLocatie(@PathVariable UUID locatieId, LocatieGegevensRequest request) {
        WijzigLocatie command = new WijzigLocatie(locatieId, request.getNaam(), request.getStraat(), request.getPostCode(), request.getWoonplaats(), request.getLatitude(), request.getLongitude());
        return commandGateway.send(command);
    }
    @DeleteMapping("/api/v1/locatie/{locatieId}")
    public CompletableFuture<Void> verwijderLocatie(@PathVariable(value = "locatieId") UUID locatieId) {
        return commandGateway.send(new VerwijderLocatie(locatieId));
    }
    @GetMapping(value = "/api/v1/locatie/")
    public List<LocatieEntity> getLocaties(){
        return locatieRepository.findAll();
    }
    @GetMapping("/api/v1/locatie/{locatieId}")
    public LocatieEntity getLocatie(@PathVariable UUID locatieId){
        return locatieRepository.findById(locatieId).orElse(null);
    }

}
