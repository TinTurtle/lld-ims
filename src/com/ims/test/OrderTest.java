package com.ims.test;

import com.ims.model.Product;
import com.ims.service.Inventory;
import com.ims.order.Order;

public class OrderTest {
    public static void main(String[] args) {
        Inventory inventory = Inventory.getInstance();

        // Add products to inventory
        Product laptop = new Product(1, "Laptop", 55000, 10, "Electronics");
        Product phone = new Product(2, "Phone", 30000, 5, "Electronics");
        inventory.addProduct(laptop);
        inventory.addProduct(phone);

        // Create an order
        Order order = new Order();
        order.addItem(laptop, 2);
        order.addItem(phone, 3);

        // Confirm order
        try {
            order.confirmOrder();
            System.out.println("Order confirmed successfully!");
            System.out.println("Total Price: " + order.calculateTotal());
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to confirm order: " + e.getMessage());
        }

        // Check inventory after order
        System.out.println("Laptop stock after order: " + inventory.getProductById(1).getQuantity());
        System.out.println("Phone stock after order: " + inventory.getProductById(2).getQuantity());

        // Test invalid order (exceed stock)
        Order badOrder = new Order();
        badOrder.addItem(phone, 10);
        try {
            badOrder.confirmOrder();
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }
}
