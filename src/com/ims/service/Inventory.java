package com.ims.service;
import com.ims.model.Product;
import java.util.Map;
import java.util.HashMap;

public class Inventory {

    private Map<Integer, Product> productMap;
    private Inventory(){
        productMap = new HashMap<>();
    }

    private static Inventory instance;
    public static Inventory getInstance(){
        if (instance == null){
            instance = new Inventory();
        }
        return instance;
    }

    public void addProduct(Product P){
        if(productMap.containsKey(P.getId())){
            throw new IllegalArgumentException("Product with this ID already exists!");
        }
        productMap.put(P.getId(), P);
    }

    public Product getProductById(int id){
        return productMap.get(id);
    } 

    public void removeProduct(int id){
        productMap.remove(id);
    }

    public void listAllProducts(){
        for (Product p : productMap.values()) {
        System.out.println(p);
    }
    }
}
