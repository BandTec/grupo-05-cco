create database humildificadores;
use humildificadores;
create table administracao (
	idAdministracao int primary key auto_increment,
    nome varchar(50),
    email varchar(45),
    senha varchar(45)
);

create table cliente (
	idCliente int primary key auto_increment,
	nome varchar (60),
	rua varchar (80),
	cidade varchar (20),
	estado varchar (20),
	email varchar (40),
	senha varchar (15)
);

create table maquinas(
	idMaquina int primary key auto_increment,
	usuario varchar(45)
);

create table parque (
    idParque int primary key auto_increment,
    nome varchar(30),
    telefone char(10),
    imgParque varchar(60),
    localizacao varchar(500),
    fkMaquina int,
    foreign key (fkMaquina) references maquinas(idMaquina)
);

create table sensor (
	idSensor int primary key,
	fkParque int,
	foreign key (fkParque) references parque(idParque)
);

create table evento(
    idEvento int primary key auto_increment,
	fkSensor int,
	foreign key (fkSensor) references sensor(idSensor),
	momento datetime,
	umidade decimal (5,2),
	temperatura decimal (4,2)
);

create table componentes(
	idComponente int primary key auto_increment,
	nome varchar(45),
	metrica varchar(45)
);

insert into componentes values 
	(null,'placa_mae','String'),
    (null,'cpu_count','Integer'),
    (null,'cpu_media_temperatura','ºC'),
    (null,'cpu_media_percent','%'),
    (null,'cpu_media_clock','MHz'),
    (null,'memory_load','%'),
    (null,'memory_use','%'),
    (null,'memory_available','%'),
    (null,'video_card','String');

create table configuracao(
	idConfiguracao int primary key,
	fkMaquina int,
	foreign key (fkMaquina) references maquinas (idMaquina),
	fkComponente int,
	foreign key (fkComponente) references componentes (idComponente),
	limiteAlerta decimal(5,2)
);

create table leituras(
	idMetrica int primary key auto_increment,
	valor decimal(6,2),
	momento datetime,
	fkConfiguracao int,
	foreign key (fkConfiguracao) references configuracao (idConfiguracao)
);

create table parqueEventos(
	idParqueEventos int primary key auto_increment,
	fkParque int,
	img_parque varchar(60),
	tituloEvento varchar(45),
	descricao varchar(500),
	dataEventos datetime,
	foreign key (fkParque) references parque (idParque)
);

create table clienteEventos(
	idClienteEventos int primary key auto_increment,
	fkParqueEventos int,
	fkCliente int,
	foreign key (fkParqueEventos) references parqueEventos (idParqueEventos),
	foreign key (fkCliente) references cliente (idCliente)
);

create table avaliacaoParque(
	idAvaliacaoParque int primary key auto_increment,
    fkCliente int,
    fkParque int,
    Avaliacao int,
    foreign key (fkCliente) references cliente (idCliente),
    foreign key (fkParque) references parque (idParque)
);