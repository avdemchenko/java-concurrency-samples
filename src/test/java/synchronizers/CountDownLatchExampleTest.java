package synchronizers;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class CountDownLatchExampleTest {

    @Test
    public void shoudUseCountDownLatch() {
        // given
        var countDownLatchExample = new CountDownLatchExample();

        // when
        var result = countDownLatchExample.useCountDownLatch();

        // then
        assertThat(result).containsAll(
                List.of(
                        "Service in pool-1-thread-1 initialized.",
                        "Service in pool-1-thread-3 initialized.",
                        "Service in pool-1-thread-2 initialized.",
                        "All services are up and running!"
                )
        );
    }
}