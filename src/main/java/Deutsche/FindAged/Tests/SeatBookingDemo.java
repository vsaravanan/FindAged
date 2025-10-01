package Deutsche.FindAged.Tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Sarav on 01 Oct 2025
 * @project govtech
 * @package Deutsche.FindAged.Tests
 * @class SeatBookingDemo
 */
public class SeatBookingDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("=== Synchronized Block Implementation ===");
        testSynchronizedBooking();

        Thread.sleep(2000);

        System.out.println("\n=== ConcurrentHashMap Implementation ===");
        testConcurrentBooking();
    }

    private static void testSynchronizedBooking() throws InterruptedException, ExecutionException {
        SeatBookingSystem system = new SeatBookingSystem();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Create multiple booking attempts for the same seats
        List<Callable<Boolean>> tasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final String seat = "A" + (i % 5); // Only 5 unique seats, creating conflicts
            final String customer = "Customer-" + i;
            tasks.add(() -> system.bookSeat(seat, customer));
        }

        // Execute all tasks
        List<Future<Boolean>> results = executor.invokeAll(tasks);

        // Wait for completion
        int successfulBookings = 0;
        for (Future<Boolean> result : results) {
            if (result.get()) {
                successfulBookings++;
            }
        }

        System.out.println("Total successful bookings: " + successfulBookings);
        System.out.println("System reports: " + system.getBookedSeatsCount() + " booked seats");

        executor.shutdown();
    }

    private static void testConcurrentBooking() throws InterruptedException, ExecutionException {
        ConcurrentSeatBookingSystem system = new ConcurrentSeatBookingSystem();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Create multiple booking attempts for the same seats
        List<Callable<Boolean>> tasks = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            final String seat = "B" + (i % 5); // Only 5 unique seats, creating conflicts
            final String customer = "Customer-" + i;
            tasks.add(() -> system.bookSeat(seat, customer));
        }

        // Add some cancellation tasks
        tasks.add(() -> system.cancelSeat("B1", "Customer-1"));
        tasks.add(() -> system.bookSeat("B1", "Customer-New"));

        // Execute all tasks
        List<Future<Boolean>> results = executor.invokeAll(tasks);

        // Wait for completion
        int successfulBookings = 0;
        for (Future<Boolean> result : results) {
            if (result.get()) {
                successfulBookings++;
            }
        }

        System.out.println("Total successful bookings: " + successfulBookings);
        System.out.println("System reports: " + system.getBookedSeatsCount() + " booked seats");
        system.displayAllBookings();

        executor.shutdown();
    }
}