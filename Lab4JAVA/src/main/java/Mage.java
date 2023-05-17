import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "Mage")
public class Mage {

    public Mage(String name, int level, Tower tower) {
        this.name = name;
        this.level = level;
        this.tower = tower;
        tower.addToTower(this);
    }

    @Getter
    @Setter
    @Column(name = "name")
    @Id
    private String name;

    @Getter
    @Setter
    private int level;

    @ManyToOne
    @Getter
    private Tower tower;

    @Override
    public String toString() {
        return "Mage: " + "name: '" + name + ", level:" + level + ", tower:" + tower.getName();
    }
}
