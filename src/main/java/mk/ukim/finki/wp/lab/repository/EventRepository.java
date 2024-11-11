package mk.ukim.finki.wp.lab.repository;


import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    public List<Event> findAll() {
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text) {
        return DataHolder.events.stream()
                .filter(event -> event.getName().contains(text) || event.getDescription().contains(text))
                .collect(Collectors.toList());
    }

}
