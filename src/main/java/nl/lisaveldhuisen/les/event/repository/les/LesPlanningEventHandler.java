package nl.lisaveldhuisen.les.event.repository.les;

import nl.lisaveldhuisen.les.coreapi.LesGeannuleerd;
import nl.lisaveldhuisen.les.coreapi.LesGepland;
import nl.lisaveldhuisen.les.coreapi.LesVerplaatst;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@ProcessingGroup("lesplanningProcessor")
@Component
public class LesPlanningEventHandler {

    private final LesRepository lesRepository;

    public LesPlanningEventHandler(LesRepository lesRepository) {
        this.lesRepository = lesRepository;
    }

    @EventHandler
    public void on(LesGepland event) {
        lesRepository.save(new LesEntity(event.getLesId(), event.getMaand(), event.getDatumTijdStart(),
                event.getDatumTijdStart().plusMinutes(event.getDuur().toMinutes()), event.getKlantId(), event.getLocatieId()));
    }

    @EventHandler
    public void on(LesVerplaatst event) {
        lesRepository.deleteById(event.getLesId());
        lesRepository.save(new LesEntity(event.getLesId(), event.getMaand(), event.getDatumTijdStart(),
                event.getDatumTijdStart().plusMinutes(event.getDuur().toMinutes()), event.getKlantId(), event.getLocatieId()));
    }

    @EventHandler

    public void on(LesGeannuleerd event) {
        lesRepository.deleteById(event.getLesId());
    }
}
