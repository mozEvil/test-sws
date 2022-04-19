CREATE TABLE persons (
    id INT auto_increment PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    middle_name VARCHAR(20),
    email VARCHAR(20),
    phone VARCHAR(20)
);

INSERT INTO persons (first_name, last_name, middle_name, email, phone)
VALUES
('Maksim', 'Mozzhevilov', 'Aleksandrovich', 'mozevil@gmail.com', '+79058255447');