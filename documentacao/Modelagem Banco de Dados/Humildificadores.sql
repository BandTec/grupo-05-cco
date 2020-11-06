create database humildificadores;
use humildificadores;

create table cliente (
	idCliente int primary key identity,
	nome varchar (30),
	rua varchar (40),
	cidade varchar (20),
	estado varchar (20),
	ddn date,
	email varchar (30),
	senha varchar (15),
    preferenciaTemp decimal (4,2),
    preferenciaUmid decimal (5,2)
);

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

create table sensor (
	idSensor int primary key,
	fkParque int,
	foreign key (fkParque) references parque(idParque)
);

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







