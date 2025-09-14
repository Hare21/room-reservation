package com.search.service;

import com.search.model.*;
import com.search.repo.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ReservationService {

    private final RoomRepository roomRepo;
    private final ReservationRepository resRepo;

    public ReservationService(RoomRepository roomRepo, ReservationRepository resRepo) {
        this.roomRepo = roomRepo;
        this.resRepo = resRepo;
    }

    @Transactional
    public Reservation book(Integer roomNumber, String guestName,
                            OffsetDateTime start, OffsetDateTime end) {
        if (roomNumber == null || guestName == null || start == null || end == null)
            throw new IllegalArgumentException("Missing required fields");
        if (!start.isBefore(end))
            throw new IllegalArgumentException("Invalid time range (start must be before end)");

        Room room = roomRepo.findByNumber(roomNumber)
                .orElseThrow(() -> new IllegalArgumentException("Unknown room: " + roomNumber));

        long conflicts = resRepo.countOverlapping(room.getId(), start, end);
        if (conflicts > 0)
            throw new IllegalStateException("Room not available for the given time range");

        Reservation r = Reservation.builder()
                .room(room)
                .guestName(guestName)
                .startTime(start)
                .endTime(end)
                .build();

        return resRepo.save(r);
    }
}
