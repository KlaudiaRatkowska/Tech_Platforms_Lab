import javax.persistence.*;
import lombok.*;
import java.util.*;


@Entity
public class Tower {

    public Tower(){
        mages = new ArrayList<>();
    }
    public Tower(String n, int h){
        mages = new ArrayList<>();
        name = n;
        height = h;
    }

    @Getter
    @Setter
    @Id
    private String name;
    @Getter
    @Setter
    private int height;

    @Getter
    @OneToMany(mappedBy = "tower")
    private List<Mage> mages;

    public void addToTower(Mage m) {
        mages.add(m);
    }
    public String toString() {
        return "Tower: " + "name: '" + name + ", height: " + height + ", number of mages: " + mages.size();
    }
}
