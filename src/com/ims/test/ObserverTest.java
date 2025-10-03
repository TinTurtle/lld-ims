package com.ims.test;

import com.ims.model.Product;
import com.ims.observer.ConsoleAlertService;
import com.ims.observer.EmailAlertService;
import com.ims.service.Inventory;

public class ObserverTest {
    public static void main(String[] args) {
        Inventory inv = Inventory.getInstance();

        // Register observers
        inv.addObserver(new ConsoleAlertService());
        inv.addObserver(new EmailAlertService("admin@ims.com"));

        // Add products
        Product laptop = new Product(1, "Laptop", 100000, 10, "Electronics");
        Product phone = new Product(2, "Phone", 50000, 5, "Electronics");

        inv.addProduct(laptop);
        inv.addProduct(phone);

        // Place orders / reduce stock
        inv.decreaseStock(1, 8); // should trigger low stock alert
        inv.decreaseStock(2, 4); // should trigger low stock alert
    }
}
