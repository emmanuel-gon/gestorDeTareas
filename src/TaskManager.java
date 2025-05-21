import java.time.LocalDate;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks;

    public TaskManager(){
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        checkTask(task);
        tasks.add(task);
    }

    public void showListTasks(){
        
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas en la lista.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Tarea n°: "+ (i+1));
            System.out.println(tasks.get(i));
            System.out.println("");
        }
    }

    public ArrayList<Task> getAllTasks(){
        return new ArrayList<>(tasks);
    }

    public void removeTask(int index){
        
        int indexReal = index-1;
        checkIndex(indexReal);

        Task taskToDelete = tasks.get(indexReal);

        tasks.remove(indexReal);
        System.out.println("Se elimino la siguiente tarea: ");
        System.out.println(taskToDelete);

    }

    public void completedTask(int index){
        
        Task taskToComplete = getTaskByIndex(index);
    
        if (taskToComplete.getIsComplete()) {
            System.out.println("La tarea ya se encuentra completa.");
        } else {
            taskToComplete.completedTask();
            System.out.println("Se ha marcado como completa la siguiente tarea:");
            System.out.println(taskToComplete);
        }
    }

    public void updateTask(int index, String newTitle, String newDescription, LocalDate newDueDate){
 

        Task taskToUpdate = getTaskByIndex(index);

        
        if (updateFieldTask(taskToUpdate, newTitle, newDescription, newDueDate)) {
            System.out.println("Se ha actualizado la tarea: ");
            System.out.println(taskToUpdate);    
        } else{
            System.out.println("La tarea no se ha actualizado: ha proporcionado valores iguales o nulos.");
        }
    }

    public void listOfCompletedTasks(){
        
        System.out.println("Lista de tareas completas: \n");
        
        boolean completed = false;

        for(Task task: tasks){
            completed = printIfTaskIsComplete(task, completed);
        }

        if (!completed) {
            System.out.println("No tenes tareas completas.");
        }
    }

    public boolean printIfTaskIsComplete(Task task, boolean completed){
        
        if (task.getIsComplete()) { 
            System.out.println(task);
            System.out.println("");
            completed = true;
        }
        return completed;
    }

    public void listOfOverdueTasks(){
        
        System.out.println("Lista de tareas vencidas: ");

        boolean overdue = false;

        for (Task task: tasks) { 
            overdue = printIfTaskIsOverdue(task, overdue);

        }

        if (!overdue) { 
            System.out.println("No tenes tareas vencidas.");
        }
    }

    public boolean printIfTaskIsOverdue(Task task, boolean overdue){

        if (task.getDueDate().isBefore(LocalDate.now()) && !task.getIsComplete()) {
            System.out.println(task);
            overdue = true;
        }
        return overdue;

    }

    public void checkTask(Task task){

        checkNullTask(task);
        checkDuplicatedTask(task); 
    }

    public void checkNullTask(Task task){
        
        if (task == null) {
            throw new IllegalArgumentException("La tarea no puede estar vacía.");
        }
    }

    public void checkDuplicatedTask(Task task){

        for (Task t: tasks){
            if (t.getTitle().equalsIgnoreCase(task.getTitle().trim())) {
                throw new IllegalArgumentException("La tarea con ese nombre ya existe.");
            }
        } 
    }


    public void checkDuplicatedTitle(Task task, String title){

        for (Task t: tasks){
            if (t!= task && t.getTitle().equalsIgnoreCase(title.trim())) {
                throw new IllegalArgumentException("La tarea con ese nombre ya existe.");
            }
        }
    }
    
    private Task getTaskByIndex(int index){
        int indexReal = index-1;
        checkIndex(indexReal);
        return tasks.get(indexReal);
    }

    public void checkIndex(int index){

        if (index <0 || index >= tasks.size()) {
            throw new IllegalArgumentException("El indice de la tarea no forma parte de la lista.");
        }
    }

    private boolean updateFieldTask(Task taskToUpdate, String newTitle, String newDescription, LocalDate newDueDate){

        boolean update = false;

        boolean updateTitle = updateTitleTask(taskToUpdate, newTitle, update);

        boolean updateDescription = updateDescriptionTask(taskToUpdate, newDescription, update);

        boolean updateDueDate = updateDueDateTask(taskToUpdate, newDueDate, update);

        return (updateTitle || updateDescription || updateDueDate);
    }

    private boolean updateTitleTask(Task taskToUpdate, String newTitle, boolean update){

        if (newTitle != null && !taskToUpdate.getTitle().equalsIgnoreCase(newTitle.trim())) {
            checkDuplicatedTitle(taskToUpdate, newTitle);
            taskToUpdate.setTitle(newTitle.trim());
            System.out.println("Titulo actualizado.");
            update = true;
        }
        return update;
    }

    private boolean updateDescriptionTask(Task taskToUpdate, String newDescription, boolean update){

        if (newDescription != null && !taskToUpdate.getDescription().equalsIgnoreCase(newDescription.trim())) {
            taskToUpdate.setDescription(newDescription.trim());
            System.out.println("Descripción actualizada.");
            update = true;
        }
        return update;
    }

    private boolean updateDueDateTask(Task taskToUpdate, LocalDate newDueDate, boolean update){

        if (newDueDate != null && !taskToUpdate.getDueDate().isEqual(newDueDate)) {
            taskToUpdate.setDueDate(newDueDate);
            System.out.println("Fecha de vencimiento actualizada.");
            update = true;
        }
        return update;
    }
}


