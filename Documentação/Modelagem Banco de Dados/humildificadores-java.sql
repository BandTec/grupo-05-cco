use humildificadores;
select nome from maquinas, componentes where idMaquina = fkMaquina;
select * from maquinas, componentes, configuracao;
select * from maquinas;
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
 select nome, idParque from parque, maquinas;