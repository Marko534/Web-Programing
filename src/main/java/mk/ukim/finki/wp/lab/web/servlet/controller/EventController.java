package mk.ukim.finki.wp.lab.web.servlet.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = {"/","/events"})
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;
    private final EventBookingService eventBookingService;

    public EventController(EventService eventService, LocationService locationService, EventBookingService eventBookingService) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.eventBookingService = eventBookingService;
    }

    @GetMapping
    public String getEventsPage(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) Double minScore,
            @RequestParam(required = false) String error,
            Model model) {

        List<Event> eventList;

        if (minScore == null) {
            minScore = 0.0;
        }
        eventList = eventService.searchEvents(searchText, minScore);


        model.addAttribute("searchText", searchText);
        model.addAttribute("minRating", minScore);

        model.addAttribute("bookings", eventBookingService);
        model.addAttribute("events", eventList);
        model.addAttribute("error", error);
        return "listEvents";
    }

    @GetMapping("/add")
    public String showAddEventForm(Model model) {
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("event", new Event());
        return "addEvent";
    }

    @GetMapping("/{id}/edit")
    public String showEditEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID:" + id));
        model.addAttribute("event", event);
        model.addAttribute("locations", locationService.findAll());
        return "addEvent";
    }
    @GetMapping("/edit/{eventId}")
    public String editEvent(@PathVariable Long eventId, Model model) {
        // Retrieve the event based on the ID
        Optional<Event> eventOpt = eventService.findById(eventId);

        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();
            model.addAttribute("event", event);
            model.addAttribute("locations", locationService.findAll()); // Provide locations for the dropdown
            return "addEvent"; // This reuses the add-event.html form
        } else {
            // If no event found, redirect with an error message
            model.addAttribute("error", "Event with the specified ID does not exist.");
            return "redirect:/events";
        }
    }

    @PostMapping("/save")
    public String saveOrUpdateEvent(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double popularityScore,
            @RequestParam Long locationId) {

        if (id == null) {
            eventService.save(name, description, popularityScore, locationId);
        } else {
            eventService.update(id, name, description, popularityScore, locationId);
        }
        return "redirect:/events";
    }

    @PostMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return "redirect:/events";
    }

    @PostMapping("/{id}/like")
    public String likeEvent(@PathVariable Long id) {
        eventService.like(id);
        return "redirect:/events";
    }

}
