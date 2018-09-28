CREATE TABLE user
(
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` varchar(128) NOT NULL,
    `phone_number` bigint(15) NOT NULL,
    `password` varchar(128) NOT NULL,
    `user_type` varchar(32) NOT NULL,
    `green_money_balance` decimal(19,4) DEFAULT '0.000000000' NOT NULL,
    `is_active` bit(1) NOT NULL DEFAULT b'1',
    `record_version_number` int(4) DEFAULT NULL,
    `created_timestamp` bigint(15) DEFAULT NULL,
    `updated_timestamp` bigint(15) DEFAULT NULL
);
CREATE UNIQUE INDEX user_phone_number_uindex ON user (phone_number);

INSERT INTO waste_management.user (id, name, phone_number, password, user_type, green_money_balance, is_active, record_version_number, created_timestamp, updated_timestamp) VALUES (1, 'Shubham', 8950382310, 'test', 'HOUSEHOLD', 0.000000000, true, 1, 1538080824350, 1538080824350);
INSERT INTO waste_management.user (id, name, phone_number, password, user_type, green_money_balance, is_active, record_version_number, created_timestamp, updated_timestamp) VALUES (2, 'Dukandar', 1234567890, 'test', 'BUSINESS', 0.000000000, true, 1, 1538080824350, 1538080824350);



CREATE TABLE coupon
(
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    issued_by_user_id bigint NOT NULL,
    valid_from bigint NOT NULL,
    valid_to bigint NOT NULL,
    title varchar(256) NOT NULL,
    description text,
    used_count bigint NOT NULL,
    max_count bigint,
    `is_active` bit(1) NOT NULL DEFAULT b'1',
    `record_version_number` int(4) DEFAULT NULL,
    `created_timestamp` bigint(15) DEFAULT NULL,
    `updated_timestamp` bigint(15) DEFAULT NULL
);

INSERT INTO waste_management.coupon (id, issued_by_user_id, valid_from, valid_to, title, description, used_count, max_count, is_active, record_version_number, created_timestamp, updated_timestamp) VALUES (1, 2, 1538115995866, 1538215995866, 'kachra', 'discount nhi milega', 0, 10, true, 0, 1538116085543, 1538116085543);

CREATE TABLE user_coupon_mapping
(
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id bigint NOT NULL,
    coupon_id bigint NOT NULL,
    redemption_code varchar(5) NOT NULL,
    `is_active` bit(1) NOT NULL DEFAULT b'1',
    `record_version_number` int(4) DEFAULT NULL,
    `created_timestamp` bigint(15) DEFAULT NULL,
    `updated_timestamp` bigint(15) DEFAULT NULL
);
CREATE UNIQUE INDEX user_coupon_mapping_redemption_code_uindex ON user_coupon_mapping (redemption_code);


CREATE TABLE transaction
(
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id bigint NOT NULL,
    transaction_type varchar(32) NOT NULL,
    remarks text,
    opening_balance decimal(19,4) NOT NULL,
    amount decimal(19,4) NOT NULL,
    closing_balance decimal(19,4) NOT NULL,
    `is_active` bit(1) NOT NULL DEFAULT b'1',
    `record_version_number` int(4) DEFAULT NULL,
    `created_timestamp` bigint(15) DEFAULT NULL,
    `updated_timestamp` bigint(15) DEFAULT NULL
);