
package nl.lisaveldhuisen.les.event.repository.les;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

public interface LesRepository extends JpaRepository<LesEntity, UUID> {
    List<LesEntity> findByMaand(YearMonth month);
}
