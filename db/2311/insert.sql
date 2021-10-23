insert into role(name) values ('root');
insert into rules(name) values ('admin');
insert into state(name) values ('on the go');
insert into category(name) values ('important');

insert into user(name, role_id) VALUES ('Ivan', 1);
insert into role_rules(role_id, rules_id) VALUES (1, 1);
insert into item(name, user_id, category_id, state_id) VALUES ('install', 1, 1, 1);
insert into comments(name, item_id) VALUES ('as soon as possible', 1);
insert into attachs(name, item_id) VALUES ('photo', 1);