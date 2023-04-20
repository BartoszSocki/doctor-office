create table registered_clients(
    id identity not null,
    email varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);

create table registered_doctors(
    id identity not null,
    email varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);

create table doctors_info(
    id identity not null,
    registered_doctor_id int not null,

    email varchar(255) not null,
    name varchar(32) not null,
    surname varchar(32) not null,
    pesel varchar(20) null,
    mobile varchar(20) null,

    pwz varchar(100) null,
    specialization varchar(32) not null,

    primary key (id),
    foreign key (registered_doctor_id) references registered_doctors(id)
);

create table clients_info(
    id identity not null,
    registered_client_id int not null,

    email varchar(255) not null,
    name varchar(32) not null,
    surname varchar(32) not null,
    pesel varchar(20) null,
    mobile varchar(20) null,

    primary key (id),
    foreign key (registered_client_id) references registered_clients(id)
);
