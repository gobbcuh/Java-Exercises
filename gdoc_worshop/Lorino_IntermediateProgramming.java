package gdoc_workshopIP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class ShoppingCart {
    static Map<String, List<String>> categories = new HashMap<>();
    static ArrayList<String> cart = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String CART_FILE = "cart.txt";
    static final String RECEIPT_FILE = "receipt.txt";
    static boolean isFirstRun = true;

    public static void main(String[] args) {
        intro_loading();
        initializeCategories();
        loadCart(); // Load previous cart items

        while (true) {
            System.out.println("\n Shopping Cart");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. Delete Cart Item");
            System.out.println("4. View Cart");
            System.out.println("5. Clear Cart");
            System.out.println("6. Place Order");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                loading();

                switch (choice) {
                    case 1 -> viewCategoriesAndProducts();
                    case 2 -> addToCart();
                    case 3 -> deleteItemFromCart();
                    case 4 -> viewCart();
                    case 5 -> clearCart(); 
                    case 6 -> placeOrder();
                    case 7 -> {
                        saveCart();
                        exit_loading();
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    static void initializeCategories() {
        categories.put("Computer Parts", Arrays.asList(
            "Computer Case - ₱4200",
            "Motherboard - ₱8000",
            "CPU - ₱12000",
            "GPU - ₱15000"
        ));
        categories.put("Gadgets", Arrays.asList(
            "Laptop - ₱50000",
            "Smartphone - ₱20000",
            "Tablet - ₱15000",
            "Smartwatch - ₱5000"
        ));
        categories.put("Accessories", Arrays.asList(
            "Mouse - ₱1000",
            "Keyboard - ₱3000",
            "Speakers - ₱2500",
            "Headphones - ₱3000"
        ));
    }

    static void viewCategoriesAndProducts() {
        System.out.println("\n Categories:"); // Display categories
        int index = 1;
        for (String category : categories.keySet()) {
            System.out.println(index++ + ". " + category);
        }

        System.out.print("Select a category to view products: ");
        try {
            int catChoice = Integer.parseInt(scanner.nextLine()) - 1;

            if (catChoice < 0 || catChoice >= categories.size()) {
                System.out.println("Invalid category number!");
                return;
            }

            String selectedCategory = (String) categories.keySet().toArray()[catChoice];
            List<String> products = categories.get(selectedCategory);

            System.out.println("\n Available Products in " + selectedCategory + ":");
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        }
    }

    static void addToCart() {
        System.out.print("Select a category to view products: ");
        try {
            int catChoice = Integer.parseInt(scanner.nextLine()) - 1;

            if (catChoice < 0 || catChoice >= categories.size()) {
                System.out.println("Invalid category number!");
                return;
            }

            String selectedCategory = (String) categories.keySet().toArray()[catChoice];
            List<String> products = categories.get(selectedCategory);

            System.out.println("\n Available Products in " + selectedCategory + ":");
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i));
            }

            System.out.print("Enter product number to add to cart: ");
            int productNum = Integer.parseInt(scanner.nextLine()) - 1;

            if (productNum >= 0 && productNum < products.size()) {
                String selectedProduct = products.get(productNum);
                cart.add(selectedProduct);
                System.out.println("Added: " + selectedProduct);
                saveCart();
            } else {
                System.out.println("Invalid product number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        }
    }

    static void viewCart() {
        System.out.println("\n   Your Cart:");
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (int i = 0; i < cart.size(); i++) {
                System.out.println((i + 1) + ". " + cart.get(i));
            }
        }
    }

    static void clearCart() {
        cart.clear();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CART_FILE, false))) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error clearing cart: " + e.getMessage());
        }
        System.out.println("Cart has been cleared.");
    }

    static void saveCart() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CART_FILE))) {
            for (String item : cart) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving cart: " + e.getMessage());
        }
    }

    static void loadCart() {
        if (isFirstRun) {
            cart.clear(); // First run, empty cart muna
            isFirstRun = false;
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(CART_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cart.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous cart found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading cart: " + e.getMessage());
        }
    }

    static void placeOrder() { // For placing orders
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add items to your cart before placing an order.");
            return;
        }

        double totalPrice = 0.0;
        System.out.println("\n Your Order Summary:");
        for (String item : cart) {
            System.out.println("- " + item);
            String[] parts = item.split(" - ₱");
            if (parts.length > 1) {
                totalPrice += Double.parseDouble(parts[1].replace(",", ""));
            }
        }
        System.out.printf("Total Price: ₱%.2f%n", totalPrice);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECEIPT_FILE))) {
            writer.write("=== Order Receipt ===\n"); // For receipt
            for (String item : cart) {
                writer.write(item + "\n");
            }
            writer.write(String.format("Total Price: ₱%.2f%n", totalPrice));
            writer.write("Thank you for shopping with us!\n");
            System.out.println("Receipt saved to " + RECEIPT_FILE);
        } catch (IOException e) {
            System.out.println("Error generating receipt: " + e.getMessage());
        }

        cart.clear(); // Empty cart after mag-place ng order
        saveCart();
    }

    static void deleteItemFromCart() { // For deleting cart items
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to delete.");
            return;
        }

        viewCart(); // Show the cart items with numbers
        System.out.print("Enter the item number to delete: ");
        try {
            int itemNum = Integer.parseInt(scanner.nextLine()) - 1;

            if (itemNum >= 0 && itemNum < cart.size()) {
                String removedItem = cart.remove(itemNum);
                System.out.println("Removed: " + removedItem);
                saveCart(); // Save the updated cart
            } else {
                System.out.println("Invalid item number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        }
    }

    public static void intro_loading() {
        System.out.print("\n\n\n\t Starting the program... Please wait! ");
        for (int i = 0; i < 25; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("\t\tError in loading delay!");
            }
            System.out.print("█");
        }
        System.out.println("\n");
        System.out.println("\tAddiCart: Your Go-To Online Cart for Tech Shopping");
    }
    
    public static void loading() {
        System.out.print("\n  Loading ");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("\t\tError in loading delay!");
            }
            System.out.print(".");
        }
        System.out.println("\n");
    }
    
    public static void exit_loading() {
        System.out.print("\n\n\n\t Exiting the program... Please wait! ");
        for (int i = 0; i < 25; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("\t\tError in loading delay!");
            }
            System.out.print("█");
        }
        System.out.println("\n");
        System.out.println("\t\t\tThank you for shopping!");
    }
}