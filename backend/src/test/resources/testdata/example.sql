
INSERT INTO public.location (id, city, country, house_nr, latitude, longitude, street) VALUES (1, 'Vienna', 'Austia', '1', 123.123, 123.123, 'Teststreet');
INSERT INTO public.regular_user (id, active, email, name, password_hash, phone_nr, location_id) VALUES (1, true, 'test@test.com', 'Testname', '9999', '123', 1);
INSERT INTO public.auction_house (id, active, email, name, password_hash, phone_nr, serial_nr, location_id) VALUES (1, true, 'test@test.com', 'Testname', '9999', '123', 123, 1);
INSERT INTO public.auction_post (id, category, end_time, min_price, start_time, status, creator, location_id) VALUES (1, 'ELECTRONICS', '2020-12-31 00:00:00', 3, '2020-12-31 00:00:00', 'ACTIVE', 1, 1);