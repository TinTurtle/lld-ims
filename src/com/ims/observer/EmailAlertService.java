package com.ims.observer;

public class EmailAlertService implements StockObserver {
    private String emailId;

    public EmailAlertService(String emailId){
        this.emailId = emailId;
    }

    @Override
    public void update(String message){
        System.out.println("📧 Sending EMAIL to " + emailId + " | Message: " + message);
    }
}
