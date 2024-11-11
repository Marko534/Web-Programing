package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Location> locations = new ArrayList<>();
    public static List<Event> events = new ArrayList<>();
    public static List<EventBooking> eventBookings = new ArrayList<>();

    @PostConstruct
    public void initData() {
        // Initialize Locations
        locations.add(new Location("Location 1", "123 Main St", "100", "A great place to host events"));
        locations.add(new Location("Location 2", "456 Elm St", "200", "Spacious and modern"));
        locations.add(new Location("Location 3", "789 Oak St", "150", "Classic venue with charm"));
        locations.add(new Location("Location 4", "101 Pine St", "80", "Cozy and intimate"));
        locations.add(new Location("Location 5", "202 Maple St", "300", "Perfect for large gatherings"));

        // Initialize Events
        for (int i = 1; i <= 10; i++) {
            Location location = locations.get(i % locations.size()); // Cycle through locations
            events.add(new Event(
                    "Event " + i,
                    "Description for Event " + i,
                    Math.random() * 100, // Random popularity score
                    location
            ));
        }

        // Initialize EventBookings
        for (int i = 1; i <= 15; i++) {
            Event event = events.get(i % events.size()); // Cycle through events
            eventBookings.add(new EventBooking(
                    "Attendee " + i,
                    "Address " + i,
                    event.getName(),
                    (long) (Math.random() * 10 + 1) // Random number of tickets
            ));
        }

        // Print Initialized Data
        printData();
    }

    private void printData() {
        System.out.println("Locations:");
        locations.forEach(System.out::println);

        System.out.println("\nEvents:");
        events.forEach(System.out::println);

        System.out.println("\nEvent Bookings:");
        eventBookings.forEach(System.out::println);
    }

    // Getter methods for testing purposes (optional)
    public List<Location> getLocations() {
        return locations;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<EventBooking> getBookings() {
        return eventBookings;
    }
}
