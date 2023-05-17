import javax.persistence.*;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        DataBase db = new DataBase(entityManager);

        Tower tower1 = new Tower("Tower1", 100);
        Tower tower2 = new Tower("Tower2", 200);
        Tower tower3 = new Tower("Tower3", 300);

        Mage mage1 = new Mage("Mage1", 1, tower1);
        Mage mage2 = new Mage("Mage2", 2, tower2);
        Mage mage3 = new Mage("Mage3", 3, tower1);
        Mage mage4 = new Mage("Mage4", 5, tower3);
        Mage mage5 = new Mage("Mage5", 1, tower3);
        Mage mage6 = new Mage("Mage6", 1, tower2);

        entityManager.getTransaction().begin();
        entityManager.persist(mage1);
        entityManager.persist(mage2);
        entityManager.persist(mage3);
        entityManager.persist(mage4);
        entityManager.persist(mage5);
        entityManager.persist(mage6);
        entityManager.persist(tower1);
        entityManager.persist(tower2);
        entityManager.persist(tower3);
        entityManager.getTransaction().commit();

        Scanner sc = new Scanner(System.in);
        int action;
        while(true)
        {
            System.out.println("Choose action: ");
            System.out.println("1 - create new tower");
            System.out.println("2 - create new mage");
            System.out.println("3 - delete tower x");
            System.out.println("4 - delete mage x");
            System.out.println("5 - print mages with level above x");
            System.out.println("6 - print towers lower than x");
            System.out.println("7 - print all mages and towers");
            System.out.println("8 - print mages from tower x that have lvl above min level of mages from tower y");
            System.out.println("9 - exit");
            action = sc.nextInt();

            if(action == 1)
                db.createTower();
            else if(action == 2)
                db.createMage();
            else if(action == 3)
                db.deleteTower(tower3);
            else if(action == 4)
                db.deleteMage(mage2);
            else if(action == 5)
                db.printOnLevel(1);
            else if(action == 6)
                db.printLowerTowers(400);
            else if(action == 7)
                db.printDatabase();
            else if(action == 8)
                db.printLevelAboveOther(tower1, tower2);
            else if(action == 9)
                break;
        }
    }
}
