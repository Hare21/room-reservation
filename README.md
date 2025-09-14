# Room Reservation (Spring Boot)

A small, production-style **room booking API** built with **Java 21** and **Spring Boot 3**.  
Focuses on clean design, concurrency safety, and preventing double-booking.

---

## ✨ Highlights

- **RESTful API** for creating reservations
- **Overlap detection** (no two bookings can overlap for the same room)
- **Transactional service layer** to keep bookings consistent
- **Optimistic locking** on reservations (safe concurrent updates)
- **Validation** on inputs (room, guest, time range)
- **Clear error model** (400 for bad input, 409 for conflicts)
- **Layered architecture** (Controller → Service → Repository → Domain)

---

## 🧭 Booking rules

- A reservation must have `start < end`
- Reservations **cannot overlap** for the **same room**
- Unknown rooms are rejected
- Conflicts return a **409 Conflict** with a human-readable message

---

## 🧱 Architecture

Controller (HTTP)
↓
Service (business rules: overlap check, transactions)
↓
Repository (persistence abstraction)
↓
Domain (Room, Reservation)

## 🔖 Tech stack
- Java 21, Spring Boot 3
- Spring Web, Spring Data JPA, Bean Validation
- JPA/Hibernate (pluggable DB)
- Maven
