package com.ims.service;
import com.ims.model.Product;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import com.ims.observer.StockObserver;

public class Inventory {

    private Map<Integer, Product> productMap;
    private List<StockObserver> observers = new ArrayList<>();
    private int lowStockThreshold = 3;

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
    //increase stock checks for availability of the product and updates it
    //after updating it notifies the observer if the stocck available after updation is lower than the threshold
    public void increaseStock(int productId, int qty){
        if(!productMap.containsKey(productId)){
            throw new IllegalArgumentException("Product not found!");
        }
        
        Product product = productMap.get(productId);
            
        product.updateQuantity(qty);

        if(product.getQuantity()<lowStockThreshold){
            notifyObservers("Stock low for "+product.getName()+" (Remaining: "+product.getQuantity()+")" );
        }
    }

    //decreases the stock in the inventory and notifies the observers in a similar fashion to increase stock
    public void decreaseStock(int productId, int qty){
        if(!productMap.containsKey(productId)){
            throw new IllegalArgumentException("Product not found!");
        }
        
        Product product = productMap.get(productId);

        if(product.getQuantity()<qty){
            throw new IllegalArgumentException("Not enough Stock!");
        }

        product.updateQuantity(-qty);
        
        if(product.getQuantity()<lowStockThreshold){
            notifyObservers("Stock low for "+product.getName()+" (Remaining: "+product.getQuantity()+")" );
        }
        
    }

    public void updateProductQuantity(int productId, int newQty){
        if(!productMap.containsKey(productId)){
            throw new IllegalArgumentException("Product not found!");
        }
        Product product = productMap.get(productId);

        int change = newQty - productMap.get(productId).getQuantity();
        product.updateQuantity(change);

        if(product.getQuantity()<lowStockThreshold){
            notifyObservers("Stock low for "+product.getName()+" (Remaining: "+product.getQuantity()+")" );
        }
    }

    //FILTERS
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

    //Observers
    public void addObserver(StockObserver observer){
        observers.add(observer);
    }

    public void removeObserver(StockObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(String message){
        for(StockObserver observer : observers){
            observer.update(message);
        }
    }
}

