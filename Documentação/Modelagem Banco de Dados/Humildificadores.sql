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

create table parque (
    idParque int primary key auto_increment,
    nome varchar(30),
    telefone char(10),
    imgParque varchar(60),
    localizacao varchar(500)
);

insert into parque values
	(null, 'Ibirapuera', '41473828', 'https://i.imgur.com/sTd7PBK.jpg', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3656.4715360935493!2d-46.65982228518805!3d-23.587416184669973!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce59f1069d11d1%3A0xcb936109af9ce541!2sParque%20Ibirapuera!5e0!3m2!1spt-BR!2sbr!4v1605707745495!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	(null, 'Independencia', '41844739', 'https://i.imgur.com/Qrn5Hq5.png', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7313.124362860315!2d-46.609827272411366!3d-23.58416289432265!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce5b944452677b%3A0xd8cac2fbf200334a!2sParque%20Independ%C3%AAncia!5e0!3m2!1spt-BR!2sbr!4v1605708159568!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	(null, 'Carmo', '26178293', 'https://i.imgur.com/iV5lAVm.png', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d14627.63183415001!2d-46.48435442235275!3d-23.571748954841855!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce6640dcf05f2d%3A0x81eaa76f515af8ea!2sParque%20do%20Carmo%20-%20Jardim%20Nossa%20Sra.%20do%20Carmo%2C%20S%C3%A3o%20Paulo%20-%20SP!5e0!3m2!1spt-BR!2sbr!4v1605708385002!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	(null, 'Chico Mendes', '38291823', 'https://i.imgur.com/nBxnjVO.png', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3656.91776214826!2d-46.78622238255615!3d-23.5713969!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce5583005abe75%3A0x75d6441b9fc0174d!2sParque%20Chico%20Mendes!5e0!3m2!1spt-BR!2sbr!4v1605708598805!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	(null, 'Aclimação', '42638202', 'https://i.imgur.com/YRLx0Bh.png', '<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d7313.651252786302!2d-46.628551300000005!3d-23.574705366803066!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xb694e44849fc52b9!2zUGFycXVlIEFjbGltYcOnw6Nv!5e0!3m2!1spt-BR!2sbr!4v1605708697655!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	(null, 'Anhanguera', '48283920', 'https://i.imgur.com/rbt0kyr.jpg', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3661.1672604127602!2d-46.784458285191455!3d-23.4183243847541!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94cefcd74d9f1eb9%3A0xaf53b07d361d0ea5!2sParque%20Anhanguera!5e0!3m2!1spt-BR!2sbr!4v1605708877859!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	(null, 'Piqueri', '41849023', 'https://i.imgur.com/to3Aj6h.png', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3658.11573065758!2d-46.576160085189116!3d-23.528339584699292!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce5edfb17a8b4f%3A0xc83a45b502f37523!2sParque%20Piqueri!5e0!3m2!1spt-BR!2sbr!4v1605708928147!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	(null, 'Villa-Lobos', '40293827', 'https://i.imgur.com/btaHsxc.png', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3657.6766898464884!2d-46.72828538518882!3d-23.544128184691473!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce56fa971a5e7d%3A0x6f6b8e0ec9daadd1!2sParque%20Villa-Lobos!5e0!3m2!1spt-BR!2sbr!4v1605708979725!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>');

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
	umidade float (5,2),
	temperatura float (4,2)
);

create table maquinas(
	idMaquina int primary key auto_increment,
	usuario varchar(45),
    fkParque int,
    foreign key (fkParque) references parque(idParque)
);

insert into maquinas values
	(null, 'kaiob', 1);

create table componentes(
	idComponente int primary key auto_increment,
	nome varchar(45),
	metrica varchar(45)
);

insert into componentes values 
    (null,'cpu_count','Integer'),
    (null,'cpu_media_temperatura','ºC'),
    (null,'cpu_media_percent','%'),
    (null,'cpu_media_clock','MHz'),
    (null,'memory_load','%'),
    (null,'memory_use','%'),
    (null,'memory_available','%');

create table configuracao(
	idConfiguracao int primary key auto_increment,
	fkMaquina int,
	fkComponente int,
	limiteAlerta float(5,2),
	foreign key (fkMaquina) references maquinas (idMaquina),
	foreign key (fkComponente) references componentes (idComponente)
);

create table leituras(
	idMetrica int primary key auto_increment,
	valor varchar(10),
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

create table pytohms2 (
	id int primary key auto_increment,
	user_desktop varchar(50),
	placa_mae varchar(50),
	cpu_count int,
	cpu_media_temperatura varchar(10),
	cpu_media_percent varchar(10),
	cpu_media_clock varchar(10),
	memory_load varchar(10),
	memory_use varchar(10),
	memory_available varchar(10),
	video_card varchar(50)
);

select idMaquina from maquinas where usuario = 'DSK-PCSSD0001';

select fkMaquina from maquinas, configuracao where usuario = 'HSL017';
select fkMaquina,usuario from maquinas m, configuracao c where  c.fkMaquina = m.idMaquina and usuario = 'DSK-PCSSD0001';

select * from pytohms2 order by id desc;

select nome from parque;
select * from configuracao ,maquinas, componentes where idComponente = fkComponente and idMaquina = fkMaquina;
select * from componentes, configuracao where fkComponente = idComponente;
select * from componentes, configuracao, leituras where fkConfiguracao = idConfiguracao;
insert into configuracao values
	(null, 1, 3, '80.00');
    
select * from leituras, configuracao, componentes where fkConfiguracao = idConfiguracao and fkComponente = idComponente order by idMetrica desc;

select * from maquinas;
select * from componentes;
select * from configuracao;
select * from leituras l, configuracao c where l.fkConfiguracao = c.idConfiguracao order by idMetrica desc;

select * from maquinas maq, componentes com, configuracao con, leituras l where maq.idMaquina = con.fkMaquina and maq.idMaquina = con.fkMaquina and com.idComponente = con.fkComponente and con.idConfiguracao = l.fkConfiguracao;

drop database humildificadores;

