package com.ims;

import com.ims.service.*;
import com.ims.model.*;
import com.ims.observer.*;
import com.ims.order.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Inventory inv = Inventory.getInstance();
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("====IMS MENU====");
            System.out.println("1. List all products");
            System.out.println("2. Search product by Name");
            System.out.println("3. Search product by Partial Name");
            System.out.println("4. Search product by Category");
            System.out.println("5. Search product by Price Range");
            System.out.println("6. View available products");
            System.out.println("7. Sort products by Price");
            System.out.println("8. Sort products by Quantity");
            System.out.println("9. Group products by Category");
            System.out.println("10. Add Product");
            System.out.println("11. Increase Stock");
            System.out.println("12. Decrease Stock");
            System.out.println("13. View Low Stock products");
            System.out.println("14. Create new order");
            System.out.println("15. Exit");
            System.out.println("================");
            System.out.print("Enter Choice : ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:// listing all products
                    inv.listAllProducts();
                    break;

                case 2:// search using name
                    searchByName();
                    break;
                
                case 3:// search using partial name
                    searchByPartialName();
                    break;

                case 4:// search using category
                    searchByCategory();
                    break;

                case 5:// search using price range
                    searchByPrice();
                    break;

                case 6://view available products
                    availableProducts();
                    break;

                case 7://sort by price
                    sortByPrice();
                    break;

                case 8://sort by qty
                    sortByQuantity();
                    break;

                case 9://group by category
                    groupByCategory();
                    break;

                case 10:// add product
                    addProduct();
                    break;

                case 11://  increase stock
                    increaseStock();
                    break;

                case 12:// deacrease stock
                    decreaseStock();
                    break;

                case 13:// view low stock products
                    viewLowStockProducts();
                    break;

                case 14:// create new order
                    createOrder();
                    break;

                case 15: // exit the application
                    running = false;
                    break;

                default:
                    System.out.println("Invalid Input!!");
                    break;
            }
        }
    } 

    //Methods to call inside the cases
    //2
    private static void searchByName(){
        System.out.println("Enter the name of the Product you want to search : ");
        String name = sc.nextLine();
        Product p = inv.searchProduct(name);
        if(p!=null) System.out.println(p);
        else System.out.println("Prodcut Not Found");

    }
    
    //3
    private static void searchByPartialName(){
        System.out.println("Enter the partial name of the Product you want to search : ");
        String name = sc.nextLine();
        List<Product> products = inv.partialSearch(name);
        if(p!=null){
            for(Product p : products){
                System.out.println(p);
            }
        } 
        else System.out.println("No products found!");
    }

    //4
    private static void searchByCategory(){
        System.out.println("Enter the Category of the Product you want to search : ");
        String cat = sc.nextLine();
        List<Product> products = inv.getProductsByCategory(cat);
        if(p!=null){
            for(Product p : products){
                System.out.println(p);
            }
        } 
        else System.out.println("No products found!");
    }
    
    //5
    private static void searchByPrice(){
        System.out.println("Enter the maximum price of your product : ");
        double max = sc.nextDouble();
        System.out.println("Enter the minimum price of your product : ");
        double min = sc.nextDouble();

        List<Product> products = inv.getProductsByPrice(min, max);
        if(p!=null){
            for(Product p : products){
                System.out.println(p);
            }
        } 
        else System.out.println("No products found!");
    }

    //6
    private static void availableProducts(){
        List<Product> products = inv.getAvailableProducts()
        if(p!=null){
            for(Product p : products){
                System.out.println(p);
            }
        } 
        else System.out.println("No products found!");
    }

    //7
    private static void sortByPrice(){
        System.out.println("1.Ascending");
        System.out.println("2.Descending");
        System.out.println("Enter your choice: ");
        int num = sc.nextInt();
        boolean ascending = false; 
        if(num==1) ascending = true;

        List<Product> products = inv.getProductsSortedByPrice(ascending);
        if(p!=null){
            for(Product p : products){
                System.out.println(p);
            }
        } 
        else System.out.println("Inventory is Empty");
    }

    //8
    private static void sortByQuantity(){
        System.out.println("1.Ascending");
        System.out.println("2.Descending");
        System.out.println("Enter your choice: ");
        int num = sc.nextInt();
        boolean ascending = false; 
        if(num==1) ascending = true;

        List<Product> products = inv.getProductsSortedByQuantity(ascending);
        if(p!=null){
            for(Product p : products){
                System.out.println(p);
            }
        } 
        else System.out.println("Inventory is Empty");
    }

    //9
    private static void groupByCategory(){
        Map<String, List<Product>> pMap = inv.groupProductsByCategory();

        if(pMap!=null){
            for(Map.Entry<String,List<Product>> me : pMap){
                System.out.println(me.getKey());
                List<Product> products = me.getValue();
                if(p!=null){
                    for(Product p : products){
                        System.out.println(p);
                    }
                } 
            }
        } 
        else System.out.println("Inventory is Empty");
    }

    //10
    private static void addProduct(){
        System.out.println("Enter product id: "); 
        int id = sc.nextInt();  
        System.out.println("Enter product name:");
        String name = sc.nextLine();   
        System.out.println("Enter product price:");
        double price = sc.nextDouble();   
        System.out.println("Enter product qunatity:");
        int qty = sc.nextInt();   
        System.out.println("Enter product category:");
        String cat = sc.nextLine(); 

        Product p = new Product(id, name, price, qty, cat);       
        inv.addProduct(p);       
        System.out.println("Product added successfully :" + p);
    }

    //11
    private static void increaseStock(){
        System.out.println("Enter product id: "); 
        int id = sc.nextInt();  
        System.out.println("Enter the no. of stock to be increased:");
        int qty = sc.nextInt();
        inv.increaseStock(id, qty);
        break;
    }

    //12
    private static void decreaseStock(){
        System.out.println("Enter product id: "); 
        int id = sc.nextInt();  
        System.out.println("Enter the no. of stock to be decreased:");
        int qty = sc.nextInt();
        inv.increaseStock(id, qty);
        break;
    }

    //13
    private static void viewLowStockProducts(){
        System.out.println("Enter the threshold for low stock : ");
        int threshold = sc.nextInt();

        List<Product> products = inv.getLowStockProducts(threshold);
        if(p!=null){
            for(Product p : products){
                System.out.println(p);
            }
        } 
        else System.out.println("No products found below threshold");
    }

    //14
    private static void createOrder(){
        return;
    }
}

