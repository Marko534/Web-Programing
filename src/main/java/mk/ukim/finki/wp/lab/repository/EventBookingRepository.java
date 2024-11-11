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

    public EventBooking save(EventBooking eventBooking) {
        DataHolder.eventBookings.add(eventBooking);
        return eventBooking;
    }

    public List<EventBooking> findAll() {
        return DataHolder.eventBookings;
    }

    public List<EventBooking> findByEventName(String eventName) {
        return DataHolder.eventBookings.stream()
                .filter(eventBooking -> eventBooking.getEventName().contains(eventName))
                .toList();
    }

    public List<EventBooking> findByAttendeeName(String attendeeName) {
        return DataHolder.eventBookings.stream()
                .filter(booking -> booking.getAttendeeName().equals(attendeeName))
                .toList();
    }



}
