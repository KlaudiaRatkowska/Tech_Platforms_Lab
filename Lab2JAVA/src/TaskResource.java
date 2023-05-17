import java.util.LinkedList;

public class TaskResource {

    private LinkedList<Integer> nb;

    public TaskResource()
    {
        nb = new LinkedList<>();
    }

    public synchronized void addTask(int task)
    {
        nb.add(task);
        notifyAll();
    }

    public synchronized int getTask() throws InterruptedException
    {
        while(nb.isEmpty())
            wait();


        int n = nb.getFirst();
        return nb.remove(0);
    }

    public boolean isCompleted()
    {
        return nb.isEmpty();
    }

}