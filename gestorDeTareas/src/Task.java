import java.time.LocalDate;
 
public class Task {
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean isComplete = false;


    public Task(){}

    public Task(String title, String description, LocalDate dueDate, boolean isComplete){
        checkTitle(title);
        checkDescription(description);
        checkDueDate(dueDate);
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isComplete = isComplete;
    }

    public void setTitle(String title){
        checkTitle(title);
        this.title = title.trim();
    }

    public void setDescription(String description){
        checkDescription(description);
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate){
        checkDueDate(dueDate);        
        this.dueDate = dueDate;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public LocalDate getDueDate(){
        return this.dueDate;
    }

    public Boolean getIsComplete(){
        return this.isComplete;
    }

    public void completedTask(){
        this.isComplete = true;
    }

    private void checkTitle(String title){
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
    }

    private void checkDescription(String description){
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
    }

    private void checkDueDate(LocalDate dueDate){
        if (dueDate == null) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede estar vacía.");
        }

        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser anterior a la fecha actual.");
        }
    }

    @Override
    public String toString(){
        return "\tTitulo: "+ title + "\n\tDescripción: "+ description + "\n\tFecha de vencimiento: "+ dueDate + "\n\t¿Completa?: "+ (isComplete ? "Si":"No");
    }
}

