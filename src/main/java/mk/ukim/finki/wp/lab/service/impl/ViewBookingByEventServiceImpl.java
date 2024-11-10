package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.EventBookingRepository;
import mk.ukim.finki.wp.lab.service.ViewBookingByEventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewBookingByEventServiceImpl implements ViewBookingByEventService {
   private final EventBookingRepository eventBookingRepository;

    public ViewBookingByEventServiceImpl(EventBookingRepository eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    @Override
    public List<EventBooking> findBookingByEventName(String eventName) {
        return this.eventBookingRepository.getEventBookings(eventName);
    }
}
