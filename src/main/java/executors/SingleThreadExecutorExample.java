package executors;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutorExample {

    public String runSingleThreadExecutor(List<String> sentences) {
        var resultString = new StringBuilder();
        var singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (String sentence : sentences) {
            singleThreadExecutor.execute(() -> resultString.append(sentence));
        }
        singleThreadExecutor.shutdown();
        try {
            singleThreadExecutor.awaitTermination(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultString.toString();
    }
}
