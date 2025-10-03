package com.ims;

import com.ims.service.*;
import com.ims.model.*;
import com.ims.observer.*;
import com.ims.order.*;
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
                    
                    break;
                
                case 3:// search using partial name
                    break;

                case 4:// search using category
                    break;

                case 5:// search using price range
                    break;
                case 6://view available products
                    break;
                case 7://sort by price
                    break;
                case 8://sort by qty
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    running = false;
                    break;
                default:
                    break;
            }
        }
    } 

    //Methods to call inside the cases
    private static void searchByName(){
        String name = sc.nextLine();
        Product p = inv.searchProduct(name);
        if(p!=null) System.out.println(p);
        else System.out.println("Prodcut Not Found");
    }

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

    private static void increaseStock(){
        System.out.println("Enter product id: "); 
        int id = sc.nextInt();  
        System.out.println("Enter the no. of stock to be increased:");
        int qty = sc.nextInt();
        inv.increaseStock(id, qty);
        break;
    }

    private static void decreaseStock(){
        System.out.println("Enter product id: "); 
        int id = sc.nextInt();  
        System.out.println("Enter the no. of stock to be decreased:");
        int qty = sc.nextInt();
        inv.increaseStock(id, qty);
        break;
    }
    private void 
    private void 
}
