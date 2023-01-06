package executors;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class SingleThreadExecutorExampleTest {

    @Test
    public void shouldRunSingleThreadExecutor() {
        // given
        var singleThreadExecutorExample = new SingleThreadExecutorExample();

        // when
        var result = singleThreadExecutorExample.runSingleThreadExecutor(
                List.of("First sentence. ", "Second one.")
        );

        // then
        assertThat(result).isEqualTo("First sentence. Second one.");
    }
}