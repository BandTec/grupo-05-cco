create database humildificadores;
use humildificadores;

create table cliente (
	idCliente int primary key auto_increment,
	nome varchar (30),
	rua varchar (40),
	cidade varchar (20),
	estado varchar (20),
	ddn date,
	email varchar (30),
	senha varchar (15),
	fkParque int
)auto_increment = 1;

create table parque (
	idParque int primary key,
	localização varchar (30),
	avaliação varchar (50),
	fkCliente int,
	foreign key (fkCliente) references cliente(idCliente)
);

create table sensor (
	idSensor int primary key,
	fkParque int,
	foreign key (fkParque) references parque(idParque)
);

create table eventos (
	fkSensor int,
	foreign key (fkSensor) references sensor(idSensor),
    idEvento int,
    primary key (fkSensor,idEvento),
	hora varchar (5),
	dataEvento date,
	umidade varchar (3),
	temperatura varchar (7)
);

insert into cliente values 
(null, 'Kalil Bego', 'Rua Barreiro', 'Dog mal', 'Gorila', '2020-12-20', 'Kalilmito@gmail.com', 'senha', 1),
(null, 'Kaio Katiau', 'Rua Camarelo', 'Dog bom', 'Caneta Azul', '2002-02-01', 'Kaiosilva@gmail.com', 'katiau', 3),
(null, 'Eric Viezzer', 'Rua Jacinto Neves', 'Beija-flor', 'MlK piranha', '2010-10-03', 'Capivara@gmail.com', 'Capivara', 1),
(null, 'Victor Barbosa', 'Rua Bacalhau', 'Butão', 'Paçoca', '2030-09-01', 'Victor@gmail.com', 'senha', 2),
(null, 'Gabriel Marcolino', 'Rua Nada', 'Mais', 'Cachorro', '2005-03-15', 'Marcoli@gmail.com', 'senha', 2);

insert into parque values
(1, 'Rua Aviões do forró', 'Muito bom', 1),
(2, 'Rua Haikaiss', 'Incrível', 2),
(3, 'Rua Adl', 'Tooop', 3);

insert into sensor values
(1, 1),
(2, 1),
(3, 1);

insert into eventos values 
(1, 1, '06:00', '2020-12-02', '20%', '22.00°C'),
(1, 2, '12:00', '2020-12-03', '30%', '23.03°C'),
(1, 3, '18:00', '2020-12-04', '25%', '23.09°C'),
(2, 1, '06:00', '2020-05-02', '30%', '24.05°C'),
(2, 2, '12:00', '2020-05-03', '50%', '21.02°C'),
(2, 3, '18:00', '2020-05-04', '45%', '30.06°C'),
(3, 1, '06:00', '2020-01-01', '50%', '32.07°C'),
(3, 2, '12:00', '2020-01-02', '50%', '29.05°C'),
(3, 3, '18:00', '2020-01-03', '60%', '30.07°C');


alter table cliente add foreign key(fkParque) references parque(idParque);

select * from cliente;
select * from parque;
select * from sensor;
select * from eventos;
