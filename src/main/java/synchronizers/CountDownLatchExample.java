package synchronizers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample {

    public List<String> useCountDownLatch() {
        var executor = Executors.newCachedThreadPool();
        var latch = new CountDownLatch(3);
        var result = new ArrayList<String>();

        Runnable task = () -> {
            try {
                Thread.sleep(1000);
                result.add("Service in " + Thread.currentThread().getName() + " initialized.");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executor.execute(task);
        executor.execute(task);
        executor.execute(task);

        try {
            latch.await(2, TimeUnit.SECONDS);
            result.add("All services are up and running!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

        return result;
    }
}
