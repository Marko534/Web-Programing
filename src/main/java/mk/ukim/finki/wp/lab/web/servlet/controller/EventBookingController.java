package mk.ukim.finki.wp.lab.web.servlet.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {
    private final EventBookingService eventBookingService;

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }
    @GetMapping
    public String getBookingPage(Model model) {
        return "bookingConfirmation";
    }
    @PostMapping
    public String addBooking(@RequestParam String eventName, @RequestParam String attendeeName, @RequestParam String numTickets, HttpServletRequest request, Model model)
    {
        String attendeeAddress = request.getRemoteAddr();
        EventBooking booking = eventBookingService.placeBooking(attendeeName, attendeeAddress, eventName, Integer.parseInt(numTickets));
        model.addAttribute("booking", booking);
        return "bookingConfirmation";

    }
}