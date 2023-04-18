insert into users_auth (id, username, encoded_password) values (10, 'bartosz', 'password');

insert into users (id, username, user_auth_id) values (10, 'bartosz', 10);
insert into users (id, username) values (20, 'kamil');
insert into users (id, username) values (30, 'sebastian');
