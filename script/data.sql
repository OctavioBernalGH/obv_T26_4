DROP DATABASE  IF EXISTS `UD26_Ejercicio_4`;

CREATE DATABASE `UD26_Ejercicio_4`;

USE `UD26_Ejercicio_4`;

CREATE TABLE `facultad` (
    `id` int auto_increment,
    `nombre` VARCHAR(100),
    PRIMARY KEY (`id`)
);

create table `investigadores`
(
	`id` int auto_increment,
    `nom_apels` varchar(100),
    `dni` varchar(100),
    `fk_facultad` int,
    primary key (`id`),
    foreign key (`fk_facultad`) references `facultad`(`id`)
);

create table `equipos`
(
	`id` int auto_increment,
	`num_serie` varchar(100),
    `nombre` varchar(100),
    `fk_facultad` int,
    primary key (`id`),
    foreign key (`fk_facultad`) references `facultad`(`id`)
);

create table `reservas`
(
	`id` int auto_increment,
    `comienzo` date,
    `fin` date,
    `fk_id_investigador` int,
    `fk_id_equipo` int,
    primary key (`id`),
    foreign key (`fk_id_investigador`) references `investigadores`(`id`),
    foreign key (`fk_id_equipo`) references `equipos`(`id`)
);

-- INSERT FACULTAD --
insert into `facultad`(`nombre`) values ('Facultad Doctor Jose');
insert into `facultad`(`nombre`) values ('Facultad Doctor Casa');
insert into `facultad`(`nombre`) values ('Facultad San Roberto');
insert into `facultad`(`nombre`) values ('Facultad Pedro Pedrote');
insert into `facultad`(`nombre`) values ('Facultad Pepe pepe');

-- INVESTIGADORES --
insert into `investigadores`(`nom_apels`, `dni`, `fk_facultad`) values ('Marichalar' , '0000X', 1);
insert into `investigadores`(`nom_apels`, `dni`, `fk_facultad`) values ('Daviduvi' , '0000A', 2);
insert into `investigadores`(`nom_apels`, `dni`, `fk_facultad`) values ('Marichalar' , '0000B', 3);
insert into `investigadores`(`nom_apels`, `dni`, `fk_facultad`) values ('Marichalar' , '0000C', 4);
insert into `investigadores`(`nom_apels`, `dni`, `fk_facultad`) values ('Marichalar' , '0000D', 5);

-- EQUIPOS --
insert into `equipos`(`num_serie`, `nombre`, `fk_facultad`) values ('S01', 'Equipo A', 1);
insert into `equipos`(`num_serie`, `nombre`, `fk_facultad`) values ('S02', 'Equipo B', 2);
insert into `equipos`(`num_serie`, `nombre`, `fk_facultad`) values ('S03', 'Equipo C', 3);
insert into `equipos`(`num_serie`, `nombre`, `fk_facultad`) values ('S04', 'Equipo D', 4);
insert into `equipos`(`num_serie`, `nombre`, `fk_facultad`) values ('S05', 'Equipo E', 5);

-- RESERVAS --
insert into `reservas`(`comienzo`, `fin`, `fk_id_investigador`, `fk_id_equipo`) values ('2022-10-10', '2023-10-10', 1, 5);
insert into `reservas`(`comienzo`, `fin`, `fk_id_investigador`, `fk_id_equipo`) values ('2022-09-10', '2023-04-04', 2, 4);
insert into `reservas`(`comienzo`, `fin`, `fk_id_investigador`, `fk_id_equipo`) values ('2022-08-10', '2023-05-05', 3, 3);
insert into `reservas`(`comienzo`, `fin`, `fk_id_investigador`, `fk_id_equipo`) values ('2022-07-10', '2023-08-07', 4, 2);
insert into `reservas`(`comienzo`, `fin`, `fk_id_investigador`, `fk_id_equipo`) values ('2022-06-10', '2023-09-09', 5, 1);