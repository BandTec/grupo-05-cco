use humildificadores;
select nome from maquinas, componentes where idMaquina = fkMaquina;
select * from maquinas, componentes, configuracao;
select * from maquinas;select * from leituras limit 30;
select * from configuracao;
select * from configuracao, maquinas, componentes where fkMaquina = idMaquina and fkMaquina = 3;
select idMetrica, valor, momento, fkParque from leituras,
			componentes, maquinas where fkParque = 1 and fkComponente = idComponente and nome = 'cpu_media_temperatura' order by idMetrica asc limit 20;
select idComponente, nome, fkMaquina from componentes, configuracao where fkMaquina = 1 and fkComponente = idComponente;
select * from componentes;
select fkMaquina, componentes.nome from parque, maquinas, configuracao, componentes where idParque = fkParque and idParque = 2 and idMaquina = fkMaquina and idComponente = fkComponente;
select * from componentes;
select idMetrica, valor, momento, fkParque from leituras,
 componentes, maquinas where fkParque = 1 and fkComponente = idComponente and nome = 'cpu_media_temperatura' order by idMetrica asc limit 20;
 desc configuracao;
 select limiteAlerta, maquinas.usuario, componentes.nome from parque, maquinas, configuracao, componentes where idParque = fkParque and idParque = 1 and idMaquina = fkMaquina and idComponente = fkComponente;
 select nome, idParque, usuario from parque, maquinas where fkParque = idParque;
 
 select idMetrica, valor, momento, fkParque from leituras, componentes, maquinas where fkParque = 1 and fkComponente = idComponente and nome = 'cpu_media_temperatura' order by idMetrica desc limit 10;
 select idMetrica, valor, momento, fkParque, componentes.nome from leituras, componentes, maquinas where fkParque = 1 and fkComponente = idComponente and nome = 'cpu_media_temperatura' order by idMetrica desc limit 10;
 
 select * from leituras;
 select limiteAlerta, maquinas.usuario, idMetrica, componentes.nome, valor
 from parque, maquinas, configuracao c, componentes, leituras l
 where idParque = fkParque
 and idParque = 1
 and idMaquina = fkMaquina
 and l.fkComponente = c.fkComponente
 and componentes.nome = 'cpu_media_temperatura';
 
 select * from leituras;
 select * from componentes;
 select * from maquinas;
 select * from configuracao;
 select idMetrica, valor, momento, limiteAlerta, fkParque from leituras l, componentes, maquinas, configuracao c
 where fkParque = 1 and l.fkComponente = idComponente and c.fkComponente = idComponente and nome = 'cpu_media_temperatura' order by idMetrica;
 select * from configuracao c, maquinas, componentes, leituras where idComponente = c.fkComponente and fkMaquina = idMaquina and idConfiguracao = fkConfiguracao;
 
 select nome from configuracao, maquinas, componentes where idComponente = fkComponente and idMaquina = fkMaquina and usuario = 'HSL017';
select idMetrica, valor, momento, nome, limiteAlerta, usuario, fkParque from leituras l, componentes, maquinas, configuracao c
where l.fkComponente = idComponente and c.fkComponente = idComponente order by idMetrica;

select idMetrica, valor, usuario, nome, limiteAlerta from leituras lei, configuracao con, maquinas, componentes where fkConfiguracao = idConfiguracao and idMaquina = fkMaquina and con.fkComponente = idComponente and fkMaquina = 2 order by idMetrica;
idMetrica, valor, usuario

select nome, idParque, usuario from parque, maquinas where idParque = fkParque;
select nome, idParque from parque;
 
 
 