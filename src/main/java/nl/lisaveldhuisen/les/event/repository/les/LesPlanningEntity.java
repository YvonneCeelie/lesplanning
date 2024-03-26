package nl.lisaveldhuisen.les.event.repository.les;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.YearMonth;
import java.util.Set;

@Entity
public class LesPlanningEntity {

    public LesPlanningEntity(YearMonth maand) {
        this.maand = maand;
        this.lessen = Set.of();
    }

    @Id
    private YearMonth maand;
    @OneToMany(mappedBy = "maand")

    private Set<LesEntity> lessen;

    public YearMonth getMaand() {
        return maand;
    }

    public void setMaand(YearMonth maand) {
        this.maand = maand;
    }

    public Set<LesEntity> getLessen() {
        return lessen;
    }

    public void setLessen(Set<LesEntity> lessen) {
        this.lessen = lessen;
    }
}
