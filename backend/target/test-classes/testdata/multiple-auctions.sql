

INSERT INTO public.regular_user (id, active, email, first_name,last_name, password_hash, phone_nr, country, city, street, house_nr) VALUES (2, true, 'test@test.com', 'Testname','TestLastName', '9999', '123', 'Austria', 'Vienna', 'Resselgasse', 1);

INSERT INTO public.auction_house (id, active, email, name, password_hash, phone_nr, tid, country, city, street, house_nr) VALUES (1, true, 'test@test.com', 'Testname', '9999', '123', '123', 'Austria', 'Vienna', 'Resselgasse', 1);
INSERT INTO public.auction_house (id, active, email, name, password_hash, phone_nr, tid, country, city, street, house_nr) VALUES (3, true, 'test1@test.com', 'Max', '3333', '312', '312', 'Austria', 'Vienna', 'Resselgasse', 1);

<<<<<<< HEAD
INSERT INTO public.auction_post (id, category, name, start_time, min_price, end_time, status, creator, bid_id, country, city, street, house_nr) VALUES (1, 'ELECTRONICS', 'Leica 5D', current_timestamp - (10 * interval '1 hour'), 3, current_timestamp  + (4 * interval '1 hour'), 'ACTIVE', 2, null, 'Austria', 'Vienna', 'Resselgasse', 1);
=======
INSERT INTO public.auction_post (id, category, name, start_time, min_price, end_time, status, creator, bid_id, country, city, street, house_nr) VALUES (1, 'ELECTRONICS', 'Leica 5D', current_timestamp - (10 * interval '1 hour'), 3, current_timestamp  + (4 * interval '1 hour'), 'ACTIVE', 1, null, 'Austria', 'Vienna', 'Resselgasse', 1);
>>>>>>> dev
INSERT INTO public.auction_post (id, category, name, start_time, min_price, end_time, status, creator, bid_id, country, city, street, house_nr) VALUES (2, 'CARS', 'BMW X6', current_timestamp - (10 * interval '1 hour'), 4, current_timestamp + (4 * interval '1 hour'), 'ACTIVE', 1, null, 'Austria', 'Vienna', 'Resselgasse', 1);
INSERT INTO public.auction_post (id, category, name, start_time, min_price, end_time, status, creator, bid_id, country, city, street, house_nr) VALUES (3, 'CARS', 'BMW X6', current_timestamp - (10 * interval '1 hour'), 4, current_timestamp + (4 * interval '1 hour'), 'ACTIVE', 1, null, 'Austria', 'Vienna', 'Resselgasse', 1);
INSERT INTO public.auction_post (id, category, name, start_time, min_price, end_time, status, creator, bid_id, country, city, street, house_nr) VALUES (4, 'CARS', 'BMW X6', current_timestamp - (10 * interval '1 hour'), 4, current_timestamp + (4 * interval '1 hour'), 'ACTIVE', 1, null, 'Austria', 'Vienna', 'Resselgasse', 1);
INSERT INTO public.auction_post (id, category, name, start_time, min_price, end_time, status, creator, bid_id, country, city, street, house_nr) VALUES (5, 'CARS', 'BMW X6', current_timestamp - (10 * interval '1 hour'), 4, current_timestamp + (4 * interval '1 hour'), 'ACTIVE', 1, null, 'Austria', 'Vienna', 'Resselgasse', 1);
INSERT INTO public.auction_post (id, category, name, start_time, min_price, end_time, status, creator, bid_id, country, city, street, house_nr) VALUES (6, 'CARS', 'BMW X6', current_timestamp - (10 * interval '1 hour'), 4, current_timestamp + (4 * interval '1 hour'), 'ACTIVE', 1, null, 'Austria', 'Vienna', 'Resselgasse', 1);
INSERT INTO public.auction_post (id, category, name, start_time, min_price, end_time, status, creator, bid_id, country, city, street, house_nr) VALUES (7, 'CARS', 'BMW X6', current_timestamp - (10 * interval '1 hour'), 4, current_timestamp + (4 * interval '1 hour'), 'ACTIVE', 1, null, 'Austria', 'Vienna', 'Resselgasse', 1);
INSERT INTO public.auction_post (id, category, name, start_time, min_price, end_time, status, creator, bid_id, country, city, street, house_nr) VALUES (8, 'CARS', 'BMW X6', current_timestamp - (10 * interval '1 hour'), 4, current_timestamp + (4 * interval '1 hour'), 'ACTIVE', 1, null, 'Austria', 'Vienna', 'Resselgasse', 1);
INSERT INTO public.auction_post (id, category, name, start_time, min_price, end_time, status, creator, bid_id, country, city, street, house_nr) VALUES (9, 'CARS', 'BMW X6', current_timestamp - (10 * interval '1 hour'), 4, current_timestamp + (4 * interval '1 hour'), 'ACTIVE', 1, null, 'Austria', 'Vienna', 'Resselgasse', 1);
INSERT INTO public.auction_post (id, category, name, start_time, min_price, end_time, status, creator, bid_id, country, city, street, house_nr) VALUES (10, 'CARS', 'BMW X6', current_timestamp - (10 * interval '1 hour'), 4, current_timestamp + (4 * interval '1 hour'), 'ACTIVE', 1, null, 'Austria', 'Vienna', 'Resselgasse', 1);
INSERT INTO public.auction_post (id, category, name, start_time, min_price, end_time, status, creator, bid_id, country, city, street, house_nr) VALUES (11, 'CARS', 'BMW X6', current_timestamp - (10 * interval '1 hour'), 4, current_timestamp + (4 * interval '1 hour'), 'ACTIVE', 1, null, 'Austria', 'Vienna', 'Resselgasse', 1);

INSERT INTO public.bid (id, date_time, offer, auction_id, user_id) VALUES (1, '2020-11-21 21:25:27.624000', 45, 1, 2);
INSERT INTO public.bid (id, date_time, offer, auction_id, user_id) VALUES (2, '2020-11-21 21:25:27.624000', 32, 2, 2);

