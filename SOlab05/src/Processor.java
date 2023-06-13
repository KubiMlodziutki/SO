import java.util.ArrayList;
import java.util.List;

public class Processor {
    private final List<Task> tasks;
    private int loadInquiries = 0;
    private int taskMigrations = 0;

    public Processor() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        taskMigrations++;
    }

    public double getLoad() {
        loadInquiries++;
        return tasks.stream().mapToDouble(Task::getLoad).sum();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task removeTask() {
        if (!tasks.isEmpty()) {
            taskMigrations++;
            return tasks.remove(tasks.size() - 1); // Removing last task
        }

        return null;
    }

    public void clearTasks() {
        this.tasks.clear();
    }
    public int getLoadQueries() {
        return loadInquiries;
    }

    public int getTaskMigrations() {
        return taskMigrations;
    }
    public void clearLoadQueries() {
        loadInquiries = 0;
    }

    public void clearTaskMigrations() {
        taskMigrations = 0;
    }
    public void incrementTaskMigrations() {
        taskMigrations++;
    }
    public void incrementTaskMigrations(double increment) {
        this.taskMigrations += increment;
    }

}