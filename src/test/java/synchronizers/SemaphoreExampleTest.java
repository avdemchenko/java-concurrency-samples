package synchronizers;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.assertThat;

class SemaphoreExampleTest {

    @Test
    public void verifySemaphore() {
        // given
        int slots = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(slots);

        // and
        SemaphoreExample semaphoreExample = new SemaphoreExample(slots);

        // when
        IntStream.range(0, slots)
                .forEach(user -> executorService.execute(semaphoreExample::acquire));

        // and
        executorService.shutdown();

        // then
        assertThat(semaphoreExample.getAvailablePermits()).isEqualTo(0);
    }

    @Test
    public void verifySemaphoreRelease() {
        // given
        int slots = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(slots);

        // and
        SemaphoreExample semaphoreExample = new SemaphoreExample(slots);

        // when
        IntStream.range(0, slots)
                .forEach(user -> executorService.execute(semaphoreExample::acquire));

        // and
        executorService.shutdown();
        semaphoreExample.release();

        // then
        assertThat(semaphoreExample.getAvailablePermits()).isGreaterThan(0);
        assertThat(semaphoreExample.acquire()).isTrue();
    }
}