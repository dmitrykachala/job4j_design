create table cars (
                          id serial primary key,
                          name varchar(50),
                          producer varchar(50),
                          count integer default 0,
                          price integer
);

insert into cars (name, producer, count, price) VALUES ('1er', 'bmw', 2, 50000);
insert into cars (name, producer, count, price) VALUES ('a3', 'audi', 3, 60000);
insert into cars (name, producer, count, price) VALUES ('e200cdi', 'mercedes', 1, 80000);