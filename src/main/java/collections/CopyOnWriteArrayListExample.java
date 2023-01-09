package collections;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CopyOnWriteArrayListExample {

    public static void main(String[] args) {
        runCopyOnWriteArrayList();
    }

    public static void runCopyOnWriteArrayList() {
        System.out.println("=== CopyOnWriteArrayList ===");
        var executor = Executors.newCachedThreadPool();
        var random = new Random();
        // No ConcurrentModificationException
        var copyOnWriteArrayList = new CopyOnWriteArrayList<Integer>();

        for (int i = 0; i < 100; i++) {
            if (i % 8 == 0) {
                // write
                executor.execute(() -> {
                    var value = random.nextInt(10);
                    System.err.println("Added " + value);
                    copyOnWriteArrayList.add(value);
                });
            } else {
                // read
                executor.execute(() -> {
                    var builder = new StringBuilder();
                    for (var value : copyOnWriteArrayList) {
                        builder.append(value + " ");
                    }
                    System.out.println("Reading " + builder);
                });
            }
        }

        // Finishing
        executor.shutdown();
        try {
            executor.awaitTermination(2000, TimeUnit.SECONDS);
            Thread.sleep(2000);
            System.out.println("\n\n\n\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
