package com.ims.service;
import com.ims.model.Product;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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

    public Product searchProduct(String name){
        for(Product p : productMap.values()){
            if(p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }

    public List<Product> partialSearch(String partial){
        List<Product> result = new ArrayList<>();
        String query = partial.toLowerCase();

        for(Product p : productMap.values()){
            if(p.getName().toLowerCase().contains(query)){
                result.add(p);
            }
        }
        return result;
    }

    public List<Product> getProductsByCategory(String category){
        List<Product> result = new ArrayList<>();

        for(Product p : productMap.values()){
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);    
            }
        }
        return result;
    }
    
    public List<Product> getProductsByPrice(double minPrice, double maxPrice){
        List<Product> result = new ArrayList<>();

        for(Product p : productMap.values()){
            if (p.getPrice()>=minPrice && p.getPrice()<=maxPrice) {
                result.add(p);    
            }
        }
        return result;
    }

    
    public List<Product> getAvailableProducts(){
        List<Product> result = new ArrayList<>();

        for(Product p : productMap.values()){
            if (p.getQuantity()>0) {
                result.add(p);    
            }
        }
        return result;
    }
    
    public void increaseStock(int productId, int qty){
        if(!productMap.containsKey(productId)){
            throw new IllegalArgumentException("Product not found!");
        }
            
        productMap.get(productId).updateQuantity(qty);
    }

    public void decreaseStock(int productId, int qty){
        if(!productMap.containsKey(productId)){
            throw new IllegalArgumentException("Product not found!");
        }
        
        productMap.get(productId).updateQuantity(-qty);
    }

    public void updateProductQuantity(int productId, int newQty){
        if(!productMap.containsKey(productId)){
            throw new IllegalArgumentException("Product not found!");
        }

        int change = newQty - productMap.get(productId).getQuantity();
        productMap.get(productId).updateQuantity(change);
    }

    public List<Product> getLowStockProducts(int threshold){
        return productMap.values().stream()
                .filter(p -> p.getQuantity()<threshold)
                .toList();
    }

    public List<Product> getProductsSortedByPrice(boolean ascending){
        return productMap.values().stream()
                .sorted(ascending
                ?Comparator.comparing(Product::getPrice)
                :Comparator.comparing(Product::getPrice).reversed())
                .toList();
    }

    public List<Product> getProductsSortedByQuantity(boolean ascending){
        return productMap.values().stream()
                .sorted(ascending
                ?Comparator.comparing(Product::getQuantity)
                :Comparator.comparing(Product::getQuantity).reversed())
                .toList();
    }

    public Map<String,List<Product>> groupProductsByCategory(){
        return productMap.values().stream()
                .collect(Collectors.groupingBy(Product::getCategory));
    }
}

