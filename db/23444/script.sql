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
insert into cars (name, producer, count, price) VALUES ('golf', 'vw', 1, 40000);

begin;
    insert into cars (name, producer, count, price) VALUES ('granta', 'lada', 17, 10000);

    select * from cars;

    savepoint fst_svpnt;
    delete from cars where price > 50000;

    select * from cars;

    update cars set price = 35000 where name = 'golf';

    select * from cars;

    rollback to fst_svpnt;

    select * from cars;

    delete from cars;

    select * from cars;

    rollback to fst_svpnt;

    select * from cars;

    drop table cars;

    select * from cars;

    rollback to fst_svpnt;

    select * from cars;

commit;