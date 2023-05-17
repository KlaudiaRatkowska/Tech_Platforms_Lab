import java.util.Scanner;
import java.util.Random;

public class Main {
    private static final int THREAD_COUNT = 2;
    public static void main(String[] args) throws InterruptedException {
        int threadCount = THREAD_COUNT;
        TaskResource taskResource = new TaskResource();
        ResultResource resultResource = new ResultResource();
        Random randGenerator = new Random();

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new Calculation(taskResource, resultResource));
            threads[i].start();
        }

        System.out.println("Type number of random numbers or type 0 if you want to finish: ");
        while(true)
        {
            Scanner sc = new Scanner(System.in);
            int k = sc.nextInt();
            if(k == 0)
                break;
            for(int i=0; i<k; i++)
            {
                int nb = randGenerator.nextInt(100000);
                taskResource.addTask(nb);
                Random randGenerator2 = new Random();
                int wait = randGenerator2.nextInt(250);
                Thread.sleep(wait);
            }

        }
        while(true)
        {
            if(taskResource.isCompleted())
                break;
        }
        System.out.println(" ");
        System.out.println("All generated numbers: ");
        resultResource.getResults();

        for (Thread t : threads)
            t.interrupt();

    }
}