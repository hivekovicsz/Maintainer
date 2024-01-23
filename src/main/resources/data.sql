INSERT INTO global_user (name) VALUES ('Hivekovics Zoltán');
INSERT INTO global_user (name) VALUES ('Kovács Norbert');



INSERT INTO global_address(user_id, address_type, country, city, place_name, street_number)
VALUES ((SELECT id FROM global_user WHERE name = 'Hivekovics Zoltán'), 'PERMANET', 'Magyarország', 'Tés', 'Táncsics Mihály utca', '43');

INSERT INTO global_address(user_id, address_type, country, city, place_name, street_number)
VALUES ((SELECT id FROM global_user WHERE name = 'Kovács Norbert'), 'PERMANET', 'Magyarország', 'Budapest', 'Benczúr utca', '47');

