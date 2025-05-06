import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class main {
    public static void main(String[] args) {
        
        System.out.println("Bienvenido al gestor de tareas. ");
        
        menuTaskManager();
 
    }
    
    public static void menuTaskManager(){
        
        TaskManager manager = new TaskManager();

        Scanner input = new Scanner(System.in);       

        boolean exit = false;
        int option =-1;
 
         while (!exit) {
            System.out.printf("""
                Puede elegir por las siguientes opciones:
                \n \t 1 - Agregar una nueva tarea.
                \n \t 2 - Mostrar el listado de las tareas.
                \n \t 3 - Mostrar el listado de tareas vencidas.
                \n \t 4 - Mostrar el listado de tareas completas.
                \n \t 5 - Marcar como completa una tarea.
                \n \t 6 - Modificar una tarea.
                \n \t 7 - Eliminar una tarea.
                \n \t 0 - Salir del programa.
              """);
            System.out.print("Ingrese la opción deseada: ");
            
            if((option = checkNumberEntered(input)) == -1){
                continue;
            }
            
            exit= optionMenu(option, exit, input, manager);         
            
         }
    }

    public static int checkNumberEntered(Scanner input){
        int option = -1;
        try {
            option = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Usted no ha ingresado un número, por favor ingrese un número válido.");
        }
        return option;
    }

    public static boolean optionMenu(int option, boolean exit, Scanner input, TaskManager manager){
        
        switch (option) {
            case 0: 
                exit= true;
                System.out.println("Gracias por utilizar el Gestor de Tareas.");
                break;
            case 1:
                addTaskToList(input, manager);
                break;
            case 2:
                manager.showListTasks();
                break;
            case 3:
                manager.listOfOverdueTasks();
                break;
            case 4:
                manager.listOfCompletedTasks();
                break;
            case 5:
                markATaskAsComplete(input, manager);
                break;
            case 6:
                updateATask(input, manager);
                break;
            case 7:
                deleteATask(input, manager);
                break;
            default:
                System.out.println("Opción invalida, intente nuevamente.");
                break;
        }
        return exit;
    }

    public static void addTaskToList(Scanner input, TaskManager manager){
        System.out.print("Ingrese el titulo de la tarea: ");
        String title = input.nextLine();
        System.out.print("Ingrese la descripción de la tarea: ");
        String description = input.nextLine();
        LocalDate dueDate = checkDueDateFormat(input);
        Task newTask = new Task(title, description, dueDate, false);
        manager.addTask(newTask);
        System.out.println("Tarea agregada con exito.");
    }

    public static LocalDate checkDueDateFormat(Scanner input){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dueDate = null;

        while (dueDate == null) {
            System.out.print("Ingrese la fecha de vencimiento en formato(dd-mm-aaaa): ");
            String date = input.nextLine();

            try {
                dueDate = LocalDate.parse(date,formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ha ingresado la fecha en un formato incorrecto.");
                dueDate = null;
            }
        }
        return dueDate;
    }
    public static void markATaskAsComplete(Scanner input, TaskManager manager){

        manager.completedTask(getTheIndex(input, manager, "Ingrese el indice de la tarea a completar: "));
        
    }

    public static void deleteATask(Scanner input, TaskManager manager){

        manager.removeTask(getTheIndex(input, manager,"Ingrese el indice de la tarea a eliminar: "));
    }

    public static void updateATask(Scanner input, TaskManager manager){
       
    }

    public static int getTheIndex(Scanner input, TaskManager manager, String mesage){
        int index =-1;
        while (index == -1) {
            System.out.println(mesage);
            index = checkNumberEntered(input); //Verifico el indice como entero
            
            try {
                manager.checkIndex(index-1); //Verifico que este en el array
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                index = -1;
            }
        }
        return index;
    }

    
}

   

 
