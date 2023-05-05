insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 10, 'DOCTOR', 'seba@gmail.com', 'pass', 'seba', 'sebson' );

insert into doctors_info ( pk_id, registered_doctor_id, specialization )
values ( 100, 10, 'urologist' );

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 20, 'CLIENT', 'bart@gmail.com', 'pass', 'bart', 'batrson' );

insert into clients_info ( pk_id, registered_client_id, date_of_birth)
values ( 100, 20, date '2002-01-01');

insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, localization, type)
values ( 1000, 10, 'MON', time '16:00:00', time '17:00:00', 250, 'warszawa', 'wizyta kontrolna 1' );

insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, localization, type)
values ( 2000, 10, 'TUE', time '12:00:00', time '13:00:00', 250, 'warszawa', 'wizyta kontrolna 2' );

insert into planned_visits (pk_id, fk_registered_doctor_id, fk_registered_client_id, fk_scheduled_visit_id, visit_day)
values ( 10000, 10, 20, 1000, timestamp '2023-05-05 14:48:00');

insert into notes (pk_id, fk_registered_doctor_id, fk_planned_visit_id, name, content, date_of_creation, date_of_modification)
values ( 100000, 10, 10000, 'notatka 1', 'super notatka', date '2023-01-01', date '2023-02-02');

insert into notes (pk_id, fk_registered_doctor_id, fk_planned_visit_id, name, content, date_of_creation, date_of_modification)
values ( 200000, 10, 10000, 'notatka 2', 'super notatka', date '2023-01-01', date '2023-02-02');

insert into notes (pk_id, fk_registered_doctor_id, fk_planned_visit_id, name, content, date_of_creation, date_of_modification)
values ( 300000, 10, 10000, 'notatka 3', 'super notatka', date '2023-01-01', date '2023-02-02');
