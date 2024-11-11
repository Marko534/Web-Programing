package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.EventBooking;

import java.util.List;

public interface EventBookingService{
    EventBooking placeBooking(String attendeeName, String attendeeAddress, String eventName, int numberOfTickets);
    List<EventBooking> getAllBookings();
    List<EventBooking> getBookingsByEventName(String eventName);
    List<EventBooking> getBookingsByAttendeeName(String attendeeName);
}