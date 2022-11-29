create table htt_user
(
    id            int auto_increment
        primary key,
    username      varchar(64)  null,
    password      varchar(64)  null,
    hash_password varchar(256) null
);



create table htt_balance
(
    id      int auto_increment
        primary key,
    user_id int          not null,
    balance varchar(128) null
);


INSERT INTO htt_user (id, username, password, hash_password) VALUES (1, 'root', 'root', '63a9f0ea7bb98050796b649e85481845');
INSERT INTO htt_user (id, username, password, hash_password) VALUES (2, 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3');
INSERT INTO htt_user (id, username, password, hash_password) VALUES (4, 'user3', 'user3', '92877af70a45fd6a2ed7fe81e1236b78');
INSERT INTO htt_user (id, username, password, hash_password) VALUES (5, 'user4', 'user4', '3f02ebe3d7929b091e3d8ccfde2f3bc6');
INSERT INTO htt_user (id, username, password, hash_password) VALUES (6, 'user5', 'user5', '0a791842f52a0acfbb3a783378c066b8');
INSERT INTO htt_user (id, username, password, hash_password) VALUES (7, 'user6', 'user6', 'affec3b64cf90492377a8114c86fc093');
INSERT INTO htt_user (id, username, password, hash_password) VALUES (8, 'user7', 'user7', '3e0469fb134991f8f75a2760e409c6ed');
INSERT INTO htt_user (id, username, password, hash_password) VALUES (9, 'user8', 'user8', '7668f673d5669995175ef91b5d171945');
INSERT INTO htt_user (id, username, password, hash_password) VALUES (10, 'user9', 'user9', '8808a13b854c2563da1a5f6cb2130868');
INSERT INTO htt_user (id, username, password, hash_password) VALUES (11, 'user10', 'user10', '990d67a9f94696b1abe2dccf06900322');


INSERT INTO htt_balance (id, user_id, balance) VALUES (1, 1, '1.2');
INSERT INTO htt_balance (id, user_id, balance) VALUES (2, 2, '2.1');
INSERT INTO htt_balance (id, user_id, balance) VALUES (3, 11, '10.0');
INSERT INTO htt_balance (id, user_id, balance) VALUES (4, 4, '4.1');
INSERT INTO htt_balance (id, user_id, balance) VALUES (5, 5, '5.0');
INSERT INTO htt_balance (id, user_id, balance) VALUES (6, 6, '6.0');
INSERT INTO htt_balance (id, user_id, balance) VALUES (7, 7, '7.0');
INSERT INTO htt_balance (id, user_id, balance) VALUES (8, 8, '8.0');
INSERT INTO htt_balance (id, user_id, balance) VALUES (9, 9, '9.0');
INSERT INTO htt_balance (id, user_id, balance) VALUES (11, 10, '9');
