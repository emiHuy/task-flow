public class Task implements Comparable<Task> {
    private String action;
    private int priority;
    private boolean completed;

    public Task(String action, int priority) {
        this.action = action;
        this.priority = priority;
        completed = false;
    }

    public String getAction() {
        return action;
    }
    public int getPriority() {
        return priority;
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
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int compareTo(Task task) {
        if (priority != task.priority) {
            return priority - task.priority;
        }
        return action.compareTo(task.action);
    }
}
