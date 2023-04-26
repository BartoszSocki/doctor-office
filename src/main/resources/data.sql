insert into registered_doctors ( id, email, password ) values ( 10, 'seba', 'pass' );
insert into registered_clients ( id, email, password ) values ( 10, 'bart', 'pass' );

insert into clients_info ( id, registered_client_id, email, name, surname )
values ( 10, 10, 'bart', 'bartosz', 'socki' );

insert into doctors_info ( id, registered_doctor_id, email, name, surname, specialization )
values ( 10, 10, 'seba', 'sebastian', 'sekula', 'urologist' );

insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, localization, type)
values ( 10, 10, 'MON', CURRENT_TIME, CURRENT_TIME, 250, 'warszawa', 'wizyta kontrolna 1' );

insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, localization, type)
values ( 20, 10, 'TUE', CURRENT_TIME, CURRENT_TIME, 250, 'warszawa', 'wizyta kontrolna 2' );

-- insert into registered_users (id, username, password, role) values (10, 'bartosz', 'password', 'DOCTOR');
-- insert into registered_users (id, username, password, role) values (20, 'seba', 'password', 'CLIENT');
-- insert into doctor_info (id, registered_user_id, name, surname, specialization) values ( 10, 10, 'bartosz', 'socki', 'dentist1' );
-- insert into doctor_info (id, registered_user_id, name, surname, specialization) values ( 20, 20, 'sebastian', 'sekula', 'dentist2' );
-- insert into users (id, username, user_auth_id) values (10, 'bartosz', 10);
-- insert into users (id, username) values (20, 'kamil');
-- insert into users (id, username) values (30, 'sebastian');
