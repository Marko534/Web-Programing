package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Find events by name or description containing a text (case-insensitive)
    List<Event> findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nameText, String descriptionText);

    // Find events with a minimum popularity score
    List<Event> findAllByPopularityScoreGreaterThanEqual(double minScore);

    // Combined search for text in name/description and minimum popularity score
    List<Event> findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndPopularityScoreGreaterThanEqual(
            String nameText, String descriptionText, double minScore);
}