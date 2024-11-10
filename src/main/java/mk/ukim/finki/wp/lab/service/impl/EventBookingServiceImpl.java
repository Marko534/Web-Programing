package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.EventBookingRepository;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final EventBookingRepository eventBookingRepository;

    public EventBookingServiceImpl(EventBookingRepository eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    @Override
    public EventBooking placeBooking (String attendeeName, String attendeeAddress, String eventName, int numberOfTickets) {
        EventBooking eventBooking = new EventBooking( attendeeName, attendeeAddress, eventName, (long) numberOfTickets);
        eventBookingRepository.addEventBooking(eventBooking);

        return eventBooking;
    }
}
