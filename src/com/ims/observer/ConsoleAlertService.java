package com.ims.observer;

public class ConsoleAlertService implements StockObserver{
    
    @Override
    public void update(String message){
        System.out.println("🖥️ Console Alert: " + message);
    }
}
