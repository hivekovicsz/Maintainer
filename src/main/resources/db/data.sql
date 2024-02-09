--liquibase formatted sql

--changeset data:1 context:test


INSERT INTO global_user (name) VALUES ('Hivekovics Zoltán');
INSERT INTO global_user (name) VALUES ('Kovács Norbert');
INSERT INTO global_user (name) VALUES ('Törlendő felhasználó');



INSERT INTO global_address(user_id, address_type, country, city, place_name, street_number)
VALUES ((SELECT id FROM global_user WHERE name = 'Hivekovics Zoltán'), 'PERMANET', 'Magyarország', 'Tés', 'Táncsics Mihály utca', '43');

INSERT INTO global_address(user_id, address_type, country, city, place_name, street_number)
VALUES ((SELECT id FROM global_user WHERE name = 'Kovács Norbert'), 'PERMANET', 'Magyarország', 'Budapest', 'Benczúr utca', '47');

INSERT INTO global_address(user_id, address_type, country, city, place_name, street_number)
VALUES ((SELECT id FROM global_user WHERE name = 'Kovács Norbert'), 'TEMPORARY', 'Magyarország', 'Budapest', 'Törlendő utca', '1');


INSERT INTO global_contact(address_id, email, phone_number)
VALUES ((SELECT id from global_address WHERE user_id = (SELECT id FROM global_user WHERE name = 'Hivekovics Zoltán') and address_type = 'PERMANET'), 'hivekovicsz@gmail.com', '+36301234567');

INSERT INTO global_contact(address_id, email, phone_number)
VALUES ((SELECT id from global_address WHERE user_id = (SELECT id FROM global_user WHERE name = 'Hivekovics Zoltán') and address_type = 'PERMANET'), 'torlendo@gmail.com', '+36307654321');

