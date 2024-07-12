import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class FoodItem {
    String name;
    String cuisine;
    double rating;

    FoodItem(String name, String cuisine, double rating) {
        this.name = name;
        this.cuisine = cuisine;
        this.rating = rating;
    }
}

public class FoodRatingSystem {
    private List<FoodItem> foodItems = new ArrayList<>();

    public void addFoodItem(String name, String cuisine, double rating) {
        foodItems.add(new FoodItem(name, cuisine, rating));
        System.out.println("Food item added: " + name);
    }

    public void editFoodItem(String oldName, String newName, String newCuisine, double newRating) {
        for (FoodItem foodItem : foodItems) {
            if (foodItem.name.equalsIgnoreCase(oldName)) {
                foodItem.name = newName;
                foodItem.cuisine = newCuisine;
                foodItem.rating = newRating;
                System.out.println("Food item edited: " + oldName);
                return;
            }
        }
        System.out.println("Food item not found: " + oldName);
    }

    public void deleteFoodItem(String name) {
        foodItems.removeIf(foodItem -> foodItem.name.equalsIgnoreCase(name));
        System.out.println("Food item deleted: " + name);
    }

    public void modifyRating(String name, double newRating) {
        for (FoodItem foodItem : foodItems) {
            if (foodItem.name.equalsIgnoreCase(name)) {
                foodItem.rating = newRating;
                System.out.println("Rating modified for: " + name);
                return;
            }
        }
        System.out.println("Food item not found: " + name);
    }

    public void listAllFoodItems() {
        for (FoodItem foodItem : foodItems) {
            System.out.println(foodItem.name + " - " + foodItem.cuisine + " - " + foodItem.rating);
        }
    }

    public void getHighestRatedFoodItem(String cuisine) {

        Optional<FoodItem> highestRated = foodItems.stream()
                .filter(foodItem -> foodItem.cuisine.equalsIgnoreCase(cuisine))
                .max(Comparator.comparingDouble(foodItem -> foodItem.rating));
        if (highestRated.isPresent()) {
            FoodItem foodItem = highestRated.get();
            System.out.println("Highest rated food item: " + foodItem.name + " - " + foodItem.rating);
        } else {
            System.out.println("No food items found for cuisine: " + cuisine);
        }
    }

    public static void main(String[] args) {
        FoodRatingSystem app = new FoodRatingSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. Add Food Item");
            System.out.println("2. Edit Food Item");
            System.out.println("3. Delete Food Item");
            System.out.println("4. Modify Rating");
            System.out.println("5. List All Food Items");
            System.out.println("6. Get Highest Rated Food Item");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter cuisine: ");
                    String cuisine = scanner.nextLine();
                    System.out.print("Enter rating: ");
                    double rating = scanner.nextDouble();
                    app.addFoodItem(name, cuisine, rating);
                    break;
                case 2:
                    System.out.print("Enter old name: ");
                    String oldName = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new cuisine: ");
                    String newCuisine = scanner.nextLine();
                    System.out.print("Enter new rating: ");
                    double newRating = scanner.nextDouble();
                    app.editFoodItem(oldName, newName, newCuisine, newRating);
                    break;
                case 3:
                    System.out.print("Enter name: ");
                    String nameToDelete = scanner.nextLine();
                    app.deleteFoodItem(nameToDelete);
                    break;
                case 4:
                    System.out.print("Enter name: ");
                    String nameToModify = scanner.nextLine();
                    System.out.print("Enter new rating: ");
                    double ratingToModify = scanner.nextDouble();
                    app.modifyRating(nameToModify, ratingToModify);
                    break;
                case 5:
                    System.out.println("All Food Items:");
                    app.listAllFoodItems();
                    break;
                case 6:
                    System.out.print("Enter cuisine: ");
                    String cuisineToSearch = scanner.nextLine();
                    app.getHighestRatedFoodItem(cuisineToSearch);
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
