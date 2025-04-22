import java.util.TreeSet;

public class TaskCollection {
    private TreeSet<Task> tasks;
    private int tasksCompleted;

    public TaskCollection() {
        tasks = new TreeSet<Task>();
        tasksCompleted = 0;
    }

    public TreeSet<Task> getTasks() {
        return new TreeSet<Task>(tasks);
    }
    public int getTasksCompleted() {
        return tasksCompleted;}

    public boolean addTask(Task task) {
        if (tasks.add(task)) {
            return true;
        }
        return false;
    }

    public boolean deleteTask(Task task) {
        if (tasks.contains(task)) {
            tasks.remove(task);
            return true;
        }
        return false;
    }

    public void completeTask(Task t) {
        t.setCompleted(true);
        tasksCompleted++;
    }

    public void undoCompleteTask(Task t) {
        t.setCompleted(false);
        tasksCompleted--;
    }

    public void editTaskDetails(Task t, String action, int priority) {
        t.setAction(action);
        t.setPriority(priority);
    }

    public static TaskCollection example() {
        TaskCollection taskCollection = new TaskCollection();
        taskCollection.addTask(new Task("Study", 1));
        return taskCollection;
    }
}
