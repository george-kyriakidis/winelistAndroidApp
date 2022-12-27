package com.example.winelistapp.Model;

public class Wine {
    private String Description, Discount, Name, Price, WineryId;

    public Wine() {
    }

    public Wine(String description, String discount, String name, String price, String wineryId) {
        Description = description;
        Discount = discount;
        Name = name;
        Price = price;
        WineryId = wineryId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getWineryId() {
        return WineryId;
    }

    public void setWineryId(String wineryId) {
        WineryId = wineryId;
    }
}
