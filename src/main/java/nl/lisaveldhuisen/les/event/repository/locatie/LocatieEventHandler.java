package nl.lisaveldhuisen.les.event.repository.locatie;

import nl.lisaveldhuisen.les.coreapi.*;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@ProcessingGroup("locatieProcessor")
@Component
public class LocatieEventHandler {
    private final LocatieRepository locatieRepository;

    public LocatieEventHandler(LocatieRepository locatieRepository) {
        this.locatieRepository = locatieRepository;
    }

    @EventHandler
    public void on(LocatieGeregistreerd event) {
        locatieRepository.save(new LocatieEntity(event.getLocatieId(), event.getNaam(), event.getStraat(), event.getPostCode(), event.getWoonplaats(), event.getLatitude(), event.getLongitude()));
    }

    @EventHandler
    public void on(LocatieGewijzigd event) {
        locatieRepository.findById(event.getLocatieId()).ifPresent(locatieEntity -> {
                    locatieEntity.setNaam(event.getNaam());
                    locatieEntity.setStraat(event.getStraat());
                    locatieEntity.setPostCode(event.getPostCode());
                    locatieEntity.setWoonplaats(event.getWoonplaats());
                    locatieEntity.setLatitude(event.getLatitude());
                    locatieEntity.setLongitude(event.getLongitude());
                    locatieRepository.save(locatieEntity);
                }
        );
    }
    @EventHandler
    public void on(LocatieVerwijderd event) {
        locatieRepository.deleteById(event.getLocatieId());
    }

}
