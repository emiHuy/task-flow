import java.time.LocalDate;

public class Task implements Comparable<Task> {
    public static final byte DECREASING_PRIORITY = 0;
    public static final byte CHRONOLOGICAL = 1;
    public static byte SortStrategy = CHRONOLOGICAL;

    private String action;
    private int priority;
    private LocalDate date;
    private String category;
    private boolean completed;

    public Task(String action, int priority, LocalDate date, String category) {
        this.action = action;
        this.priority = priority;
        this.date = date;
        this.category = category;
        completed = false;
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
    public boolean isCompleted() {
        return completed;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int compareTo(Task task) {
        switch (SortStrategy) {
            case DECREASING_PRIORITY: return compareByPriority(task);
            case CHRONOLOGICAL: return compareByDate(task);
        }
        return -1;
    }

    private int compareByPriority(Task task) {
        if (priority != task.priority) {
            return task.priority - priority;
        }
        if (!date.equals(task.date)) {
            return date.compareTo(task.date);
        }
        if (!category.equals(category)) {
            return category.compareTo(task.category);
        }
        return action.compareTo(task.action);
    }

    private int compareByDate(Task task) {
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
