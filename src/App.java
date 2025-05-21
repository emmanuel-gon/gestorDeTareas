import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        LocalDate fechaVencimiento = pedirFecha(scanner);
        
        System.out.println("Fecha ingresada correctamente: " + fechaVencimiento);
    }

    public static LocalDate pedirFecha(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = null;
        
        while (fecha == null) {
            System.out.print("Ingrese la fecha de vencimiento (dd-MM-yyyy): ");
            String input = scanner.nextLine();
            
            try {
                fecha = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato incorrecto. Por favor, ingrese la fecha en formato dd-MM-yyyy.");
            }
        }
        
        return fecha;
    }
}
