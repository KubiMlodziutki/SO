import java.util.Arrays;
public class Results {
    public static void calculateAndPrint(Processor[] processors) {
        double totalLoad = Arrays.stream(processors).mapToDouble(Processor::getLoad).sum()*0.01;
        double avgLoad = totalLoad / processors.length;
        double deviation = Arrays.stream(processors)
                .mapToDouble(p -> Math.abs(p.getLoad() - avgLoad)).average().orElse(0)*0.01;

        int totalLoadInquiries = 0;
        int totalTaskMigrations = 0;
        for (Processor processor : processors) {
            totalLoadInquiries += processor.getLoadQueries();
            totalTaskMigrations = processor.getTaskMigrations();
            processor.clearLoadQueries();
            processor.clearTaskMigrations();
        }

        System.out.println("Total task migrations: " + totalTaskMigrations);
        System.out.println("Total load inquiries: " + totalLoadInquiries);
        System.out.println("Average load: " + avgLoad);
        System.out.println("Average deviation: " + deviation);
    }

}
