package com.ims.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String category;


    //constructor
    public Product(int id, String name, double price, int quantity, String category){
        if(price<0) throw new IllegalArgumentException("Price cannot be negative."); 
        if(quantity<0) throw new IllegalArgumentException("Quantity cannot be negative."); 
        if(category==null) throw new IllegalArgumentException("Category cannot be null."); 
        if(name==null) throw new IllegalArgumentException("Name cannot be null."); 
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    //Setters
    public void setName(String name){
        if(name==null) throw new IllegalArgumentException("Name cannot be null."); 
        this.name = name;
    }
    public void setPrice(double price){
        if(price<0) throw new IllegalArgumentException("Price cannot be negative.");
        this.price = price;
    }
    public void setCategory(String category){
        if(category==null) throw new IllegalArgumentException("Category cannot be null."); 
        this.category = category;
    }

    //getters
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){ 
        return quantity; 
    }
    public String getCategory(){
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                '}';
    }

    

    //public method to handle stock updates
    public void updateQuantity(int change){
        if(this.quantity + change < 0) throw new IllegalArgumentException("Not enough Stock, Please restock, Only "+this.quantity+" items are available");
        else this.quantity += change;
    }
}
