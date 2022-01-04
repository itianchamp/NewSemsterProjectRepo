package com.example.foodorderingapp.Models;

public class OrdersModel {

    int orderimage;

    String solditemname, ordersampleprice, ordernumbers;

    public OrdersModel() {
        this.orderimage = orderimage;
        this.solditemname = solditemname;
        this.ordersampleprice = ordersampleprice;
        this.ordernumbers = ordernumbers;
    }

    public int getOrderimage() {
        return orderimage;
    }

    public void setOrderimage(int orderimage) {
        this.orderimage = orderimage;
    }

    public String getSolditemname() {
        return solditemname;
    }

    public void setSolditemname(String solditemname) {
        this.solditemname = solditemname;
    }

    public String getOrdersampleprice() {
        return ordersampleprice;
    }

    public void setOrdersampleprice(String ordersampleprice) {
        this.ordersampleprice = ordersampleprice;
    }

    public String getOrdernumbers() {
        return ordernumbers;
    }

    public void setOrdernumbers(String ordernumbers) {
        this.ordernumbers = ordernumbers;
    }
}
