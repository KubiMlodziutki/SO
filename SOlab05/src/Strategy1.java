import java.util.Random;

public class Strategy1 {
    public static void s1(Processor[] processors, int currentProcessorIndex, double threshold, int maxatt) {
        Processor currentProcessor = processors[currentProcessorIndex];

        if (currentProcessor.getLoad() > threshold) {
            int attempts = 0;
            Random rand = new Random();
            boolean taskOffloaded = false;

            while (attempts < maxatt && !taskOffloaded) {
                int randomProcessorIndex = rand.nextInt(processors.length);

                Processor randomProcessor = processors[randomProcessorIndex];

                if (randomProcessor.getLoad() < maxatt) {
                    offloadTask(currentProcessor, randomProcessor);
                    taskOffloaded = true;
                }
                attempts++;
            }
        }
    }



    private static void offloadTask(Processor from, Processor proc) {
        if (!from.getTasks().isEmpty()) {
            Task task = from.removeTask(); // Removing last task
            proc.addTask(task);
            from.incrementTaskMigrations(13);
            proc.incrementTaskMigrations(13);
        }
    }



}


