-- insert into registered_doctors ( id, email, password ) values ( 10, 'seba', 'pass' );
-- insert into registered_clients ( id, email, password ) values ( 10, 'bart', 'pass' );
--
-- insert into clients_info ( id, registered_client_id, email, name, surname )
-- values ( 10, 10, 'bart', 'bartosz', 'socki' );
--
-- insert into doctors_info ( id, registered_doctor_id, email, name, surname, specialization )
-- values ( 10, 10, 'seba', 'sebastian', 'sekula', 'urologist' );
--
-- insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, localization, type)
-- values ( 10, 10, 'MON', CURRENT_TIME, CURRENT_TIME, 250, 'warszawa', 'wizyta kontrolna 1' );
--
-- insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, localization, type)
-- values ( 20, 10, 'TUE', CURRENT_TIME, CURRENT_TIME, 250, 'warszawa', 'wizyta kontrolna 2' );

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 10, 'DOCTOR', 'seba@gmail.com', 'pass', 'seba', 'sebson' );

insert into doctors_info ( pk_id, registered_doctor_id, specialization )
values ( 10, 10, 'urologist' );

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 20, 'CLIENT', 'bart@gmail.com', 'pass', 'bart', 'batrson' );

insert into clients_info ( pk_id, registered_client_id, date_of_birth)
values ( 10, 20, date '2002-01-01');

insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, localization, type)
values ( 10, 10, 'MON', CURRENT_TIME, CURRENT_TIME, 250, 'warszawa', 'wizyta kontrolna 1' );

insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, localization, type)
values ( 20, 10, 'TUE', CURRENT_TIME, CURRENT_TIME, 250, 'warszawa', 'wizyta kontrolna 2' );
