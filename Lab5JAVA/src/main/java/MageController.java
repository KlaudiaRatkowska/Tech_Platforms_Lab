import java.util.Optional;

public class MageController {

    private MageRepository repository;

    public MageController(MageRepository repository) {
        this.repository = repository;
    }

    public String find(String name) {
        Optional<Mage> mageOptional = repository.find(name);
        if(!mageOptional.isPresent())
            return "not found";
        return mageOptional.get().toString();
    }

    public String delete(String name) {
        try {
            repository.delete(name);
        }
        catch (IllegalArgumentException ex) {
            return "not found";
        }
        return "done";
    }

    public String save(String name, int level) {
        try {
            repository.save(new Mage(name, level));
        }
        catch (IllegalArgumentException ex) {
            return "bad request";
        }
        return "done";
    }
}