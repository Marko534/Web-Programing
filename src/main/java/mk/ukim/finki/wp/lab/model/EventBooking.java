package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class EventBooking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String attendeeName;
    private String attendeeAddress;
    private Long numberOfTickets;

    public EventBooking(String attendeeName, String attendeeAddress, String eventName,  Long numberOfTickets) {
        this.id = (long) (Math.random() * 1000);
        this.eventName = eventName;
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTickets = numberOfTickets;
    }

}

