create table registered_users(
    pk_id identity not null,

    role varchar(16) not null,
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
    disabled bit default false,

    primary key (pk_id),
    foreign key (fk_registered_doctor_id) references registered_users(pk_id)
);

create table planned_visits(
    pk_id identity not null,
    fk_registered_doctor_id int not null,
    fk_registered_client_id int null,
    fk_scheduled_visit_id int,

    visit_day datetime not null,
    canceled bit default false,
    was_mail_send bit default false,
    is_mail_scheduled bit default false,

    primary key (pk_id),
    foreign key (fk_registered_doctor_id) references registered_users(pk_id),
    foreign key (fk_registered_client_id) references registered_users(pk_id),
    foreign key (fk_scheduled_visit_id) references scheduled_visits(pk_id)
);

create table notes(
    pk_id identity not null,
    fk_registered_doctor_id int not null,
    fk_planned_visit_id int not null,

    name varchar(255) not null,
    content varchar(1024) not null,
    date_of_creation datetime not null,
    date_of_modification datetime not null,

    primary key (pk_id),
    foreign key (fk_registered_doctor_id) references registered_users(pk_id),
    foreign key (fk_planned_visit_id) references planned_visits(pk_id)
)