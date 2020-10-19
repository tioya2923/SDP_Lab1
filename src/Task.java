import java.util.ArrayList;

public class Task {

    private static String task;

    static ArrayList<String> taskCollection = new ArrayList<String>();

    public Task (String task) {
        this.task = task;
    }

    public static void showTasks(){
        for (String task : taskCollection) {
            System.out.println(task);
        }
    }

    public static ArrayList<String> addTask(){
        if (task.length() <= 120){
            taskCollection.add(task);
        }else{
            System.out.println("Please use 120 characters as maximum");
        }
        return taskCollection;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
