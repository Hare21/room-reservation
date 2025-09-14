package com.search.repo;

import com.search.model.Reservation;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.time.OffsetDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("""
    SELECT COUNT(r) FROM Reservation r
    WHERE r.room.id = :roomId
      AND NOT (r.endTime <= :start OR r.startTime >= :end)
  """)
    long countOverlapping(@Param("roomId") Long roomId,
                          @Param("start") OffsetDateTime start,
                          @Param("end") OffsetDateTime end);
}
