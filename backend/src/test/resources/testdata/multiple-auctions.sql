

INSERT INTO public.regular_user (id, active, email, first_name,last_name, password_hash, phone_nr) VALUES (2, true, 'test@test.com', 'Testname','TestLastName', '9999', '123');

INSERT INTO public.auction_house (id, active, email, name, password_hash, phone_nr, tid) VALUES (1, true, 'test@test.com', 'Testname', '9999', '123', '123');
INSERT INTO public.auction_house (id, active, email, name, password_hash, phone_nr, tid) VALUES (3, true, 'test1@test.com', 'Max', '3333', '312', '312');

INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id) VALUES (1, 'ELECTRONICS', '2020-11-10 00:00:00.000000', 3, '2020-12-31 00:00:00.000000', 'ACTIVE', 1, null);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id) VALUES (2, 'CARS', '2020-11-12 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id) VALUES (3, 'CARS', '2020-11-13 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id) VALUES (4, 'CARS', '2020-11-14 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id) VALUES (5, 'CARS', '2020-11-15 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id) VALUES (6, 'CARS', '2020-11-16 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id) VALUES (7, 'CARS', '2020-11-17 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id) VALUES (8, 'CARS', '2020-11-18 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id) VALUES (9, 'CARS', '2020-11-20 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id) VALUES (10, 'CARS', '2020-11-21 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null);
INSERT INTO public.auction_post (id, category, start_time, min_price, end_time, status, creator, bid_id) VALUES (11, 'CARS', '2020-11-22 00:00:00.000000', 4, '2020-12-26 00:00:00.000000', 'ACTIVE', 1, null);

INSERT INTO public.bid (id, date_time, offer, auction_id, user_id) VALUES (1, '2020-11-21 21:25:27.624000', 45, 1, 2);
INSERT INTO public.bid (id, date_time, offer, auction_id, user_id) VALUES (2, '2020-11-21 21:25:27.624000', 32, 2, 2);

