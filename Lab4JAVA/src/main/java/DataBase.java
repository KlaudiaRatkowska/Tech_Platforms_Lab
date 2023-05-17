import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class DataBase {
    private static EntityManager entityManager;

    public DataBase(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static void createMage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type the name of the mage: ");
        String name = sc.next();
        System.out.println("Type the level of the mage: ");
        int lvl = sc.nextInt();
        sc.nextLine();
        System.out.println("Type the name of the tower: ");
        Tower tower = getTower(sc.nextLine());
        while(tower == null){
            System.out.println("Error, type the name of the tower again: ");
            tower = getTower(sc.nextLine());
        }
        Mage mage = new Mage(name, lvl, tower);
        entityManager.getTransaction().begin();
        if(entityManager.find(mage.getTower().getClass(), mage.getTower().getName()) == null)
            entityManager.persist(mage.getTower());
        entityManager.persist(mage);
        entityManager.getTransaction().commit();
        System.out.println("Mage added");
        System.out.println(" ");
    }
    public static void createTower() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type the name of the tower: ");
        String name = sc.next();
        System.out.println("Type the height of the tower: ");
        int height = sc.nextInt();
        Tower tower = new Tower(name, height);
        entityManager.getTransaction().begin();
        for(Mage mage : tower.getMages())
        {
            if(entityManager.find(mage.getClass(), mage.getName()) == null)
                entityManager.persist(mage);
        }
        entityManager.persist(tower);
        entityManager.getTransaction().commit();
        System.out.println("Tower added");
        System.out.println(" ");
    }

    public static Tower getTower(String name) {
        TypedQuery<Tower> tower = entityManager.createQuery("SELECT t FROM Tower t WHERE t.name=\'"+ name +"\'", Tower.class);
        if(tower.getResultList().size()==0)
            return null;
        return tower.getSingleResult();
    }

    public static void deleteMage(Mage mage){
        entityManager.getTransaction().begin();
        Tower tower = entityManager.find(mage.getTower().getClass(), mage.getTower().getName());
        tower.getMages().remove(mage);
        entityManager.remove(mage);
        entityManager.getTransaction().commit();
        System.out.println("Mage deleted");
        System.out.println(" ");
    }
    public static void deleteTower(Tower tower){
        entityManager.getTransaction().begin();
        for(Mage mage : tower.getMages())
        {
            if(entityManager.find(mage.getClass(), mage.getName()) != null)
                entityManager.remove(mage);
        }
        entityManager.remove(tower);
        entityManager.getTransaction().commit();
        System.out.println("Tower deleted");
        System.out.println(" ");
    }

    public static void printDatabase(){
        System.out.println("Print all towers and mages");
        for(Tower tower : entityManager.createQuery("SELECT t FROM Tower t", Tower.class).getResultList()) {
            System.out.println(tower);
        }
        for(Mage mage : entityManager.createQuery("SELECT m FROM Mage m", Mage.class).getResultList()) {
            System.out.println(mage);
        }
        System.out.println(" ");
    }

    public static void printOnLevel(int x) {
        List<Mage> mages= entityManager.createQuery("SELECT m FROM Mage m WHERE m.level > "+x, Mage.class).getResultList();
       System.out.println("Print all mages that have level above " + x);
        for(Mage mage : mages) {
            System.out.println(mage);
        }
        System.out.println(" ");
    }

    public static void printLowerTowers(int height)
    {
        List<Tower> tower = entityManager.createQuery("SELECT t FROM Tower t WHERE t.height < "+ height, Tower.class).getResultList();
       System.out.println("Print towers which are lower than " + height);
        for(Tower t : tower) {
            System.out.println(t.getName() + " height: " + t.getHeight());
        }
        System.out.println(" ");
    }

    public static void printLevelAboveOther(Tower tower1, Tower tower2){
        Mage minMage = null;
        for(Mage m1 : entityManager.createQuery("SELECT m FROM Mage m ORDER BY m.level ASC", Mage.class).getResultList()){
            if(m1.getTower() == tower1) {
                minMage = m1;
                break;
            }
        }
        int lvl = minMage.getLevel();
        List<Mage> mage2 = entityManager.createQuery("SELECT m2 FROM Mage m2 WHERE m2.level > "+ lvl, Mage.class).getResultList();
        System.out.println("Print mages from " + tower2.getName() + " who has level above minimum level of mage from " + tower1.getName());
        for(Mage m2 : mage2) {
            if(m2.getTower() == tower2)
                System.out.println(m2.getName() + " level: " + m2.getLevel());
        }
        System.out.println(" ");
    }
}
