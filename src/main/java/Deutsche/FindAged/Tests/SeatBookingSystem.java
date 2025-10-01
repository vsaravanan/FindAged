package Deutsche.FindAged.Tests;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Sarav on 01 Oct 2025
 * @project govtech
 * @package Deutsche.FindAged.Tests
 * @class SeatBookingSystem
 */
public class SeatBookingSystem {


    private final Set<String> bookedSeats = new HashSet<>();
    private final Object lock = new Object();

    public boolean bookSeat(String seatNumber, String customerName) {
        synchronized(lock) {
            if (bookedSeats.contains(seatNumber)) {
                System.out.println("Seat " + seatNumber + " is already booked. Customer: " + customerName);
                return false;
            }

            // Simulate some processing time
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            bookedSeats.add(seatNumber);
            System.out.println("Seat " + seatNumber + " successfully booked for: " + customerName);
            return true;
        }
    }

    public int getBookedSeatsCount() {
        synchronized(lock) {
            return bookedSeats.size();
        }
    }
}