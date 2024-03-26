package nl.lisaveldhuisen.les.event.repository.les;

import nl.lisaveldhuisen.les.coreapi.LesEvent;
import nl.lisaveldhuisen.les.coreapi.LesGeannuleerd;
import nl.lisaveldhuisen.les.coreapi.LesGepland;
import nl.lisaveldhuisen.les.coreapi.LesVerplaatst;
import nl.lisaveldhuisen.les.event.repository.les.LesEntity;
import nl.lisaveldhuisen.les.event.repository.les.LesPlanningEntity;
import nl.lisaveldhuisen.les.event.repository.les.LesPlanningRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ProcessingGroup("lesplanningProcessor")
@Component
public class LesPlanningEventHandler {
    private final LesPlanningRepository lesPlanningRepository;

    public LesPlanningEventHandler(LesPlanningRepository lesPlanningRepository) {
        this.lesPlanningRepository = lesPlanningRepository;
    }

    @EventHandler
    public void on(LesGepland event) {
        lesPlanningRepository.findById(event.getMaand())
                .ifPresentOrElse(lesPlanningEntity -> addLesToLesPlanning(lesPlanningEntity, event),
                        () -> addLesToLesPlanning(new LesPlanningEntity(event.getMaand()), event));
    }

    @EventHandler
    public void on(LesVerplaatst event) {
        lesPlanningRepository.findById(event.getMaand())
                .ifPresent(lesPlanningEntity -> removeLesFromLesPlanning(lesPlanningEntity, event.getLesId()));
        lesPlanningRepository.findById(event.getMaand())
                .ifPresent(lesPlanningEntity -> addLesToLesPlanning(lesPlanningEntity, event));
    }

    @EventHandler
    public void on(LesGeannuleerd event) {
        lesPlanningRepository.findById(event.getMaand())
                .ifPresent(lesPlanningEntity -> removeLesFromLesPlanning(lesPlanningEntity, event.getLesId()));
    }


    private void addLesToLesPlanning(LesPlanningEntity lesPlanningEntity, LesEvent event) {
        Set<LesEntity> lessen = lesPlanningEntity.getLessen();
        lessen.add(new LesEntity(event.getLesId(), event.getMaand(), event.getDatumTijdStart(),
                event.getDatumTijdStart().plusMinutes(event.getDuur().toMinutes()), event.getKlantId(), event.getLocatieId()));
    }

    private void removeLesFromLesPlanning(LesPlanningEntity lesPlanningEntity, UUID lesId) {
        Set<LesEntity> lessen = lesPlanningEntity.getLessen();
        Optional<LesEntity> lesEntityOptional = lessen.stream().filter(lesEntity -> Objects.equals(lesEntity.getId(), lesId)).findFirst();
        lesEntityOptional.map(lessen::remove);
    }
}
