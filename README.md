
# Inventory Management System (IMS) – Java Console Project

## 📌 Overview

This is a **console-based Inventory Management System** implemented in Java.
It allows users to manage products, track stock, create customer orders, and view sales history.

The project demonstrates **Object-Oriented Programming (OOP)** concepts, **Low-Level Design (LLD)**, and clean modular code organization.

---

## ⚙️ Features

### 🔹 Product Management

* Add new products with ID, name, price, quantity, and category
* Search products by name, partial name, category, or price range
* List all products
* Sort products (by price or quantity in ascending/descending order)
* Group products by category
* View only available products (quantity > 0)
* Check for low stock items with a custom threshold

### 🔹 Stock Management

* Increase or decrease stock of existing products
* Error handling for invalid stock operations (e.g., decreasing below 0)

### 🔹 Order Management

* Create a new order
* Add items (with quantity check)
* Confirm an order (updates inventory automatically)
* View all orders with order details and total price

### 🔹 Robust User Input Handling

* Uses `try-catch` for input mismatches
* Clear error messages for invalid operations

---

## 📂 Package Structure

```
com.ims
│── Main.java                # Entry point with menu-driven UI
│
├── model
│   └── Product.java          # Product entity
|
├── observer                  # Observer pattern for Alerts
|   └── ConsoleAlertService.java
|   └── EmailAlertService.java
|   └── StockObserver.java
│
├── service
│   └── Inventory.java        # Singleton inventory manager
│
└── order
    ├── Order.java            # Handles customer orders
    └── OrderItem.java        # Represents product + quantity in an order
```



