package nl.lisaveldhuisen.les.event.repository.locatie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocatieRepository extends JpaRepository<LocatieEntity, UUID> {
}
