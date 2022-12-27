package com.example.winelistapp.Model;


import java.util.List;

public class Request {
    private String phone;
    private String name;
    private List<Winelist> winelists; //List of wines in winelist

    public Request() {
    }

    public Request(String phone, String name, List<Winelist> winelists) {
        this.phone = phone;
        this.name = name;
        this.winelists = winelists;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public List<Winelist> getWinelists() {
        return winelists;
    }

    public void setWinelists(List<Winelist> winelists) {
        this.winelists = winelists;
    }
}
