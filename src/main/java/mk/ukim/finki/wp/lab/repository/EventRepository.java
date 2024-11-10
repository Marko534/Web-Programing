package mk.ukim.finki.wp.lab.repository;


import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    private final List<Event> events = new ArrayList<>(List.of(
            new Event("Event 1", "Description of Event 1", 8.5),
            new Event("Event 2", "Description of Event 2", 7.2),
            new Event("Event 3", "Description of Event 3", 6.8),
            new Event("Event 4", "Description of Event 4", 9.1),
            new Event("Event 5", "Description of Event 5", 5.4),
            new Event("Event 6", "Description of Event 6", 7.7),
            new Event("Event 7", "Description of Event 7", 8.0),
            new Event("Event 8", "Description of Event 8", 9.3),
            new Event("Event 9", "Description of Event 9", 6.5),
            new Event("Event 10", "Description of Event 10", 8.8)
    ));

    public List<Event> findAll() {
        return events;
    }

    public List<Event> searchEvents(String text) {
        return events.stream()
                .filter(event -> event.getName().contains(text) || event.getDescription().contains(text))
                .collect(Collectors.toList());
    }

}
