
INSERT INTO public.regular_user (id, active, email, first_name, last_name, password_hash, phone_nr) VALUES (1, true, 'test@test.com', 'Testname', 'Testname', '9999', '123');
INSERT INTO public.auction_house (id, active, email, name, password_hash, phone_nr) VALUES (1, true, 'test@test.com', 'Testname', '9999', '123');
INSERT INTO public.auction_post (id, category, end_time, min_price, start_time, status, creator) VALUES (1, 'ELECTRONICS', '2020-12-31 00:00:00', 3, '2020-12-31 00:00:00', 'ACTIVE', 1);