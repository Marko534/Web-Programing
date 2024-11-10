package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Event {

    private String name;
    private String description;
    private double popularityScore;

    public Event(String s, String s1, double v) {
        this.name = s;
        this.description = s;
        this.popularityScore = v;
    }
}
