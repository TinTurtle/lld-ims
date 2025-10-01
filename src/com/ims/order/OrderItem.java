package com.ims.order;

import com.ims.model.Product;

public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    //getters
    public Product getProduct(){
        return product;
    }

    public int getQuantity(){
        return quantity;
    }

    //method to calculate the total of the item ordered
    public double calculateSubTotal(){
        return product.getPrice() * quantity;
    }
}
