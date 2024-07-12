import java.util.Scanner;

public class exhandle{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter an integer: ");
            int n = scanner.nextInt();
            System.out.println("Result of division: " + (5 / n));
        } catch (ArithmeticException e) {
            System.out.println("Division by zero is not allowed. Please enter another number.");
        } catch (Exception e) {
            System.out.println("Invalid input or error occurred: " + e.getMessage());
        }


        // After handling the division, prompt for another integer input
        System.out.print("Enter another integer: ");
        int p = scanner.nextInt();
        System.out.println("You entered: " + p);

        scanner.close(); // Ensure scanner is closed properly
    }
}
