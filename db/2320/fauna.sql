create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('sdsffishdf', 15000, '1900-01-01');
insert into fauna(name, avg_age, discovery_date) values ('lion', 25000, '1800-01-01');
insert into fauna(name, avg_age) values ('tiger', 17000);
insert into fauna(name, avg_age, discovery_date) values ('fishaaa', 300000, '1901-01-01');
insert into fauna(name, avg_age, discovery_date) values ('human', 70, '1960-01-01');

select * from fauna;

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <=21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';