package com.ims.test;

import com.ims.model.Product;
import com.ims.service.Inventory;

public class InventoryTest2 {
    public static void main(String[] args) {
        Inventory inventory = Inventory.getInstance();

        // Add some products
        inventory.addProduct(new Product(1, "Laptop", 55000, 10, "Electronics"));
        inventory.addProduct(new Product(2, "Phone", 30000, 2, "Electronics"));
        inventory.addProduct(new Product(3, "Table", 4000, 5, "Furniture"));
        inventory.addProduct(new Product(4, "Chair", 2000, 15, "Furniture"));
        inventory.addProduct(new Product(5, "Shoes", 2500, 8, "Fashion"));

        // === Low Stock Tests ===
        System.out.println("=== Low Stock (threshold = 5) ===");
        System.out.println(inventory.getLowStockProducts(5));

        // === Sort by Quantity Tests ===
        System.out.println("\n=== Products Sorted by Quantity (Ascending) ===");
        System.out.println(inventory.getProductsSortedByQuantity(true));

        System.out.println("\n=== Products Sorted by Quantity (Descending) ===");
        System.out.println(inventory.getProductsSortedByQuantity(false));

        // === Sort by Price Tests ===
        System.out.println("\n=== Products Sorted by Price (Ascending) ===");
        System.out.println(inventory.getProductsSortedByPrice(true));

        System.out.println("\n=== Products Sorted by Price (Descending) ===");
        System.out.println(inventory.getProductsSortedByPrice(false));

        // === Group by Category Tests ===
        System.out.println("\n=== Products Grouped by Category ===");
        System.out.println(inventory.groupProductsByCategory());
    }
}
