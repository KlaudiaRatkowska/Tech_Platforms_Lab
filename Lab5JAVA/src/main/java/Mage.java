public class Mage {

    private String name;
    private int level;

    public Mage(String name, int level){
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return ("Mage name: " + name + ", level:" + level);
    }
}