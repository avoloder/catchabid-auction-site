

INSERT INTO public.location (id, city, country, house_nr, latitude, longitude, street) VALUES (1, 'Vienna', 'Austia', '1', 123.123, 123.123, 'Teststreet');

INSERT INTO public.regular_user (id, active, email, name, password_hash, phone_nr, location_id) VALUES (1, true, 'test@test.com', 'Testname', '9999', '123', 1);

INSERT INTO public.auction_house (id, active, email, name, password_hash, phone_nr, serial_nr, location_id) VALUES (1, true, 'test@test.com', 'Testname', '9999', '123', '123', 1);
INSERT INTO public.auction_house (id, active, email, name, password_hash, phone_nr, serial_nr, location_id) VALUES (2, true, 'test@test.com', 'Max', '3333', '312', '312', 1);

INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id, location_id) VALUES (1, 'ELECTRONICS', '2020-12-10 00:00:00.000000', 3, '2020-12-31 00:00:00.000000', 'ACTIVE', 1, null, 1);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id, location_id) VALUES (2, 'CAR', '2020-12-12 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null, 1);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id, location_id) VALUES (3, 'CAR', '2020-12-13 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null, 1);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id, location_id) VALUES (4, 'CAR', '2020-12-14 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null, 1);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id, location_id) VALUES (5, 'CAR', '2020-12-15 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null, 1);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id, location_id) VALUES (6, 'CAR', '2020-12-16 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null, 1);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id, location_id) VALUES (7, 'CAR', '2020-12-17 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null, 1);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id, location_id) VALUES (8, 'CAR', '2020-12-18 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null, 1);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id, location_id) VALUES (9, 'CAR', '2020-12-20 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null, 1);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id, location_id) VALUES (10, 'CAR', '2020-12-31 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null, 1);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id, location_id) VALUES (11, 'CAR', '2020-12-24 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null, 1);

INSERT INTO public.bid (id, date_time, offer, auction_id, user_id) VALUES (1, '2020-11-21 21:25:27.624000', 45, 1, 1);
INSERT INTO public.bid (id, date_time, offer, auction_id, user_id) VALUES (2, '2020-11-21 21:25:27.624000', 32, 2, 1);

