create database humildificadores;
use humildificadores;

create table cliente (
	idCliente int primary key identity,
	nome varchar (60),
	rua varchar (80),
	cidade varchar (20),
	estado varchar (20),
	ddn date,
	email varchar (40),
	senha varchar (15),
    preferenciaTemp decimal (4,2),
    preferenciaUmid decimal (5,2)
);
insert into cliente (nome,rua,cidade,estado,ddn,email,senha,preferenciaTemp,preferenciaUmid) values
    ('admin','admin','admin','admin','2002-09-07T00:00:00.0000000','admin','admin',25,50),
    ('Kalil', 'Rua Desembargador Rocha Portela', 'São Paulo', 'SP', '2001-12-10T00:00:00.0000000', 'kalil.rocha@bandtec.com.br', 'admin', 25, 70),
	('Eric', 'Rua Mário Totta', 'São Paulo', 'SP', '2002-12-15T00:00:00.0000000', 'eric.lessa@bandtec.com.br', 'admin', 20, 65),
	('Victor', 'Rua Antônio Fortunato', 'São Paulo', 'SP', '2002-04-06T00:00:00.0000000', 'victor.barbosa@bandtec.com.br', 'admin', 27, 30),
	('Kaio', 'Rua Gaspar Coelho', 'São Paulo', 'SP', '2002-09-07T00:00:00.0000000', 'kaio.jesus@bandtec.com.br', 'admin', 23, 62),
	('Gabriel', 'Rua Professora Ernestina Loureiro de Miranda', 'São Paulo', 'SP', '2002-01-12T00:00:00.0000000', 'gabriel.marcolino@bandtec.com.br', 'admin', 21, 62),
	('Gabriel Marcolino da Silva', 'Rua Alencar Araripe', 'São Paulo', 'SP', '2002-12-01T00:00:00.0000000', 'gmarco.gmds@gmail.com', '46646', 27, 61),
	('gabriel', 'Rua Alencar Araripe', 'São Paulo', 'SP', '2002-01-12T00:00:00.0000000', 'gmarco.gmds@gmail.com', '123456', 23 ,57),
	('Hakel Boscolo Dias Blanco', 'Rua Ismael Neri', 'São Paulo', 'SP', '2001-12-06T00:00:00.0000000', 'hakel.blanco@gmail.com', 'hakel100', 24, 50),
	('Fernando', 'Rua Antônio Fortunato', 'São Paulo', 'SP', '2002-06-04T00:00:00.0000000', 'fernando@bandtec.com.br', 'admin', 30, 42),
	('', 'Via de Pedestre Marco Inicial', 'São Paulo', 'SP', '2002-09-11T00:00:00.0000000', 'sunguinath3@gmail.com', 'meupaudetoca', 24, 50),
	('isabelle', 'Rua Nelson Natal', 'Sumaré', 'SP', '2002-02-02T00:00:00.0000000', 'isabelle@gmail.com', '123456', 27, 57),
	('Gabriel curti', 'Praça Monte Cristo', 'São Paulo','SP', '2002-10-22T00:00:00.0000000', 'gabrielcurti15@gmail.com', 'gabrielcurti1', 20, 50),
	('Gabriel curti', 'Praça Monte Cristo', 'São Paulo', 'SP', '2002-10-22T00:00:00.0000000', 'gabrielcurti15@gmail.com','', 20, 50),
	('gabriell', 'Rua Alencar Araripe', 'São Paulo', 'SP', '2002-01-12T00:00:00.0000000', 'gmarco.gmds@gmail.com', '123', 21, 54),
	('douglas', 'Avenida O Trabuco Rádio Jornal', 'Osasco', 'SP', '2020-01-01T00:00:00.0000000', 'rouba@nao.com', '123456789', 24, 50),
	('kiko', 'Rua Alencar Araripe', 'São Paulo', 'SP', '2001-08-10T00:00:00.0000000', 'gmarco@gmail.com', '466453', 24, 53),
	('José', 'Rua Alencar Araripe', 'São Paulo', 'SP', '2002-05-26T00:00:00.0000000', 'jose.silva@bandtec.com.br', 'joka0810', 23, 52),
	('alaks', 'Rua Desembargador Rocha Portela', 'São Paulo', 'SP', '2002-01-12T00:00:00.0000000', 'kalil@kalil.com', '123456', 24, 50),
	('Thiago Emídio', 'Avenida Barro Branco', 'São Paulo','SP','2006-11-27T00:00:00.0000000','thiago.emidio.rr@gmail.com','thiago123', 25, 50);


create table parque (
    idParque int primary key identity,
    nome varchar(30),
    cpfGerente char(11),
    area int,
    imgParque varchar(80),
    avaliacao int,
    localizacao varchar(80),
    quantidadeclassificacao int
);

insert into parque (nome,cpfGerente,area,imgParque,avaliacao,localizacao,quantidadeclassificacao) values
	('Ibirapuera', '98284392801', 1584000, 'https://i.imgur.com/sTd7PBK.jpg', 4, 'Av. Pedro Álvares Cabral', 1),
    ('Independencia', '08640215855', 161300, 'https://i.imgur.com/Qrn5Hq5.png', 4, 'Av. Nazareth', 1),
    ('Carmo', '10086586858', 1500000, 'https://i.imgur.com/iV5lAVm.png', 4, 'Av. Afonso de Sampaio e Souza', 1),
	('Chico Mendes', '56507021873', 61600, 'https://i.imgur.com/nBxnjVO.png', 3, 'Rua Cembira', 1),
	('Aclimação', '44959086858', 112000, 'https://i.imgur.com/YRLx0Bh.png', 4, 'Rua Muniz de Souza', 1),
	('Anhanguera', '24181485803', 9500000, 'https://i.imgur.com/rbt0kyr.jpg', 5, 'Av. Fortunata Tadiello Natucci', 1),
	('Piqueri', '47256103808', 97200, 'https://i.imgur.com/to3Aj6h.png', 3, 'R. Tuiuti', 1);


create table sensor (
	idSensor int primary key,
	fkParque int,
	foreign key (fkParque) references parque(idParque)
);

insert into sensor values
    (1,1),
    (2,2),
    (3,3),
    (4,4),
    (5,5),
    (6,6),
    (7,7);

create table evento(
    idEvento int primary key identity,
	fkSensor int,
	foreign key (fkSensor) references sensor(idSensor),
	momento datetime,
	umidade decimal (5,2),
	temperatura decimal (4,2)
);

create table maquinas(
	idMaquina int primary key identity,
	usuario varchar(45),
	localizacaoMaquina varchar(45),
	fkParque int,
	foreign key (fkParque) references parque (idParque)
);

create table componente(
	idComponente int primary key identity,
	nome varchar(45),
	metrica varchar(45)
);

create table configuracao(
	idConfiguracao int primary key,
	fkMaquina int,
	foreign key (fkMaquina) references maquinas (idMaquina),
	fkComponente int,
	foreign key (fkComponente) references componente (idComponente),
	limiteAlerta decimal(5,2)
);

create table leituras(
	idMetrica int primary key identity,
	valor decimal(6,2),
	momento datetime,
	fkConfiguracao int,
	foreign key (fkConfiguracao) references configuracao (idConfiguracao)
);







