package nl.lisaveldhuisen.les.event.repository.klant;

import nl.lisaveldhuisen.les.coreapi.KlantGeregistreerd;
import nl.lisaveldhuisen.les.coreapi.KlantGewijzigd;
import nl.lisaveldhuisen.les.coreapi.KlantVerwijderd;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@ProcessingGroup("klantProcessor")
@Component
public class KlantEventHandler {
    private final KlantRepository klantRepository;

    public KlantEventHandler(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    @EventHandler
    public void on(KlantGeregistreerd event) {
        klantRepository.save(new KlantEntity(event.getKlantId(), event.getNaam(), event.getStraat(), event.getPostCode(), event.getWoonplaats(), event.getEmail()));
    }

    @EventHandler
    public void on(KlantGewijzigd event) {
        klantRepository.findById(event.getKlantId()).ifPresent(klantEntity -> {
                    klantEntity.setNaam(event.getNaam());
                    klantEntity.setStraat(event.getStraat());
                    klantEntity.setWoonplaats(event.getWoonplaats());
                    klantEntity.setEmail(event.getEmail());
                    klantRepository.save(klantEntity);
                }
        );
    }
    @EventHandler
    public void on(KlantVerwijderd event) {
        klantRepository.deleteById(event.getKlantId());
    }

}
