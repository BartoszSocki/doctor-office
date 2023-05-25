--------------------------------------------------------------------------

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 10, 'DOCTOR', 'seba@gmail.com', 'pass', 'seba', 'sebson' );

insert into doctors_info ( pk_id, registered_doctor_id, specialization )
values ( 100, 10, 'urologist' );

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 21, 'DOCTOR', 'seba1@gmail.com', 'pass', 'seba1', 'sebson1' );

insert into doctors_info ( pk_id, registered_doctor_id, specialization )
values ( 200, 21, 'cardiologist' );

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 30, 'DOCTOR', 'seba2@gmail.com', 'pass', 'seba2', 'sebson2' );

insert into doctors_info ( pk_id, registered_doctor_id, specialization )
values ( 300, 30, 'cardiologist' );

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 40, 'DOCTOR', 'seba3@gmail.com', 'pass', 'seba3', 'sebson3' );

insert into doctors_info ( pk_id, registered_doctor_id, specialization )
values ( 400, 40, 'cardiologist' );

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 50, 'DOCTOR', 'seba4@gmail.com', 'pass', 'seba4', 'sebson4' );

insert into doctors_info ( pk_id, registered_doctor_id, specialization )
values ( 500, 50, 'cardiologist' );

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 60, 'DOCTOR', 'seba5@gmail.com', 'pass', 'seba5', 'sebson5' );

insert into doctors_info ( pk_id, registered_doctor_id, specialization )
values ( 600, 60, 'cardiologist' );

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 70, 'DOCTOR', 'seba6@gmail.com', 'pass', 'seba6', 'sebson6' );

insert into doctors_info ( pk_id, registered_doctor_id, specialization )
values ( 700, 70, 'cardiologist' );

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 80, 'DOCTOR', 'seba7@gmail.com', 'pass', 'seba7', 'sebson7' );

insert into doctors_info ( pk_id, registered_doctor_id, specialization )
values ( 800, 80, 'cardiologist' );

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 90, 'DOCTOR', 'seba8@gmail.com', 'pass', 'seba8', 'sebson8' );

insert into doctors_info ( pk_id, registered_doctor_id, specialization )
values ( 900, 90, 'cardiologist' );

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 100, 'DOCTOR', 'seba9@gmail.com', 'pass', 'seba9', 'sebson9' );

insert into doctors_info ( pk_id, registered_doctor_id, specialization )
values ( 1000, 100, 'cardiologist' );

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 110, 'DOCTOR', 'seba10@gmail.com', 'pass', 'seba10', 'sebson10' );

insert into doctors_info ( pk_id, registered_doctor_id, specialization )
values ( 1100, 110, 'cardiologist' );
--------------------------------------------------------------------------

insert into registered_users ( pk_id, role, email, password, name, surname )
values ( 20, 'CLIENT', 'bart@gmail.com', 'pass', 'bart', 'batrson' );

insert into clients_info ( pk_id, registered_client_id, date_of_birth)
values ( 100, 20, date '2002-01-01');
--------------------------------------------------------------------------

insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, city, type)
values ( 1000, 10, 'MON', time '16:00:00', time '17:00:00', 250, 'warszawa', 'wizyta kontrolna 1' );

insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, city, type)
values ( 2000, 10, 'MON', time '12:00:00', time '13:00:00', 250, 'warszawa', 'wizyta kontrolna 2' );

insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, city, type)
values ( 3000, 10, 'TUE', time '16:00:00', time '17:00:00', 250, 'warszawa', 'wizyta kontrolna 1' );

insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, city, type)
values ( 4000, 10, 'TUE', time '12:00:00', time '13:00:00', 250, 'warszawa', 'wizyta kontrolna 2' );

insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, city, type)
values ( 5000, 10, 'WED', time '16:00:00', time '17:00:00', 250, 'warszawa', 'wizyta kontrolna 1' );

insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, city, type)
values ( 6000, 10, 'THU', time '16:00:00', time '17:00:00', 250, 'warszawa', 'wizyta kontrolna 1' );

insert into scheduled_visits (pk_id, fk_registered_doctor_id, day_of_the_week, beg_time, end_time, price, city, type)
values ( 7000, 10, 'FRI', time '12:00:00', time '13:00:00', 250, 'warszawa', 'wizyta kontrolna 2' );

--------------------------------------------------------------------------

insert into planned_visits (pk_id, fk_registered_doctor_id, fk_registered_client_id, fk_scheduled_visit_id, visit_day)
values ( 10000, 10, 20, 1000, timestamp '2023-05-05 14:48:00');

insert into planned_visits (pk_id, fk_registered_doctor_id, fk_registered_client_id, fk_scheduled_visit_id, visit_day)
values ( 20000, 10, 20, 1000, timestamp '2023-05-05 14:48:00');

insert into planned_visits (pk_id, fk_registered_doctor_id, fk_registered_client_id, fk_scheduled_visit_id, visit_day)
values ( 30000, 10, 20, 1000, timestamp '2023-05-05 14:48:00');

insert into notes (pk_id, fk_registered_doctor_id, fk_planned_visit_id, name, content, date_of_creation, date_of_modification)
values ( 100000, 10, 10000, 'notatka 1', 'super notatka', date '2023-01-01', date '2023-02-02');

insert into notes (pk_id, fk_registered_doctor_id, fk_planned_visit_id, name, content, date_of_creation, date_of_modification)
values ( 200000, 10, 10000, 'notatka 2', 'super notatka', date '2023-01-01', date '2023-02-02');

insert into notes (pk_id, fk_registered_doctor_id, fk_planned_visit_id, name, content, date_of_creation, date_of_modification)
values ( 300000, 10, 10000, 'notatka 3', 'super notatka', date '2023-01-01', date '2023-02-02');
