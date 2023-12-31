Create TABLE City (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar,
    longitude varchar,
    latitude varchar
);

Create TABLE Person (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar,
    email varchar,
    password varchar
);

Create TABLE Weather (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    city_id int REFERENCES city(id),
    temperature int,
    feels_like int,
    main varchar,
    description varchar
);

alter table city
    alter column longitude type varchar using longitude::varchar;

alter table city
    alter column latitude type varchar using latitude::varchar;

-- \  latitude - северная широта
-- \  longitude - восточная долгота

INSERT INTO City (name, longitude, latitude) VALUES ('Moscow','55.44', '37.36');
INSERT INTO City (name, longitude, latitude) VALUES ('Saint-Petersburg','30.19', '59.57');
INSERT INTO City (name, longitude, latitude) VALUES ('Baku','49.53', '40.22');
INSERT INTO City (name, longitude, latitude) VALUES ('New-York','75.17', '43.6');
INSERT INTO City (name, longitude, latitude) VALUES ('Dubai','55.18', '25.15');

update City set longitude = '55.18' where id = 5;
update City set latitude = '25.15' where id = 5;
SELECT longitude
from City
where name = 'Saint-Petersburg';

SELECT * from City where id=1;
SELECT * from Weather;
SELECT * from Weather where temperature=(SELECT min(temperature) from Weather);


SELECT *
from 'User' where name = 'Kenan';

SELECT * from Person where name = 'Kenan' and password = 'gggg';