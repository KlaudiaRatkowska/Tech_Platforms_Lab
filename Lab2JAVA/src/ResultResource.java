import java.util.HashMap;

public class ResultResource {
    private HashMap<Integer, Boolean> res;

    public ResultResource()
    {
        res = new HashMap<>();
    }

    public synchronized void addResult(Integer number, Boolean is)
    {
        res.put(number,is);
    }

    public void getResults()
    {
        res.forEach((k, v) -> {
            if(v)
                System.out.println("Number " + k + " is primary");
            else
                System.out.println("Number " + k + " is not primary");
        });
    }

}