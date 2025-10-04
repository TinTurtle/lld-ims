package com.ims.order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.ims.service.Inventory;
import com.ims.model.Product;

public class Order {
    private static final AtomicInteger idGenerator = new AtomicInteger(1000);
    private int orderId;
    private List<OrderItem> items = new ArrayList<>();
    private double totalPrice = 0.0;

    public Order() {
        this.orderId = idGenerator.getAndIncrement();
    }

    public int getOrderId() {
        return orderId;
    }

    public void addItem(Product p, int qty){
        if(p==null){
            throw new IllegalArgumentException("Product Not found!");
        }
        if(p.getQuantity()<qty){
            throw new IllegalArgumentException("Not enough Stock!");
        }
        items.add(new OrderItem(p, qty));
    }

    private double calculateTotal(){
        double sum = 0.0;
        for(OrderItem item : items){
            sum += item.calculateSubTotal();
        }
        return sum;
    }

    public void confirmOrder(){
        for(OrderItem item : items){
            Inventory.getInstance().decreaseStock(item.getProduct().getId(), item.getQuantity());
        }
        totalPrice = calculateTotal();
    }

    public void showItems(){
        for(OrderItem oItem : items){
            System.out.println(oItem);
        }
    }
    @Override
    public String toString() {
        return "Order ID: " + orderId + " | Total: " + totalPrice;
    }
}
