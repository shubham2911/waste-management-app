CREATE TABLE user
(
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` varchar(128) NOT NULL,
    `phone_number` bigint(15) NOT NULL,
    `password` varchar(128) NOT NULL,
    `user_type` varchar(32) NOT NULL,
    `green_money_balance` decimal(16,9) DEFAULT '0.000000000' NOT NULL,
    `is_active` bit(1) NOT NULL DEFAULT b'1',
    `record_version_number` int(4) DEFAULT NULL,
    `created_timestamp` bigint(15) DEFAULT NULL,
    `updated_timestamp` bigint(15) DEFAULT NULL
);
CREATE UNIQUE INDEX user_phone_number_uindex ON user (phone_number);

INSERT INTO waste_management.user (id, name, phone_number, password, user_type, green_money_balance, is_active, record_version_number, created_timestamp, updated_timestamp) VALUES (1, 'Shubham', 8950382310, 'testing', 'HOUSEHOLD', 0.000000000, true, 1, 1538080824350, 1538080824350);