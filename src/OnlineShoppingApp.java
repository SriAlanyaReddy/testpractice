import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}

class Product {
    private String name;
    private String description;
    private double price;

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
}

class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}

class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public double getTotalPriceWithGST() {
        return getTotalPrice() * 1.065; // 6.5% GST
    }

    public void displayCart() {
        for (CartItem item : items) {
            System.out.println(item.getProduct().getName() + " - Quantity: " + item.getQuantity() + " - Total: $" + item.getTotalPrice());
        }
        System.out.println("Total (without GST): $" + getTotalPrice());
        System.out.println("Total (with 6.5% GST): $" + getTotalPriceWithGST());
    }
}

public class OnlineShoppingApp {
    private List<User> users = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private User currentUser;
    private ShoppingCart currentCart = new ShoppingCart();

    public void createUser(String name, String email, String password) {
        users.add(new User(name, email, password));
    }

    public void displayProducts() {
        for (Product product : products) {
            System.out.println(product.getName() + " - " + product.getDescription() + " - $" + product.getPrice());
        }
    }

    public void addToCart(Product product, int quantity) {
        currentCart.addProduct(product, quantity);
    }

    public void checkout() {
        System.out.println("Checking out:");
        currentCart.displayCart();
        // Reset the cart after checkout
        currentCart = new ShoppingCart();
    }

    public static void main(String[] args) {
        OnlineShoppingApp app = new OnlineShoppingApp();
        Scanner scanner = new Scanner(System.in);

        // Creating some products
        app.products.add(new Product("Laptop", "A high performance laptop", 1200));
        app.products.add(new Product("Smartphone", "Latest model smartphone", 800));
        app.products.add(new Product("Headphones", "Noise-cancelling headphones", 200));

        // User creation
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        app.createUser(name, email, password);

        // Simulate browsing and adding to cart
        System.out.println("\nAvailable products:");
        app.displayProducts();
        System.out.println("\nEnter the name of the product to add to cart:");
        String productName = scanner.nextLine();
        System.out.println("Enter the quantity:");
        int quantity = scanner.nextInt();

        for (Product product : app.products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                app.addToCart(product, quantity);
                break;
            }
        }

        // Checkout
        app.checkout();
    }
}
