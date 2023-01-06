package executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedThreadPoolExecutorExample {

    public String runCachedThreadPoolExecutor(List<String> tasks) {
        var cachedThreadPool = Executors.newCachedThreadPool();
        var resultString = new StringBuilder();
        var taskList = new ArrayList<Runnable>();

        for (String task : tasks) {
            taskList.add(() -> {
                        resultString
                                .append(task)
                                .append(" ")
                                .append("Thread: ")
                                .append(Thread.currentThread().getName())
                                .append(" ");
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException ex) {
                            throw new IllegalStateException(ex);
                        }
                    }
            );
        }

        taskList.forEach(cachedThreadPool::execute);

        try {
            cachedThreadPool.awaitTermination(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resultString.toString();
    }
}
