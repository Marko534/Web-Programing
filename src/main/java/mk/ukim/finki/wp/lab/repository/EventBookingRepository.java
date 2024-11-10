package mk.ukim.finki.wp.lab.repository;


import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventBookingRepository {
    private List<EventBooking> eventBookings;

    public EventBookingRepository() {
        eventBookings = new ArrayList<>();
    }

    public void addEventBooking(EventBooking eventBooking) {
        eventBookings.add(eventBooking);
    }

    public List<EventBooking> getEventBookings(String eventName) {
        return eventBookings.stream()
                .filter(eventBooking -> eventBooking.getEventName().contains(eventName))
                .collect(Collectors.toList());
    }
}
