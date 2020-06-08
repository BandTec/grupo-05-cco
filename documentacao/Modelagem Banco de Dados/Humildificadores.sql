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
    preferenciaTemp varchar (7),
    preferenciaUmid varchar (3)
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
(null, 'Kalil Bego', 'Rua Barreiro', 'Carapicuíba', 'São Paulo', '2020-12-20', 'Kalilmito@gmail.com', 'senha', '22.00°C', '50%'),
(null, 'Kaio Katiau', 'Rua Camarelo', 'Osasco', 'São Paulo', '2002-02-01', 'Kaiosilva@gmail.com', 'katiau', '20.00°C', '20%'),
(null, 'Eric Viezzer', 'Rua Jacinto Neves', 'Parati', 'Rio de Janeiro', '2010-10-03', 'Capivara@gmail.com', 'Capivara', '15.00°C', '70%'),
(null, 'Victor Barbosa', 'Rua Bacalhau', 'Butão', 'Goiana', '2030-09-01', 'Victor@gmail.com', 'senha', '25.00°C', '50%'),
(null, 'Gabriel Marcolino', 'Rua Nada', 'Piauzinho', 'Sergipe', '2005-03-15', 'Marcoli@gmail.com', 'senha', '30.00°C', '30%');

insert into parque values
	(1, 'Rua Aviões do forró', 'Muito bom', 1),
	(2, 'Rua Haikaiss', 'Incrível', 2),
	(3, 'Rua Adl', 'Tooop', 3);

insert into sensor values
	(1, 1),
	(2, 1),
	(3, 1);


select * from cliente;-- Dados dos clientes
select * from parque;-- Dados dos parques
select * from sensor;-- Dados da tabela sensor
select * from eventos;-- Dados dos eventos
select * from parque,sensor where fkParque = idParque; -- Mostra os dados dos parques junto aos dados da tabela sensor
























