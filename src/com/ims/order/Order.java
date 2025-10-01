package com.ims.order;

import java.util.ArrayList;
import java.util.List;
import com.ims.order.OrderItem;
import com.ims.service.Inventory;
import com.ims.model.Product;

public class Order {
    private List<OrderItem> items = new ArrayList<>();
    private double totalPrice = 0.0;

    public double getTotalPrice(){
        return totalPrice;
    }
    public void addItem(Product p, int qty){
        OrderItem item = new OrderItem(p, qty);
        items.add(item);
    }

    public double calculateTotal(){
        double sum = 0.0;
        for(OrderItem item : items){
            sum += item.calculateSubTotal();
        }
        return sum;
    }

    public void confirmOrder(){
        for(OrderItem item : items){
            if(Inventory.getInstance().getProductById(item.getProduct().getId()) == null){
                throw new IllegalArgumentException("Product Not Found");
            }
            if(item.getQuantity() > Inventory.getInstance().getProductById(item.getProduct().getId()).getQuantity()){
                throw new IllegalArgumentException("Not Enough Stock");
            }
            Inventory.getInstance().decreaseStock(item.getProduct().getId(), item.getQuantity());
            
            totalPrice = calculateTotal();
        }
    }

}
