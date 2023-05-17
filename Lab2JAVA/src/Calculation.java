
public class Calculation implements Runnable {
    private TaskResource taskResource;
    private ResultResource resultResource;

    public Calculation(TaskResource taskResource, ResultResource resultResource) {
        this.taskResource = taskResource;
        this.resultResource = resultResource;
    }

    @Override
    public void run() {
        try
        {
            calculate();
        }
        catch(InterruptedException ex)
        {
            if(taskResource.isCompleted())
                System.out.println("Thread was interrupted");
            else
            {
                System.out.println("Thread was not interrupted");
                ex.printStackTrace();
            }
        }
    }

    public synchronized void calculate() throws InterruptedException
    {
        while(true)
        {
            int nb = taskResource.getTask();
            boolean isPrimary = true;
            for(int i=2; i<nb; i++)
            {
                if(nb%i==0){
                    isPrimary = false;
                    break;
                }
            }

            resultResource.addResult(nb, isPrimary);

            if(isPrimary)
                System.out.println("Thread: number " + nb + " is primary");
            else
                System.out.println("Thread: number " + nb + " is not primary");
        }
    }
}