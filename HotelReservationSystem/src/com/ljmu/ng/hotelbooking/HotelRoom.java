package com.ljmu.ng.hotelbooking;

public class HotelRoom {

	public int roomNum;
	public String roomType;
	public boolean balcony;
	public boolean lounge;
	public double price;
	public boolean isBooked;
	public String reservationDetail;

    public HotelRoom(int roomNum, String roomType, boolean balcony, boolean lounge, double price, boolean isBooked, String reservationDetail) {
        this.roomNum = roomNum;
        this.roomType = roomType;
        this.balcony = balcony;
        this.lounge = lounge;
        this.price = price;
        this.isBooked = isBooked;
        this.reservationDetail = reservationDetail;
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

    public String getReservationDetail() {
		return reservationDetail;
	}

	@Override
    public String toString() {
        return "HotelRoom{" +
                "roomNum=" + roomNum +
                ", roomType='" + roomType + '\'' +
                ", balcony=" + balcony +
                ", lounge=" + lounge +
                ", price=" + price +
                ", reservationDetail=" + reservationDetail +
                
                '}';
    }
}
