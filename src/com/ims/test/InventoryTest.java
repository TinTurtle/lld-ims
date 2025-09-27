package com.ims.test;

import com.ims.model.Product;
import com.ims.service.Inventory;

import java.util.List;

public class InventoryTest {
    public static void main(String[] args) {
        Inventory inventory = Inventory.getInstance();

        // === Setup Data ===
        try {
            inventory.addProduct(new Product(1, "Laptop", 55000, 10, "Electronics"));
            inventory.addProduct(new Product(2, "Phone", 30000, 0, "Electronics"));
            inventory.addProduct(new Product(3, "Table", 4000, 5, "Furniture"));
            inventory.addProduct(new Product(4, "Chair", 2000, 15, "Furniture"));
            inventory.addProduct(new Product(5, "Shoes", 2500, 8, "Fashion"));
        } catch (Exception e) {
            System.out.println("Setup error: " + e.getMessage());
        }

        // === Exact Search ===
        System.out.println("\n=== Exact Search Tests ===");
        System.out.println("Search 'Laptop': " + inventory.searchProduct("Laptop"));
        System.out.println("Search 'Unknown': " + inventory.searchProduct("Unknown"));

        // === Partial Search ===
        System.out.println("\n=== Partial Search Tests ===");
        List<Product> partial1 = inventory.partialSearch("top");
        System.out.println("Search 'top': " + partial1);
        List<Product> partial2 = inventory.partialSearch("tab");
        System.out.println("Search 'tab': " + partial2);

        // === Category Filter ===
        System.out.println("\n=== Category Filter Tests ===");
        List<Product> furniture = inventory.getProductsByCategory("Furniture");
        System.out.println("Category 'Furniture': " + furniture);
        List<Product> fashion = inventory.getProductsByCategory("Fashion");
        System.out.println("Category 'Fashion': " + fashion);

        // === Price Range ===
        System.out.println("\n=== Price Range Tests ===");
        List<Product> range1 = inventory.getProductsByPrice(2000, 6000);
        System.out.println("Price 2000-6000: " + range1);
        List<Product> range2 = inventory.getProductsByPrice(100000, 200000);
        System.out.println("Price 100000-200000: " + range2);

        // === Availability ===
        System.out.println("\n=== Availability Tests ===");
        List<Product> available = inventory.getAvailableProducts();
        System.out.println("Available products: " + available);
    }
}
