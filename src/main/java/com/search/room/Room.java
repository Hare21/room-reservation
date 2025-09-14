package com.search.room;

public class Room {
    private int roomNumber;
    private RoomType roomType;
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, RoomType roomType, double price, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Room() {

    }
    public synchronized  boolean bookRoom() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean unbookRoom() {
        if (!isAvailable) {
            isAvailable = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
