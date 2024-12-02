package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text, Double minRating) {

        if (text != null && !text.isEmpty() && minRating > 0) {
            return eventRepository.findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndPopularityScoreGreaterThanEqual(
                    text, text, minRating);
        } else if (text != null && !text.isEmpty()) {
            return eventRepository.findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(text, text);
        } else if (minRating > 0) {
            return eventRepository.findAllByPopularityScoreGreaterThanEqual(minRating);
        } else {
            return eventRepository.findAll(); // Return all events if no filters are provided
        }
    }

    @Override
    public Optional<Event> save(String name, String description, Double popularityScore, Long locationId) {
        Optional<Location> location = locationRepository.findById(locationId);
        Event event = new Event(name, description, popularityScore, location.orElse(null));

        return Optional.of(this.eventRepository.save(event));
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Optional<Event> update(Long id, String name, String description, Double popularityScore, Long locationId) {
        Event event = this.eventRepository.findById(id).orElse(null);

        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        event.setLocation(locationRepository.findById(locationId).orElse(null));


        return Optional.of(this.eventRepository.save(event));
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> like(long id) {
        Event event = this.eventRepository.findById(id).orElse(null);

        event.setPopularityScore(event.getPopularityScore() +1);

        return Optional.of(eventRepository.save(event));
    }

    @Override
    public List<Event> fintByLocation_Id(long id) {
        return eventRepository.findAllByLocation_Id(id);
    }


}
