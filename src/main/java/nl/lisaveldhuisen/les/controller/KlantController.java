package nl.lisaveldhuisen.les.controller;

import nl.lisaveldhuisen.les.coreapi.KlantGegevensRequest;
import nl.lisaveldhuisen.les.coreapi.RegistreerKlant;
import nl.lisaveldhuisen.les.coreapi.VerwijderKlant;
import nl.lisaveldhuisen.les.coreapi.WijzigKlant;
import nl.lisaveldhuisen.les.event.repository.klant.KlantEntity;
import nl.lisaveldhuisen.les.event.repository.klant.KlantRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class KlantController {
    final private CommandGateway commandGateway;
    final private KlantRepository klantRepository;

    public KlantController(CommandGateway commandGateway, KlantRepository klantRepository) {
        this.commandGateway = commandGateway;
        this.klantRepository = klantRepository;
    }
    @PostMapping(value = "/api/v1/klanten")
    public CompletableFuture<Void> registreerKlant(@RequestBody KlantGegevensRequest request) {
        RegistreerKlant command = new RegistreerKlant(UUID.randomUUID(), request.getNaam(), request.getStraat(), request.getPostCode(), request.getWoonplaats(), request.getEmail());
        return commandGateway.send(command);
    }
    @PutMapping("/api/v1/klanten/{klantId}")
    public CompletableFuture<Void> updateKlant(@PathVariable UUID klantId, KlantGegevensRequest request) {
        WijzigKlant command = new WijzigKlant(klantId, request.getNaam(), request.getStraat(), request.getPostCode(), request.getWoonplaats(), request.getEmail());
        return commandGateway.send(command);
    }
    @DeleteMapping("api/v1/klanten/{klantId}")
    public CompletableFuture<Void> verwijderKlant(@PathVariable UUID klantId) {
        return commandGateway.send(new VerwijderKlant(klantId));
    }
    @GetMapping("/api/v1/klanten")
    public List<KlantEntity> getKlanten(){
        return klantRepository.findAll();
    }
    @GetMapping("/api/v1/klanten/{klantId}")
    public KlantEntity getKlant(@PathVariable UUID klantId){
        return klantRepository.findById(klantId).orElse(null);
    }
}
