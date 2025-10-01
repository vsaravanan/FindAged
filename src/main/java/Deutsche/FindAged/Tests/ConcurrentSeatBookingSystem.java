package Deutsche.FindAged.Tests;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sarav on 01 Oct 2025
 * @project govtech
 * @package Deutsche.FindAged.Tests
 * @class ConcurrentSeatBookingSystem
 */
public class ConcurrentSeatBookingSystem {

    private final ConcurrentHashMap<String, String> seatAssignments = new ConcurrentHashMap<>();
    private final AtomicInteger bookingCounter = new AtomicInteger(0);

    public boolean bookSeat(String seatNumber, String customerName) {
        // Use putIfAbsent for atomic check-and-set operation
        String previousOwner = seatAssignments.putIfAbsent(seatNumber, customerName);

        if (previousOwner != null) {
            System.out.println("Seat " + seatNumber + " is already booked by: " + previousOwner +
                    ". Attempt by: " + customerName);
            return false;
        }

        // Simulate some processing time
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // Rollback the booking if interrupted
            seatAssignments.remove(seatNumber, customerName);
            return false;
        }

        bookingCounter.incrementAndGet();
        System.out.println("Seat " + seatNumber + " successfully booked for: " + customerName);
        return true;
    }

    public boolean cancelSeat(String seatNumber, String customerName) {
        return seatAssignments.remove(seatNumber, customerName);
    }

    public int getBookedSeatsCount() {
        return bookingCounter.get();
    }

    public void displayAllBookings() {
        System.out.println("\nCurrent Bookings:");
        seatAssignments.forEach((seat, customer) ->
                System.out.println("Seat " + seat + " -> " + customer));
    }
}