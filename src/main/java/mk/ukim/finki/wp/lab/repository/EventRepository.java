package mk.ukim.finki.wp.lab.repository;


import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {

    public List<Event> findAll() {
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text, double minScore) {
        List<Event> events = new ArrayList<>();

        if (text != null && !text.isEmpty()) {
            events = DataHolder.events.stream()
                    .filter(event -> event.getName().contains(text) || event.getDescription().contains(text))
                    .toList();
        } else {
            events = DataHolder.events;
        }

        if (minScore > 0) {
            events = events.stream()
                    .filter(x -> x.getPopularityScore() >= minScore)
                    .toList();
        }

        return events;
    }

    public Optional<Event> addEvent(String name, String description, Double popularityScore, Long locationId) {
        Optional<Location> location= DataHolder.locations.stream().filter(x -> x.getId().equals(locationId)).findFirst();
        Event event=new Event(name,description,popularityScore, location.orElse(null));
        if(event != null) {
            DataHolder.events.add(event);
        }
        return Optional.of(event);
    }

    public Optional<Event>findById(Long id) {
        return DataHolder.events.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    public Optional<Event>update(Long id, String name, String description,  Double popularityScore,  Long locationId)
    {
        Optional<Location> location =DataHolder.locations.stream().filter(x -> x.getId().equals(locationId)).findFirst();
        Event event=findById(id).get();
        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        if (location.isPresent()) {
            event.setLocation(location.get());
        }else {
            throw new RuntimeException();
        }
        return Optional.of(event);
    }
    public Optional<Event>delete(Long id)
    {
        Event event=findById(id).get();
//        DataHolder.events.removeIf(i->i.getId().equals(id));
        DataHolder.events.remove(event);
        return Optional.of(event);
    }

    public Optional<Event>like(Long id){
        Event event=findById(id).get();
        event.setPopularityScore(event.getPopularityScore()+1);
        return Optional.of(event);
    }

}
