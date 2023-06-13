import java.util.Random;

public class Strategy3 {
    public static void s3(Processor[] processors, int currentProcessorIndex, double threshold, double minThreshold) {
        Processor currentProcessor = processors[currentProcessorIndex];

        if (currentProcessor.getLoad() < minThreshold) {
            boolean taskOffloaded = false;
            Random rand = new Random();

            while (!taskOffloaded) {
                int randomProcessorIndex = rand.nextInt(processors.length);

                Processor randomProcessor = processors[randomProcessorIndex];

                if (randomProcessor.getLoad() > threshold) {
                    int tasksToOffload = randomProcessor.getTasks().size() / 2;

                    for (int i = 0; i < tasksToOffload; i++) {
                        offloadTask(randomProcessor, currentProcessor);
                    }
                    taskOffloaded = true;
                }
            }
        }
    }


    private static void offloadTask(Processor from, Processor proc) {
        if (!from.getTasks().isEmpty()) {
            Task task = from.removeTask(); // Removing last task
            proc.addTask(task);
            from.incrementTaskMigrations(1.3);
            proc.incrementTaskMigrations(1.3);
        }
    }

}



