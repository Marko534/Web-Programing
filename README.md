# Lab 4 Web Programing 

## Startup 

### Start database for Linux

```bash
  sudo systemctl start postgresql
```

### Populate database for testing

```sql
INSERT INTO public.location (address, capacity, description, name)
VALUES ('123 Main Street, Springfield', '500', 'Main Event Hall', 'Grand Hall'),
       ('456 Elm Street, Shelbyville', '200', 'Small Conference Room', 'Elm Conference Center'),
       ('789 Maple Avenue, Capital City', '1000', 'Outdoor Venue with a scenic view', 'Maple Park'),
       ('101 Pine Street, Ogdenville', '300', 'Indoor Sports Arena', 'Pine Arena');


INSERT INTO public.event (description, name, popularity_score, location_id)
VALUES ('An evening of jazz music featuring local artists.', 'Jazz Night', 8.5, 1),
       ('A conference on AI advancements and their societal impacts.', 'AI Summit 2024', 9.0, 2),
       ('Outdoor movie screening of popular classics.', 'Movie Under the Stars', 7.8, 3),
       ('A community yoga session followed by wellness talks.', 'Yoga for All', 6.7, 4);


INSERT INTO public.event_booking (attendee_address, attendee_name, event_name, number_of_tickets)
VALUES ('555 Cherry Lane, Springfield', 'John Doe', 'Jazz Night', 2),
       ('789 Oak Avenue, Shelbyville', 'Jane Smith', 'AI Summit 2024', 1),
       ('101 Birch Drive, Capital City', 'Alice Johnson', 'Movie Under the Stars', 4),
       ('202 Cedar Road, Ogdenville', 'Bob Brown', 'Yoga for All', 1);

```
