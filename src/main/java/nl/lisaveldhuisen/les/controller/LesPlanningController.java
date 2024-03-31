package nl.lisaveldhuisen.les.controller;

import nl.lisaveldhuisen.les.coreapi.*;
import nl.lisaveldhuisen.les.event.repository.les.LesEntity;
import nl.lisaveldhuisen.les.event.repository.les.LesRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class LesPlanningController {
    final private CommandGateway commandGateway;
    final private LesRepository lesRepository;

    public LesPlanningController(CommandGateway commandGateway, LesRepository lesRepository) {
        this.commandGateway = commandGateway;
        this.lesRepository = lesRepository;
    }

    @PostMapping(value = "/api/v1/les/")
    public CompletableFuture<Void> planLes(@RequestBody PLanLesRequest request) {
        YearMonth yearMonth = YearMonth.of(request.getDatumTijdStart().getYear(), request.getDatumTijdStart().getMonth());
        Duration duur = Duration.between(request.getDatumTijdStart(), request.getDatumTijdEinde());
        PlanLes command = new PlanLes(yearMonth, UUID.randomUUID(), request.getKlantId(), request.getDatumTijdStart(), duur, request.getLocatieId());
        return commandGateway.send(command);
    }

    @PutMapping("/api/v1/{lesId}")
    public CompletableFuture<Void> verplaatsLes(@PathVariable UUID lesId, VerplaatsLesRequest request) {
        YearMonth yearMonthNieuw = YearMonth.of(request.getDatumTijdStartNieuw().getYear(), request.getDatumTijdStartNieuw().getMonth());
        Duration duurNieuw = Duration.between(request.getDatumTijdStartNieuw(), request.getDatumTijdEindeNieuw());
        Duration duurOud = Duration.between(request.getDatumTijdStartOud(), request.getDatumTijdEindeOud());
        VerplaatsLes verplaatsLes = new VerplaatsLes(yearMonthNieuw, lesId, request.getLocatieId(), request.getDatumTijdStartOud(), duurOud, request.getDatumTijdStartNieuw(), duurNieuw, request.getLocatieId());
        commandGateway.sendAndWait(verplaatsLes);
        YearMonth yearMonthOud = YearMonth.of(request.getDatumTijdStartOud().getYear(), request.getDatumTijdStartOud().getMonth());
        AnnuleerLes annuleerLes = new AnnuleerLes(yearMonthOud, lesId);
        return commandGateway.send(annuleerLes);
    }

    @DeleteMapping("/api/v1/{maand}/{lesId}")
    public CompletableFuture<Void> annuleerLes(@PathVariable(value = "maand") YearMonth maand, @PathVariable(value = "lesId") UUID lesId) {
        return commandGateway.send(new AnnuleerLes(maand, lesId));
    }

    @GetMapping("/api/v1/{maand}/{jaar}")
    public List<LesEntity> getMaandPlanning(@PathVariable int maand, @PathVariable int jaar) {
        YearMonth yearMonth = YearMonth.of(jaar,maand);
        return lesRepository.findByMaand(yearMonth);
    }
}