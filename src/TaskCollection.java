import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;

public class TaskCollection implements java.io.Serializable{
    private TreeSet<Task> tasks;
    private TreeSet<String> categories;

    public TaskCollection() {
        tasks = new TreeSet<Task>();
        categories = new TreeSet<String>();
        categories.add("Miscellaneous");
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<Task>(tasks);
    }
    public TreeSet<String> getCategories() {
        return categories;
    }

    public ArrayList<Task> getOverdueTasks() {
        ArrayList<Task> overdueTasks = new ArrayList<Task>();
        for (Task t: tasks) {
            if (t.getDate().compareTo(LocalDate.now()) < 0) {
                overdueTasks.add(t);
            }
        }
        return overdueTasks;
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
    public ArrayList<Task> getTasksByPriority(ArrayList<Task> taskList, int priority) {
        ArrayList<Task> tasksWithPriority= new ArrayList<Task>();
        for (Task t: taskList) {
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

    public boolean addCategory(String category) {
        return categories.add(category);
    }

    public boolean addTask(Task task) {
        if (tasks.add(task)) {
            return true;
        }
        return false;
    }

    public boolean completeTask(Task task) {
        if (task != null && tasks.contains(task)) {
            tasks.remove(task);
            return true;
        }
        return false;
    }
}
