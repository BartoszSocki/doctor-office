insert into registered_doctors ( id, email, password ) values ( 10, 'seba', 'pass' );
insert into registered_clients ( id, email, password ) values ( 10, 'bart', 'pass' );

insert into clients_info ( id, registered_client_id, email, name, surname )
values ( 10, 10, 'bart', 'bartosz', 'socki' );

insert into doctors_info ( id, registered_doctor_id, email, name, surname, specialization )
values ( 10, 10, 'seba', 'sebastian', 'sekula', 'urologist' );

-- insert into registered_users (id, username, password, role) values (10, 'bartosz', 'password', 'DOCTOR');
-- insert into registered_users (id, username, password, role) values (20, 'seba', 'password', 'CLIENT');
-- insert into doctor_info (id, registered_user_id, name, surname, specialization) values ( 10, 10, 'bartosz', 'socki', 'dentist1' );
-- insert into doctor_info (id, registered_user_id, name, surname, specialization) values ( 20, 20, 'sebastian', 'sekula', 'dentist2' );
-- insert into users (id, username, user_auth_id) values (10, 'bartosz', 10);
-- insert into users (id, username) values (20, 'kamil');
-- insert into users (id, username) values (30, 'sebastian');
