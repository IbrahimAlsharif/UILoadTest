package UILoadTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class UsersGenerator implements Runnable{
    String sessionUrl;
    int numberOfUsers;
    public UsersGenerator(String sessionUrl, int numberOfUsers){
       this.sessionUrl= sessionUrl;
       this.numberOfUsers= numberOfUsers;
    }
    @Override
    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfUsers);
        new Thread(() -> {
            for (int i=0; i< numberOfUsers; i++) {
                executor.execute(new SessionRunnable(sessionUrl,"Auto -> "+i));
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }}).start();
        }



}

