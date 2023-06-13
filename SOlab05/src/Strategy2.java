import java.util.Arrays;
import java.util.Random;

public class Strategy2 {
    public static void s2(Processor[] processors, int currentProcessorIndex, double threshold) {
        Processor currentProcessor = processors[currentProcessorIndex];

        if (currentProcessor.getLoad() > threshold) {
            boolean taskOffloaded = false;
            Random rand = new Random();

            boolean isProcessorWithLessLoad = Arrays.stream(processors).anyMatch(proc -> proc.getLoad() < threshold);

            while (!taskOffloaded && isProcessorWithLessLoad) {
                int randomProcessorIndex = rand.nextInt(processors.length);

                Processor randomProcessor = processors[randomProcessorIndex];

                if (randomProcessor.getLoad() < threshold) {
                    offloadTask(currentProcessor, randomProcessor);
                    taskOffloaded = true;
                }
            }
        }
    }


    private static void offloadTask(Processor from, Processor proc) {
        if (!from.getTasks().isEmpty()) {
            Task task = from.removeTask(); // Removing last task
            proc.addTask(task);
            from.incrementTaskMigrations(1.2);
            proc.incrementTaskMigrations(1.2);
        }
    }

}


