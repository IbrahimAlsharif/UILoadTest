package UILoadTest;

import org.testng.annotations.Test;

import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTest {


//    public static void main(String[] args) throws InterruptedException {
    @Test
    public void startLoad(){
        int numberOfUsersPerSession = 10;
        String[] listOfSessionsUrls = new String[]{
                "https://bbb.famcare.app/b/ahm-hb6-gsm-akr",
                "https://bbb.famcare.app/b/ahm-rbq-u3t-jcm",
                "https://bbb.famcare.app/b/ahm-blk-xne-0tj",
                "https://bbb.famcare.app/b/ahm-yro-5km-60r",
                "https://bbb.famcare.app/b/ahm-zky-pbt-xjq"
        };
        ExecutorService executor = Executors.newFixedThreadPool(listOfSessionsUrls.length);

        for(String sessionUrl: listOfSessionsUrls){
        executor.execute(new UsersGenerator(sessionUrl, numberOfUsersPerSession));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
