# Lab 3 Web Programing 

## Startup 

### Start database 

```bash
  sudo systemctl start postgresql
```

### Populate database for testing

```sql
INSERT INTO location (name, address, capacity, description)
VALUES ('Location 1', '123 Main St', '100', 'A great place to host events'),
       ('Location 2', '456 Elm St', '200', 'Spacious and modern'),
       ('Location 3', '789 Oak St', '150', 'Classic venue with charm'),
       ('Location 4', '101 Pine St', '80', 'Cozy and intimate'),
       ('Location 5', '202 Maple St', '300', 'Perfect for large gatherings');
```