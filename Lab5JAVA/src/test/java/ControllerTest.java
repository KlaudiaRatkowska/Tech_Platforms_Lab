import org.junit.*;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class ControllerTest {
    MageController mageContr;
    MageRepository mageRepo;

    @Before
    public void initialize(){
        mageRepo = mock(MageRepository.class);
        mageContr = new MageController(mageRepo);

        Mage mage99 = new Mage("mage99", 99);
        Optional<Mage> mageOptional = Optional.of(mage99);
        when(mageRepo.find("mage99")).thenReturn(mageOptional);
    }

    @Test
    public void deleteExistingObject(){
        String s = mageContr.delete("mage98");
        assert s.equals("done");
    }

    @Test(expected = AssertionError.class)
    public void deleteNonExistingObject(){
        String s = mageContr.delete("mage90");
        assert s.equals("not found");
    }

    @Test
    public void findNonExistingObject(){
        String s = mageContr.find("mage123");
        assert s.equals("not found");
    }

    @Test
    public void findExistingObject(){
        Mage mage = new Mage("mage99",99);
        String s = mageContr.find("mage99");
        assert mage.toString().equals(s);
    }

    @Test
    public void saveNonExistingKeyObject(){
        doNothing().when(mageRepo).save(isA(Mage.class));
        String s = mageContr.save("mage33", 33);
        assert s.equals("done");
    }

    @Test
    public void saveExistingKeyObject(){
        doThrow(IllegalArgumentException.class).when(mageRepo).save(isA(Mage.class));
        String s = mageContr.save("mage99", 99);
        assert s.equals("bad request");
    }
}