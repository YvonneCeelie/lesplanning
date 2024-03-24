package nl.lisaveldhuisen.les.event.repository.klant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KlantRepository extends JpaRepository<KlantEntity, UUID> {
}
