package com.example.winelistapp.Model;

public class Winelist {
    private int ID;
    private String WineID;
    private String WineLabel;
    private String Quantity;
    private String Price;
    private String Discount;
    private String Total;

    public Winelist() {
    }

    public Winelist(String wineID, String wineLabel, String quantity, String price, String discount, String total) {
        WineID = wineID;
        WineLabel = wineLabel;
        Quantity = quantity;
        Price = price;
        Discount = discount;
        Total = total;
    }

    public Winelist(int ID, String wineID, String wineLabel, String quantity, String price, String discount, String total) {
        this.ID = ID;
        WineID = wineID;
        WineLabel = wineLabel;
        Quantity = quantity;
        Price = price;
        Discount = discount;
        Total = total;
    }

    public int getID() { return ID; }

    public void setID(int ID) { this.ID = ID; }

    public String getWineID() {
        return WineID;
    }

    public void setWineID(String wineID) {
        WineID = wineID;
    }

    public String getWineLabel() {
        return WineLabel;
    }

    public void setWineLabel(String wineLabel) {
        WineLabel = wineLabel;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

}
