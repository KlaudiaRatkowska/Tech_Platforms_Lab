import org.junit.*;
import java.util.Optional;


public class RepositoryTest {
    MageRepository mageRepo;

    @Before
    public void initialize(){
        mageRepo = new MageRepository();
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNonExistingObject(){
            mageRepo.delete("mage5");

    }

    @Test
    public void findNonExistingObject(){
        Optional<Mage> optionalMage = mageRepo.find("mage100");
        assert !optionalMage.isPresent();
    }

    @Test
    public void findExistingObject(){
        Mage m = new Mage("mage5", 5);
        mageRepo.save(m);
        Optional<Mage> optionalMage = mageRepo.find("mage5");
        assert optionalMage.isPresent();
        assert optionalMage.get() == m;
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveExistingKey(){
        Mage mage = new Mage("mage99", 9);
        mageRepo.save(mage);
        mageRepo.save(mage);
    }

    @Test
    public void deleteExistingObject(){
        mageRepo.save(new Mage("mage5", 5));
        mageRepo.delete("mage5");
        Optional<Mage> optionalMage = mageRepo.find("mage5");
        assert !optionalMage.isPresent();
    }
}