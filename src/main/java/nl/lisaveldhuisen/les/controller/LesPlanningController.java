package nl.lisaveldhuisen.les.controller;

import nl.lisaveldhuisen.les.coreapi.*;
import nl.lisaveldhuisen.les.event.repository.les.LesPlanningRepository;
import nl.lisaveldhuisen.les.event.repository.les.LesPlanningEntity;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class LesPlanningController {
    final private CommandGateway commandGateway;
    final private LesPlanningRepository lesPlanningRepository;

    public LesPlanningController(CommandGateway commandGateway, LesPlanningRepository lesPlanningRepository) {
        this.commandGateway = commandGateway;
        this.lesPlanningRepository = lesPlanningRepository;
    }

    @PostMapping(value = "/api/v1/lesplanning/")
    public CompletableFuture<Void> planLes(@RequestBody PLanLesRequest request) {
        PlanLes command = new PlanLes(request.getMaand(), UUID.randomUUID(), request.getKlantId(), request.getDatumTijdStart(), request.getDuur(), request.getLocatieId());
        return commandGateway.send(command);
    }
    @PutMapping("/api/v1/{maand}/{lesId}")
    public CompletableFuture<Void> verplaatsLes(@PathVariable UUID lesId, @PathVariable YearMonth maand, VerplaatsLesRequest request) {
        VerplaatsLes command = new VerplaatsLes(maand, lesId, request.getLocatieId(), request.getDatumTijdStartOud(), request.getDuurOud(), request.getDatumTijdStartNieuw(), request.getDuurNieuw(), request.getLocatieId());
        return commandGateway.send(command);
    }
    @DeleteMapping("/api/v1/{maand}/{lesId}")
    public CompletableFuture<Void> annuleerLes(@PathVariable(value = "maand") YearMonth maand, @PathVariable(value = "lesId") UUID lesId) {
        return commandGateway.send(new AnnuleerLes(maand, lesId));
    }
    @GetMapping("/api/v1/{maand}/")
    public LesPlanningEntity getMaandPlanning(@PathVariable YearMonth maand){
        return lesPlanningRepository.findById(maand).orElse(null);
    }
}
