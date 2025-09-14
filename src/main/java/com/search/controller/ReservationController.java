package com.search.controller;

import com.search.model.Reservation;
import com.search.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/api")
public class ReservationController {

    private final ReservationService svc;

    public ReservationController(ReservationService svc) {
        this.svc = svc;
    }

    public record BookRequest(Integer roomNumber, String guestName,
                              OffsetDateTime start, OffsetDateTime end) {}

    @PostMapping("/reservations")
    public Reservation create(@RequestBody BookRequest req) {
        return svc.book(req.roomNumber(), req.guestName(), req.start(), req.end());
    }
}
