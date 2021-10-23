create table user (
    id serial primary key,
    name text,
    role_id int references role(id)
);

create table role (
    id serial primary key,
    name text
);

create table role_rules (
    id serial primary key,
    role_id references role(id),
    rules_id references rules(id)
);

create table rules (
    id serial primary key,
    name text
);

create table item (
    id serial primary key,
    name text,
    user_id references user(id),
    category_id references category(id),
    state_id references state(id)
);

create table comments (
    id serial primary key,
    name text,
    item_id references item(id)
);

create table attachs(
    id serial primary key,
    name text,
    item_id references item(id)
);

create table category (
    id serial primary key,
    name text
);

create table state (
    id serial primary key,
    name text
);