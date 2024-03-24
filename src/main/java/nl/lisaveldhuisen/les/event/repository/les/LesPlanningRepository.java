package nl.lisaveldhuisen.les.event.repository.les;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;

public interface LesPlanningRepository extends JpaRepository<LesPlanningEntity, YearMonth> {
}
