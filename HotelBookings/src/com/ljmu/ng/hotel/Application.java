package com.ljmu.ng.hotel;

import javax.sound.midi.SysexMessage;
import javax.swing.plaf.synth.SynthMenuBarUI;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main (String args[]) {

        HotelRoom hotelRoom1 = new HotelRoom(201,"Double", true, false, 25.50);
        HotelRoom hotelRoom2 = new HotelRoom(301,"Single", false, true, 15.00);

        System.out.print("Hotel room price: " + hotelRoom1.getPrice() + "\n\n");
        System.out.println(hotelRoom1.toString());
        System.out.println(hotelRoom2.toString());



        List<HotelRoom> hotelRoomList = new ArrayList<>();

        hotelRoomList.add(hotelRoom1);
        hotelRoomList.add(hotelRoom2);

        System.out.println("Hotel 1 = " + hotelRoomList.get(0).toString());

        System.out.println("\n\nHotel Room List Size = " + hotelRoomList.size());

    }
}
