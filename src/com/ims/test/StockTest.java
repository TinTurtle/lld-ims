package com.ims.test;

import com.ims.model.Product;
import com.ims.service.Inventory;

public class StockTest {
    public static void main(String[] args) {
        Inventory inventory = Inventory.getInstance();

        // === Setup Products ===
        inventory.addProduct(new Product(1, "Laptop", 55000, 10, "Electronics"));
        inventory.addProduct(new Product(2, "Chair", 2000, 5, "Furniture"));

        // === Test increaseStock ===
        System.out.println("\n=== increaseStock Tests ===");
        inventory.increaseStock(1, 5); // Laptop: 10 -> 15
        System.out.println("Laptop quantity after increase: " + inventory.getProductById(1).getQuantity());

        try {
            inventory.increaseStock(999, 5); // Invalid ID
        } catch (Exception e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        // === Test decreaseStock ===
        System.out.println("\n=== decreaseStock Tests ===");
        inventory.decreaseStock(2, 3); // Chair: 5 -> 2
        System.out.println("Chair quantity after decrease: " + inventory.getProductById(2).getQuantity());

        try {
            inventory.decreaseStock(2, 5); // Too much
        } catch (Exception e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        try {
            inventory.decreaseStock(999, 1); // Invalid ID
        } catch (Exception e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}
