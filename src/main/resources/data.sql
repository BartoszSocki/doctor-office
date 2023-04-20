insert into registered_users (id, username, password, role) values (10, 'bartosz', 'password', 'DOCTOR');
insert into registered_users (id, username, password, role) values (20, 'seba', 'password', 'CLIENT');

insert into doctor_info (id, registered_user_id, name, surname, specialization) values ( 10, 10, 'bartosz', 'socki', 'dentist1' );
insert into doctor_info (id, registered_user_id, name, surname, specialization) values ( 20, 20, 'sebastian', 'sekula', 'dentist2' );
-- insert into users (id, username, user_auth_id) values (10, 'bartosz', 10);
-- insert into users (id, username) values (20, 'kamil');
-- insert into users (id, username) values (30, 'sebastian');
