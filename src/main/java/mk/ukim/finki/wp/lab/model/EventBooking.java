package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class EventBooking {

    private String eventName;
    private String attendeeName;
    private String attendeeAddress;
    private Long numberOfTickets;

    public EventBooking(String attendeeName, String attendeeAddress, String eventName,  Long numberOfTickets) {
        this.eventName = eventName;
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTickets = numberOfTickets;
    }

}

