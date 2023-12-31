create table Person(
    id int generated by default as identity primary key,
    full_name varchar (200) not null unique,
    year_of_birth int check (year_of_birth > 0) not null
)

create table Book(
    id int generated by default as identity primary key,
    title varchar(100) not null,
    author varchar(100) not null,
    year int check (1500 > 0) not null,
    person_id int references Person(id) on delete set null
)

insert into Person(full_name, year_of_birth) values ('Иванов Иван Иванович', 1951);
insert into Person(full_name, year_of_birth) values ('Петров Петр Иванович', 1960);

select * from person
