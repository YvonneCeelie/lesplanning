package nl.lisaveldhuisen.les.event.repository.les;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.YearMonth;
import java.util.Set;

@Entity
public class LesPlanningEntity {

    @Id
    private YearMonth maand;
    @OneToMany(mappedBy = "maand")
    private Set<LesEntity> lessen;
}
