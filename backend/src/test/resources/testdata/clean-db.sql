
DELETE FROM regular_user_preferences;
DELETE FROM regular_user_notification;
DELETE FROM notification;
DELETE FROM rating;
update auction_post set bid_id = null;
DELETE FROM bid;
DELETE FROM auction_subscriptions;
DELETE FROM auction_post;
DELETE FROM auction_house;
DELETE FROM regular_user;
DELETE FROM auction_subscriptions;
DELETE FROM password_reset_token;
DELETE FROM regular_user_preferences;
delete from contact_form;

