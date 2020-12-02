package com.ljmu.ng.hotel;

public class HotelRoom {

    int roomNum;
    String roomType;
    boolean balcony;
    boolean lounge;
    double price;
    boolean isBooked;
    String emailAddress;

    public HotelRoom(int roomNum, String roomType, boolean balcony, boolean lounge, double price, boolean isBooked, String emailAddress) {
        this.roomNum = roomNum;
        this.roomType = roomType;
        this.balcony = balcony;
        this.lounge = lounge;
        this.price = price;
        this.isBooked = isBooked;
        this.emailAddress = emailAddress;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public boolean isLounge() {
        return lounge;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "HotelRoom{" +
                "roomNum=" + roomNum +
                ", roomType='" + roomType + '\'' +
                ", balcony=" + balcony +
                ", lounge=" + lounge +
                ", price=" + price +
                '}';
    }
}
