 create table docs(
     id serial primary key,
     name varchar(255)
 );
 
 create table patients(
     id serial primary key,
     name varchar(255)
 );
 
 create table docs_patients(
     id serial primary key,
     docs_id int references docs(id),
     patients_id int references patients(id)
 );

insert into docs(name) values ('Aibolit');
insert into docs(name) values ('Malahov');
insert into docs(name) values ('Kurpatov');

insert into patients(name) values ('Ivan');
insert into patients(name) values ('Petr');
insert into patients(name) values ('Sidor');

select * from docs;
select * from patients;

insert into docs_patients(docs_id, patients_id) values (7, 7);
insert into docs_patients(docs_id, patients_id) values (7, 8);
insert into docs_patients(docs_id, patients_id) values (7, 9);
insert into docs_patients(docs_id, patients_id) values (8, 7);
insert into docs_patients(docs_id, patients_id) values (8, 8);
insert into docs_patients(docs_id, patients_id) values (9, 9);

select * from docs_patients;