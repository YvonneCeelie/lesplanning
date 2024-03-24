package nl.lisaveldhuisen.les.event.repository.les;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.YearMonth;
import java.util.Set;

@Entity
public class LesPlanningEntity {

    @Id
    private YearMonth maand;
    @OneToMany(mappedBy = "maand")
    private Set<LesEntity> lessen;
}
