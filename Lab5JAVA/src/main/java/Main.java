import java.util.Optional;

public class Main {

    public static void main(String args[]){

        MageRepository mageRepo = new MageRepository();
        MageController mageContr = new MageController(mageRepo);

        System.out.println("Deleting non existing mage: " + mageContr.delete("mage2"));
        System.out.println("Saving mage: " + mageContr.save("mage1",1));
        System.out.println("Deleting existing mage: " + mageContr.delete("mage1"));
        System.out.println("Saving non existing mage: " + mageContr.save("mage2",2));
        System.out.println("Saving existing mage: " + mageContr.save("mage2", 2));
        System.out.println("Finding existing mage: " + mageContr.find("mage2"));
        System.out.println("Finding non existing mage: " + mageContr.find("mage4"));
        System.out.println(" ");

        mageRepo.save(new Mage("mage12", 12));
        mageRepo.save(new Mage("mage22",22));

        //saving existing object throws exception
        // mageRepo.save(new Mage("mage12", 12));

        mageRepo.delete("mage12");

        //deleting existing object throws exception
        // mageRepo.delete("mage13");

        //finding existing object returns object with content
        Optional op1 = mageRepo.find("mage22");
        System.out.println(op1);

        //finding non existing object returns empty object
        Optional op2 = mageRepo.find("mage100");
        System.out.println(op2);
    }
}