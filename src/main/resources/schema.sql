create table registered_users(
    pk_id identity not null,

    email varchar(255) not null,
    password varchar(255) not null,

    name varchar(32) not null,
    surname varchar(64) not null,
    gender varchar(1) null,
    mobile varchar(16) null,

    verified bit default false,
    primary key (pk_id)
);

create table doctors_info(
    pk_id identity not null,
    registered_doctor_id int not null,

    specialization varchar(32) not null,

    primary key (pk_id),
    foreign key (registered_doctor_id) references registered_users(pk_id)
);

create table clients_info(
    pk_id identity not null,
    registered_client_id int not null,

    date_of_birth date null,

    primary key (pk_id),
    foreign key (registered_client_id) references registered_users(pk_id)
);

create table scheduled_visits(
    pk_id identity not null,
    fk_registered_doctor_id int not null,

    day_of_the_week varchar(20) not null,
    beg_time time not null,
    end_time time not null,
    price int null,
    localization varchar(60) not null,
    type varchar(60) not null,

    primary key (pk_id),
    foreign key (fk_registered_doctor_id) references doctors_info(pk_id)
);

create table planned_visits(
    pk_id identity not null,
    fk_registered_doctor_id int not null,
    fk_registered_client_id int null,
    fk_scheduled_visit_id int not null,

    visit_day datetime not null,
    cancelled bit default false,

    primary key (pk_id),
    foreign key (fk_registered_doctor_id) references registered_users(pk_id),
    foreign key (fk_registered_client_id) references registered_users(pk_id),
    foreign key (fk_scheduled_visit_id) references scheduled_visits(pk_id)
);