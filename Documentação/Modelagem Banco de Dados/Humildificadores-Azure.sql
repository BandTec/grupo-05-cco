create table administracao (
	idAdministracao int primary key identity,
	nome varchar(50),
	email varchar(45),
	senha varchar(45)
);

create table cliente (
	idCliente int primary key identity,
	nome varchar (60),
	rua varchar (80),
	cidade varchar (20),
	estado varchar (20),
	email varchar (40),
	senha varchar (15)
);

insert into cliente values
	('Thiago Emídio', 'Avenida Barro Branco', 'São Paulo', 'SP', 'thiago.emidio.rr@gmail.com', 'thiago123'),
	('Kalil', 'Rua Desembargador Rocha Portela', 'São Paulo', 'SP', 'admin@admin.com', 'admin'),
	('Kaio', 'Rua Gaspar Coelho', 'São Paulo', 'SP', 'kaio.jesus@bandtec.com.br', 'admin'),
	('Douglas Dourado', 'Avenida O Trabuco Rádio Jornal', 'Osasco', 'SP', 'rouba@nao.com', '123456'),
	('José Paulo', 'Avenida Barro Branco', 'São Paulo', 'SP', 'jose.silva@bandtec.com.br', 'admin');

create table parque (
	idParque int primary key identity,
	nome varchar(30),
	telefone char(10),
	imgParque varchar(60),
	localizacao varchar(500)
);

insert into parque values
	('Ibirapuera', '41473828', 'https://i.imgur.com/sTd7PBK.jpg', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3656.4715360935493!2d-46.65982228518805!3d-23.587416184669973!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce59f1069d11d1%3A0xcb936109af9ce541!2sParque%20Ibirapuera!5e0!3m2!1spt-BR!2sbr!4v1605707745495!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	('Independencia', '41844739', 'https://i.imgur.com/Qrn5Hq5.png', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7313.124362860315!2d-46.609827272411366!3d-23.58416289432265!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce5b944452677b%3A0xd8cac2fbf200334a!2sParque%20Independ%C3%AAncia!5e0!3m2!1spt-BR!2sbr!4v1605708159568!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	('Carmo', '26178293', 'https://i.imgur.com/iV5lAVm.png', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d14627.63183415001!2d-46.48435442235275!3d-23.571748954841855!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce6640dcf05f2d%3A0x81eaa76f515af8ea!2sParque%20do%20Carmo%20-%20Jardim%20Nossa%20Sra.%20do%20Carmo%2C%20S%C3%A3o%20Paulo%20-%20SP!5e0!3m2!1spt-BR!2sbr!4v1605708385002!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	('Chico Mendes', '38291823', 'https://i.imgur.com/nBxnjVO.png', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3656.91776214826!2d-46.78622238255615!3d-23.5713969!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce5583005abe75%3A0x75d6441b9fc0174d!2sParque%20Chico%20Mendes!5e0!3m2!1spt-BR!2sbr!4v1605708598805!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	('Aclimação', '42638202', 'https://i.imgur.com/YRLx0Bh.png', '<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d7313.651252786302!2d-46.628551300000005!3d-23.574705366803066!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xb694e44849fc52b9!2zUGFycXVlIEFjbGltYcOnw6Nv!5e0!3m2!1spt-BR!2sbr!4v1605708697655!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	('Anhanguera', '48283920', 'https://i.imgur.com/rbt0kyr.jpg', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3661.1672604127602!2d-46.784458285191455!3d-23.4183243847541!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94cefcd74d9f1eb9%3A0xaf53b07d361d0ea5!2sParque%20Anhanguera!5e0!3m2!1spt-BR!2sbr!4v1605708877859!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	('Piqueri', '41849023', 'https://i.imgur.com/to3Aj6h.png', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3658.11573065758!2d-46.576160085189116!3d-23.528339584699292!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce5edfb17a8b4f%3A0xc83a45b502f37523!2sParque%20Piqueri!5e0!3m2!1spt-BR!2sbr!4v1605708928147!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>'),
	('Villa-Lobos', '40293827', 'https://i.imgur.com/btaHsxc.png', '<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3657.6766898464884!2d-46.72828538518882!3d-23.544128184691473!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce56fa971a5e7d%3A0x6f6b8e0ec9daadd1!2sParque%20Villa-Lobos!5e0!3m2!1spt-BR!2sbr!4v1605708979725!5m2!1spt-BR!2sbr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>');

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
	(7,7),
	(8,8);

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
	fkParque int,
	foreign key (fkParque) references parque(idParque)
);

create table componentes(
	idComponente int primary key identity,
	nome varchar(45),
	metrica varchar(45)
);

insert into componentes values 
	('placa_mae', 'Nome'),
	('cpu_count', 'Integer'),
	('cpu_media_temperatura', 'ºC'),
	('cpu_media_percent', '%'),
	('cpu_media_clock', 'MHz'),
	('memory_load', '%'),
	('memory_use', '%'),
	('memory_available', '%'),
	('video_card', 'Nome');

create table configuracao(
	idConfiguracao int primary key identity,
	fkMaquina int,
	foreign key (fkMaquina) references maquinas (idMaquina),
	fkComponente int,
	foreign key (fkComponente) references componentes (idComponente),
	limiteAlerta decimal(5,2)
);

create table leituras (
	idMetrica int primary key identity,
	valor decimal(6,2),
	momento datetime,
	fkConfiguracao int,
	foreign key (fkConfiguracao) references configuracao (idConfiguracao)
);

create table parqueEventos (
	idParqueEventos int primary key identity,
	fkParque int,
	img_parque varchar(60),
	tituloEvento varchar(45),
	descricao varchar(500),
	dataEventos datetime,
	foreign key (fkParque) references parque (idParque)
);

insert into parqueEventos values
	(1,'https://i.imgur.com/3A6MaPc.png','Pokémon Go','Evento de caça Pokémon LENDÁRIO! Venha se divertir nesta caçada surpreendente','2020-12-08T14:00:00'),
	(2,'https://i.imgur.com/MURIjKw.png','Corrida da terceira idade','Venha participar da corrida da terceira idade! Os primeiros colocados receberão ótimos prêmios','2020-12-16T16:00:00'),
	(3,'https://i.imgur.com/KI7LQnw.png','Show do 7 minutoz','Grande apresentação do grupo 7 minutoz, Rap do itachi, Rap do Mario, e muito mais!','2021-01-25T20:00:00'),
	(4,'https://i.imgur.com/BWuWAwP.png','Natal comunitário','Encontro para para doações de roupas e mantimentos. Ajude as pessoas que mais precisam!','2020-12-23T08:30:00'),
	(5,'https://i.imgur.com/OaJILRQ.png','Show do Alok','Um dos maiores DJs da atualidade, com músicas de arrepiar! Participações de Marshmello e Tiësto','2021-02-20T09:00:00'),
	(6,'https://i.imgur.com/xP7GzV2.jpg','Evento 7 de setembro','Venha comemorar o dia de nossa independencia! Traga a família e venha comemorar essa data maravavilhosa','2021-09-07T15:00:00'),
	(1,'https://i.imgur.com/apEnMlz.png','Brasil Game Show','Venha particiar do maior evento de Games da américa Latina!','2021-03-10T08:30:00'),
	(4,'https://i.imgur.com/B0XthAy.png','Futebol','Venha conhecer o futeboleiro mais famoso do brasil: William Leal!','2020-11-13T22:00:00');

create table clienteEventos (
	idClienteEventos int primary key identity,
	fkParqueEventos int,
	fkCliente int,
	foreign key (fkParqueEventos) references parqueEventos (idParqueEventos),
	foreign key (fkCliente) references cliente (idCliente)
);

create table avaliacaoParque(
	idAvaliacaoParque int primary key identity,
    fkCliente int,
    fkParque int,
    Avaliacao int,
    foreign key (fkCliente) references cliente (idCliente),
    foreign key (fkParque) references parque (idParque)
);