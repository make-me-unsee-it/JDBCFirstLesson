create table goods
(
    id      int AUTO_INCREMENT,
    title   VARCHAR(50),
    price   DOUBLE
);
insert into goods (id, title, price)
values (1, 'iphone', 95.7);
insert into goods (id, title, price)
values (2, 'samsung', 99.9);
insert into goods (id, title, price)
values (3, 'google', 58.2);
insert into goods (id, title, price)
values (4, 'nokia', 10.1);

create table users
(
    id          int AUTO_INCREMENT,
    userName    VARCHAR(50),
    password    VARCHAR(50)
);
insert into users (userName, password)
values ('Michail', 'qwerty');
insert into users (userName, password)
values ('Andrey', '12345');
insert into users (userName, password)
values ('Nikolay', 'xcvi12345');
insert into users (userName, password)
values ('Roma', '11111');
