import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;

public class TaskCollection {
    private TreeSet<Task> tasks;
    private int tasksCompleted;
    private TreeSet<String> categories;

    public TaskCollection() {
        tasks = new TreeSet<Task>();
        tasksCompleted = 0;
        categories = new TreeSet<String>();
        categories.add("Miscellaneous");
    }

    public TreeSet<Task> getTasks() {
        return tasks;
    }
    public int getTasksCompleted() {
        return tasksCompleted;
    }
    public TreeSet<String> getCategories() {
        return categories;
    }

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

    public boolean addCategory(String category) {
        return categories.add(category);
    }

    public ArrayList<Task> getTasksByDate(LocalDate date) {
        ArrayList<Task> tasksOnDate = new ArrayList<Task>();
        for (Task t: tasks) {
            if (t.getDate().equals(date)) {
                tasksOnDate.add(t);
            }
        }
        return tasksOnDate;
    }

    public ArrayList<Task> getTasksByPriority(int priority) {
        ArrayList<Task> tasksWithPriority= new ArrayList<Task>();
        for (Task t: tasks) {
            if (t.getPriority() == priority) {
                tasksWithPriority.add(t);
            }
        }
        return tasksWithPriority;
    }

    public ArrayList<Task> getTasksByCategory(String category) {
        ArrayList<Task> tasksInCategory= new ArrayList<Task>();
        for (Task t: tasks) {
            if (t.getCategory().equals(category)) {
                tasksInCategory.add(t);
            }
        }
        return tasksInCategory;
    }
}
