/* Creación de la base de datos y establecimiento en ella */
CREATE DATABASE pfc_daw CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE pfc_daw;

/* Creación de la tabla de roles de usuario */
CREATE TABLE fbl_users_rol (
    user_rol_id     INT NOT NULL AUTO_INCREMENT,
    user_rol_name   VARCHAR(40) NOT NULL,
    PRIMARY KEY (user_rol_id)
)ENGINE = INNODB;

/* Se insertan los roles de usuario en la tabla */
INSERT INTO 
	fbl_users_rol (user_rol_id, user_rol_name)
VALUES
	(1, 'Manager'),
	(2, 'Jefe de Instructores'),
    (3, 'Instructor'),
    (4, 'Alumno'),
    (5, 'Socio');

/* Creación de la tabla de usuarios */
CREATE TABLE fbl_users (
    user_id         INT NOT NULL AUTO_INCREMENT,
    user_name       VARCHAR(40) NOT NULL,
    user_surname1   VARCHAR(40) NOT NULL,
    user_surname2   VARCHAR(40) DEFAULT NULL,
    user_dni        VARCHAR(40) NOT NULL,
    user_birth      DATE NOT NULL,
    user_email      VARCHAR(40) NOT NULL,
    user_password   VARCHAR(40) DEFAULT NULL,
    user_phone      VARCHAR(40) NOT NULL,
    user_city       VARCHAR(40) NOT NULL,
    user_address    VARCHAR(40) NOT NULL,
    user_hours      DECIMAL(10,1) NOT NULL,
    user_rol        INT NOT NULL,
    user_active     TINYINT NOT NULL,
    user_fst_login  TINYINT NOT NULL,
    user_token      VARCHAR(255) DEFAULT NULL,
    user_expires    DATETIME DEFAULT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT FK_UserRol FOREIGN KEY (user_rol)
    REFERENCES fbl_users_rol (user_rol_id)
)ENGINE = INNODB;

/* Se inserta en la tabla de usuarios unos usuarios de ejemplo */
INSERT INTO 
	fbl_users (user_id, user_name, user_surname1, user_surname2, user_dni, user_birth, user_email, user_password, user_phone, user_city, user_address, user_hours, user_rol, user_active,user_fst_login, user_token, user_expires)
VALUES
	(1, 'Pedro', 'Gimenez', 'Aparicio', '11222333A', '1985-12-25','pedro@gmail.com', MD5('1234'), '600454544', 'Alfafar', 'Carretera de Benetusser, 45 - 1º', 0, 1, 1, 0, NULL, NULL),

	(2, 'Juan', 'Gimenez', 'Perez', '22111333B', '1965-10-02','juan@gmail.com', MD5('1234'), '655444566', 'Benetusser', 'Avda. Paiporta, 15 - 2ºA', 5500, 2, 1, 0, NULL, NULL),

    (3, 'Pablo', 'Lopez', 'Gomez', '31444666C', '1971-01-15','pablo@gmail.com', MD5('1234'), '617252555', 'Benetusser', 'C/Palleter, 3 - 8ºA', 3700, 3, 1, 0, NULL, NULL),
    (4, 'Gines', 'Guerra', 'Martí', '47888963N', '1979-01-29','guerra@gmail.com', MD5('1234'), '617007744', 'Catarroja', 'C/Fonteta nova, 23', 5200, 3, 1, 0, NULL, NULL),

    (5, 'Jose', 'Martinez', 'Cubillas', '55777888C', '1977-11-05','cubillas@gmail.com', MD5('1234'), '667255885', 'Benetusser', 'C/Maestra Juana Sena, 2 - 9ºA', 10, 4, 1, 0, NULL, NULL),
    (6, 'Juan Jose', 'Maestre', 'Lozano', '22444888D', '1985-02-12','maestre@gmail.com', MD5('1234'), '963751112', 'Sedaví', 'Avda. C. Valenciana, 65 - 23ºA', 25, 4, 1, 0, NULL, NULL),
    (7, 'Estefania', 'Pastor', 'Toro', '23454868H', '1996-11-20','estefania@gmail.com', MD5('1234'), '625366996', 'Alfafar', 'Avda. Constitución, 115 - 2ºC', 20, 4, 1, 0, NULL, NULL),

    (8, 'Angela', 'Ares', 'Rey', '45456768B', '2000-10-31','angela@gmail.com', MD5('1234'), '666336699', 'Valencia', 'Avda. Baleares, 101 - 22ºA', 150, 5, 1, 0, NULL, NULL),
    (9, 'Vanessa', 'Ares', 'Maneiro', '46455767F', '1986-09-10','vanessa@gmail.com', MD5('1234'), '685251447', 'Benetusser', 'C/Miguel Hernandez, 87 - 8ª', 1500, 5, 1, 0, NULL, NULL),
    (99, '------', '------', '-------', '------', '1979-01-01','--------@--------.com', MD5('1234'), '----------', '-------', '------------------------', 0, 3, 1, 0, NULL, NULL),

    (10, 'Aeroclub', 'Las', 'Alas', 'B12654987', '1986-09-10','lasalas@gmail.com', MD5('1234'), '600211141', 'Olocau', 'Carretera los naranjos, s/n', 0, 5, 1, 0, NULL, NULL);


/* Creación de la tabla de roles de usuario */
CREATE TABLE fbl_planes_cat (
    plane_cat_id     INT NOT NULL AUTO_INCREMENT,
    plane_cat_name   VARCHAR(40) NOT NULL,
    PRIMARY KEY (plane_cat_id)
)ENGINE = INNODB;

/* Se insertan los roles de usuario en la tabla */
INSERT INTO 
	fbl_planes_cat (plane_cat_id, plane_cat_name)
VALUES
	(1, 'Drone'),
	(2, 'ULM'),
    (3, 'Ligera'),
    (4, 'Cat A'),
    (5, 'Cat B');

/* Creación de la tabla de aeronaves */
CREATE TABLE fbl_planes (
    plane_id        INT NOT NULL AUTO_INCREMENT,
    plane_brand     VARCHAR(40) NOT NULL,
    plane_model     VARCHAR(40) NOT NULL,
    plane_reg       VARCHAR(40) NOT NULL,
    plane_motors    INT NOT NULL,
    plane_hours     DECIMAL(10,1) NOT NULL,
    plane_year      INT NOT NULL,
    plane_cat       INT NOT NULL,
    plane_zfw       DECIMAL(10,2) NOT NULL,
    plane_mtow      DECIMAL(10,2) NOT NULL,
    plane_rent      TINYINT NOT NULL,
    plane_owner     INT NOT NULL,
    PRIMARY KEY (plane_id),
    CONSTRAINT FK_PlaneCat FOREIGN KEY (plane_cat)
    REFERENCES fbl_planes_cat (plane_cat_id),
    CONSTRAINT FK_PlaneOwner FOREIGN KEY (plane_owner)
    REFERENCES fbl_users (user_id) 
    ON DELETE CASCADE
)ENGINE = INNODB;

/* Se inserta en la tabla de aeronaves unas aeronaves de ejemplo */
INSERT INTO 
	fbl_planes (plane_id, plane_brand, plane_model, plane_reg, plane_motors, plane_hours, plane_year, plane_cat, plane_zfw, plane_mtow, plane_rent, plane_owner)
VALUES
	(1, 'Dyamond', 'D-42', 'EC-FAE', 2, 15.5, 2015, 5, 4500, 4900, 1, 10),

	(2, 'Piper', 'Arrow', 'EC-DAM', 1, 1504.2, 1985, 4, 3200, 3400, 1, 10),

    (3, 'Dyamond', 'D-20', 'EC-GGL', 1, 250.8, 2016, 3, 1800, 2200, 1, 10),

    (4, 'Gazaile', 'II', 'EC-Z31', 1, 450.8, 2000, 2, 340, 450, 1, 10),
    (5, 'Tecnam', 'P96', 'EC-ZFK', 1, 1045.8, 1999, 2, 350, 450, 0, 8),
    (6, 'Tecnam', 'P92', 'EC-ZDC', 1, 2450.4, 1995, 2, 364, 450, 0, 9),

    (7, 'DJI', 'Mavik 3', 'EC-ABB', 4, 100.0, 2020, 1, 2.2, 3, 1, 10),
    (8, 'Matrice', '300 RTK', 'EC-ABZ', 6, 120.5, 2021, 1, 15.3, 25.0, 0, 8),
    (9, 'Autel', 'EVO 2', 'EC-ABG', 4, 172.5, 2021, 1, 12.3, 20.0, 0, 9),
    (10, 'DJI', 'Inspire 2', 'EC-ABN', 4, 15.5, 2021, 1, 12.3, 25.0, 1, 10);

/* Creación de la tabla de vuelos */
CREATE TABLE fbl_flights (
    flight_id       INT NOT NULL AUTO_INCREMENT,
    flight_day      DATE NOT NULL,
    flight_hdep     TIME NOT NULL,
    flight_harr     TIME,
    flight_p_reg    INT NOT NULL,
    flight_dep      VARCHAR(40) NOT NULL,
    flight_arr      VARCHAR(40) NOT NULL,
    flight_pic      INT NOT NULL,
    flight_inst     INT,
    flight_land_d   INT,
    flight_land_n   INT,
    flight_isTrain  TINYINT NOT NULL,
    flight_route    VARCHAR(128),
    flight_notes    VARCHAR(128),
    flight_finish   TINYINT,
    PRIMARY KEY (flight_id),
    CONSTRAINT FK_flightpic FOREIGN KEY (flight_pic)
    REFERENCES fbl_users (user_id) 
    ON DELETE CASCADE,
    CONSTRAINT FK_flightinst FOREIGN KEY (flight_inst)
    REFERENCES fbl_users (user_id) 
    ON DELETE CASCADE,
    CONSTRAINT FK_flightplane FOREIGN KEY (flight_p_reg)
    REFERENCES fbl_planes (plane_id) 
    ON DELETE CASCADE
)ENGINE = INNODB;

/* Se inserta en la tabla de vuelos unos vuelos de ejemplo */
INSERT INTO 
	fbl_flights (flight_id, flight_day, flight_hdep, flight_harr, flight_p_reg, flight_dep, flight_arr, flight_pic, flight_inst, flight_land_d, flight_land_n, flight_isTrain, flight_route, flight_notes, flight_finish)
VALUES
	(1, '2022-03-10', '12:00:00', NULL, 2, 'LEVC', 'LEVC', 5, 3, NULL, NULL, 1, NULL, NULL, 0),
	(2, '2022-03-11', '12:30:00', NULL, 2, 'LEVC', 'LEAL', 6, 4, NULL, NULL, 1, NULL, NULL, 0),
    (3, '2022-03-12', '13:30:00', NULL, 2, 'LEVC', 'LEAB', 7, 3, NULL, NULL, 1, NULL, NULL, 0),
    (4, '2022-04-10', '17:00:00', NULL, 3, 'LEVC', 'LEVC', 5, 4, NULL, NULL, 1, NULL, NULL, 0),
	(5, '2022-05-11', '10:30:00', NULL, 3, 'LEVC', 'LEMH', 6, 3, NULL, NULL, 1, NULL, NULL, 0),
    (6, '2022-05-15', '11:30:00', NULL, 3, 'LEVC', 'LEPM', 7, 4, NULL, NULL, 1, NULL, NULL, 0),

    (7, '2022-01-10', '12:00:00', '13:25:00', 2, 'LEVC', 'LEVC', 5, 4, 4, 0, 1, 'S - coast line - S', 'TS-01-05 Vuelo recto y nivelado', 1),
	(8, '2022-01-11', '12:30:00', '13:35:00', 2, 'LEVC', 'LEVC', 6, 3, 4, 0, 1, 'Touch and go', 'TS-01-07 Despegues y aterrizajes', 1),
    (9, '2022-01-12', '13:30:00', '15:45:00', 2, 'LEVC', 'LEAB', 7, 4, 4, 0, 1, 'N - Pantano - E', 'TS-01-09 Campo distinto al habitual', 1),
    (10, '2022-02-10', '19:00:00', '20:45:00', 3, 'LEVC', 'LEIB', 5, 3, 0, 1, 1, 'N - DCT - NE', 'TS-01-15 Aterrizaje nocturno', 1),
	(11, '2022-03-11', '10:30:00', '11:35:00', 3, 'LEVC', 'LEVC', 6, 4, 4, 0, 1, 'S - coast line - S', 'TS-01-03 Virages coordinados', 1),
    (12, '2022-04-15', '11:30:00', '12:45:00', 3, 'LEVC', 'LEAL', 7, 3, 4, 4, 1, 'S - Guitiriz - N', 'TS-01-08 Pérdidas y barrenas', 1);