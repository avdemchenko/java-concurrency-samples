package synchronizers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample {

    private Semaphore semaphore;

    public SemaphoreExample(int slotLimit) {
        semaphore = new Semaphore(slotLimit);
    }

    boolean acquire() {
        return semaphore.tryAcquire();
    }

    void release() {
        semaphore.release();
    }

    int getAvailablePermits() {
        return semaphore.availablePermits();
    }
}
