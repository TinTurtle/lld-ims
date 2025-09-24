package com.ims.test;

import com.ims.model.Product;

public class ProductTest {
    public static void main(String[] args) {
        
        // === 1. Constructor Tests ===
        System.out.println("=== Constructor Tests ===");
        Product p1 = new Product(1, "Laptop", 55000.0, 10, "Electronics");
        System.out.println("Created Product: " + p1);

        try {
            Product pInvalidPrice = new Product(2, "Phone", -5000.0, 5, "Electronics");
            System.out.println("Should have failed: " + pInvalidPrice);
        } catch (Exception e) {
            System.out.println("Correctly caught invalid price: " + e.getMessage());
        }

        try {
            Product pInvalidQty = new Product(3, "Tablet", 12000.0, -3, "Electronics");
            System.out.println("Should have failed: " + pInvalidQty);
        } catch (Exception e) {
            System.out.println("Correctly caught invalid quantity: " + e.getMessage());
        }

        // === 2. Setter Tests ===
        System.out.println("\n=== Setter Tests ===");
        p1.setName("Gaming Laptop");
        System.out.println("Updated Name: " + p1.getName());

        p1.setPrice(60000.0);
        System.out.println("Updated Price: " + p1.getPrice());

        p1.setCategory("Gaming");
        System.out.println("Updated Category: " + p1.getCategory());

        // === 3. Quantity Update Tests ===
        System.out.println("\n=== Quantity Update Tests ===");
        p1.updateQuantity(-5); // Selling 5
        System.out.println("After selling 5: Quantity = " + p1.getQuantity());

        p1.updateQuantity(10); // Restocking 10
        System.out.println("After restocking 10: Quantity = " + p1.getQuantity());

        p1.updateQuantity(-15); // Selling all
        System.out.println("After selling all: Quantity = " + p1.getQuantity());

        p1.updateQuantity(-1); // Selling when stock is 0
        System.out.println("After trying to sell with no stock: Quantity = " + p1.getQuantity());

        // === 4. Getter Verification ===
        System.out.println("\n=== Getter Verification ===");
        System.out.println("ID: " + p1.getId());
        System.out.println("Name: " + p1.getName());
        System.out.println("Price: " + p1.getPrice());
        System.out.println("Quantity: " + p1.getQuantity());
        System.out.println("Category: " + p1.getCategory());
    }
}
