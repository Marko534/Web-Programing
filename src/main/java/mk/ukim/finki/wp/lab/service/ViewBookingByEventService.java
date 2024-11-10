package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.EventBooking;

import java.util.List;

public interface ViewBookingByEventService{
    public List<EventBooking> findBookingByEventName(String eventName);
}
