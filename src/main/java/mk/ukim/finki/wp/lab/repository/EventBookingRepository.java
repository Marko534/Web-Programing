package mk.ukim.finki.wp.lab.repository;


import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventBookingRepository {


    public void addEventBooking(EventBooking eventBooking) {
        DataHolder.eventBookings.add(eventBooking);
    }

    public List<EventBooking> getEventBookings(String eventName) {
        return DataHolder.eventBookings.stream()
                .filter(eventBooking -> eventBooking.getEventName().contains(eventName))
                .collect(Collectors.toList());
    }
}
