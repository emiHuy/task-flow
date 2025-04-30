import java.time.LocalDate;

public class Task implements Comparable<Task> {
    private String action;
    private int priority;
    private LocalDate date;
    private String category;

    public Task(String action, int priority, LocalDate date, String category) {
        this.action = action;
        this.priority = priority;
        this.date = date;
        this.category = category;
    }

    public String getAction() {
        return action;
    }
    public int getPriority() {
        return priority;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getCategory() {
        return category;
    }

    public int compareTo(Task task) {
        if (!date.equals(task.date)) {
            return date.compareTo(task.date);
        }
        if (priority != task.priority) {
            return priority - task.priority;
        }
        if (!category.equals(category)) {
            return category.compareTo(task.category);
        }
        return action.compareTo(task.action);
    }
}
