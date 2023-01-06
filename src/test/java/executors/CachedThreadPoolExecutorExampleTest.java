package executors;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CachedThreadPoolExecutorExampleTest {

    @Test
    public void shouldRunSingleThreadExecutor() {
        // given
        var cachedThreadPoolExecutorExample = new CachedThreadPoolExecutorExample();

        // when
        var result = cachedThreadPoolExecutorExample.runCachedThreadPoolExecutor(
                List.of("Task 1", "Task 2", "Task 3")
        );

        // then
        assertThat(result).contains(
                List.of("Task 1 Thread: pool-1-thread-1",
                        "Task 2 Thread: pool-1-thread-2",
                        "Task 3 Thread: pool-1-thread-3 "
                )
        );
    }
}
