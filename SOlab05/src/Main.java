import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int processorNumber = 10;
        double threshold = 0.75;
        double minThreshold = 0.2;
        int maxAttempts = 10;
        int totalTasks = 2000;

        Processor[] processors = new Processor[processorNumber];
        for (int i = 0; i < processorNumber; i++) {
            processors[i] = new Processor();
        }

        generateTasks(processors, processorNumber, totalTasks);

        System.out.println("Simulating with parameters:");
        System.out.println("Number of processors: " + processorNumber);
        System.out.println("Threshold: " + threshold);
        System.out.println("min Threshold: " + minThreshold);
        System.out.println("Number of max attempts finding less loaded processor: " + maxAttempts);
        System.out.println("Number od total tasks: " + totalTasks);
        System.out.println();


        System.out.println("Strategy 1:");
        for (int i = 0; i < processors.length; i++) {
            Strategy1.s1(processors, i, threshold, maxAttempts);
        }
        Results.calculateAndPrint(processors);
        resetProcessors(processors);

        generateTasks(processors, processorNumber, totalTasks);

        System.out.println();
        System.out.println("Strategy 2:");
        for (int i = 0; i < processors.length; i++) {
            Strategy2.s2(processors, i, threshold);
        }
        Results.calculateAndPrint(processors);
        resetProcessors(processors);

        generateTasks(processors, processorNumber, totalTasks);

        System.out.println();
        System.out.println("Strategy 3:");
        for (int i = 0; i < processors.length; i++) {
            Strategy3.s3(processors, i, threshold, minThreshold);
        }
        Results.calculateAndPrint(processors);
    }

    private static void resetProcessors(Processor[] processors) {
        for (Processor processor : processors) {
            processor.clearTasks();
        }
    }

    private static void generateTasks(Processor[] processors, int N, int funcTotalTasks) {
        Random rand = new Random();
        for (int i = 0; i < funcTotalTasks; i++) {
            int processorIndex = rand.nextInt(N);
            double taskLoad = 1 + (3 - 1) * rand.nextDouble();
            Task task = new Task(taskLoad);
            processors[processorIndex].addTask(task);
        }
    }
}






