CREATE DATABASE centroComercial;

USE centroComercial;

CREATE TABLE tienda(
	id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(255),
    ubicacion varchar(255)
);

CREATE TABLE cliente(
	id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(255),
    apellido varchar(255),
	email varchar(255)
);

CREATE TABLE producto(
	id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(255),
    precio DECIMAL(10,3),
	id_tienda int,
    FOREIGN KEY (id_tienda) REFERENCES tienda (id) ON DELETE CASCADE
);

CREATE TABLE compra(
	id int PRIMARY KEY AUTO_INCREMENT,
    id_cliente int,
	FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE,
    id_producto int,
    FOREIGN KEY (id_producto) REFERENCES producto(id) ON DELETE CASCADE,
    fecha_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cantidad int
);

SELECT * FROM producto;

INSERT INTO compra 	(id_cliente, id_producto, cantidad) VALUES (1, 3, 5);

SELECT * FROM compra INNER JOIN cliente on cliente.id = compra.id_cliente INNER JOIN producto on producto.id = compra.id_producto INNER JOIN tienda on tienda.id = producto.id_tienda;

ALTER TABLE `centroComercial`.`producto` 
ADD COLUMN `stock` INT NOT NULL AFTER `id_tienda`;