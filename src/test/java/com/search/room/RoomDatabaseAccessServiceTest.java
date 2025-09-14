package com.search.room;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoomDatabaseAccessServiceTest {

    @Test
    void testLoadRoom_ValidRoom_ReturnsRoom() {
        RoomDatabaseAccessService service = RoomDatabaseAccessService.getInstance();
        Room room = service.loadRoom(101);
        assertNotNull(room, "Room 101 should exist");
        assertEquals(101, room.getRoomNumber(), "Room number should match");
    }

    @Test
    void testLoadRoom_InvalidRoom_ReturnsNull() {
        RoomDatabaseAccessService service = RoomDatabaseAccessService.getInstance();
        Room room = service.loadRoom(999); // non-existent room
        assertNull(room, "Non-existent room should return null");
    }

    @Test
    void testSingletonInstance_IsSame() {
        RoomDatabaseAccessService instance1 = RoomDatabaseAccessService.getInstance();
        RoomDatabaseAccessService instance2 = RoomDatabaseAccessService.getInstance();
        assertSame(instance1, instance2, "Both instances should be the same (singleton)");
    }

    @Test
    void testInitialRoomAvailability() {
        RoomDatabaseAccessService service = RoomDatabaseAccessService.getInstance();
        Room room = service.loadRoom(106); // this room is initialized as available=true
        assertNotNull(room);
        assertTrue(room.isAvailable(), "Room 106 should initially be available");
    }

    @Test
    void testRoomBookingChangesAvailability() {
        RoomDatabaseAccessService service = RoomDatabaseAccessService.getInstance();
        Room room = service.loadRoom(102);
        assertNotNull(room);
        boolean booked = room.bookRoom();
        assertTrue(booked, "Room should be booked successfully");
        assertFalse(room.isAvailable(), "Room should now be marked as unavailable");
    }

    @Test
    void testBookingAlreadyBookedRoom_Fails() {
        RoomDatabaseAccessService service = RoomDatabaseAccessService.getInstance();
        Room room = service.loadRoom(103); // Already initialized as unavailable
        assertNotNull(room);
        boolean booked = room.bookRoom();
        assertFalse(booked, "Booking an already booked room should fail");
    }

    @Test
    void testUnbookingAvailableRoom_Fails() {
        RoomDatabaseAccessService service = RoomDatabaseAccessService.getInstance();
        Room room = service.loadRoom(104);
        assertNotNull(room);
        assertTrue(room.isAvailable());

        boolean unbooked = room.unbookRoom();
        assertFalse(unbooked, "Unbooking an already available room should fail");
    }

}
