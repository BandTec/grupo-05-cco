var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Leitura = require('../models').Leitura;

/* Recuperar as últimas N leituras */
router.get('/ultimas/:Parque', function(req, res, next) {

    const Parque = req.params.Parque;

    // quantas são as últimas leituras que quer? 8 está bom?
    const limite_linhas = 7;

    console.log(`Recuperando as últimas ${limite_linhas} leituras`);

    const instrucaoSql = `select top ${limite_linhas} 
						temperatura, 
						umidade, 
						momento,
						FORMAT(momento,'HH:mm:ss') as momento_grafico 
                        from evento, sensor
                        where fkParque = ${Parque} and fkSensor = idSensor
                        order by idEvento desc `;

    sequelize.query(instrucaoSql, {
            model: Leitura,
            mapToModel: true
        })
        .then(resultado => {
            console.log(`Encontrados: ${resultado.length}`);
            res.json(resultado);
        }).catch(erro => {
            console.error(erro);
            res.status(500).send(erro.message);
        });
});


// tempo real (último valor de cada leitura)
router.get('/tempo-real/:Parque', function(req, res, next) {

    const Parque = req.params.Parque;

    console.log(`Recuperando a última leitura`);

    const instrucaoSql = `select top 1 temperatura, umidade, FORMAT(momento,'HH:mm:ss') as momento_grafico  
                        from evento, sensor
                        where fkParque = ${Parque} and fkSensor = idSensor
                        order by idEvento desc`;

    sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
        .then(resultado => {
            res.json(resultado[0]);
        }).catch(erro => {
            console.error(erro);
            res.status(500).send(erro.message);
        });

});


// estatísticas (max, min, média, mediana, quartis etc)
router.get('/estatisticas', function(req, res, next) {

    console.log(`Recuperando as estatísticas atuais`);

    const instrucaoSql = `select 
							max(temperatura) as temp_maxima, 
							min(temperatura) as temp_minima, 
							avg(temperatura) as temp_media,
							max(umidade) as umidade_maxima, 
							min(umidade) as umidade_minima, 
							avg(umidade) as umidade_media 
						from evento`;

    sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
        .then(resultado => {
            res.json(resultado[0]);
        }).catch(erro => {
            console.error(erro);
            res.status(500).send(erro.message);
        });

});

router.get('/parques', function(req, res, next) {

    console.log(`Recuperando a última leitura`);

    const instrucaoSql = `
    select idParque, nome, imgParque, estrelas, fkparque   
        from parque
        LEFT OUTER JOIN (
            select fkParque ,AVG(Avaliacao) as estrelas 
                from avaliacaoParque ap 
                group by fkParque) as avalia ON (idParque = fkparque)
	order by idParque desc`;

    sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
        .then(resultado => {
            res.json(resultado);
        }).catch(erro => {
            console.error(erro);
            res.status(500).send(erro.message);
        });

});

router.get('/parques/:Parque', function(req, res, next) {

    const Parque = req.params.Parque;

    console.log(`Recuperando a última leitura`);

    const instrucaoSql = `
    select temperatura, umidade, avalia.estrelas, parque.*
        from   parque
        inner JOIN sensor s ON parque.idParque=s.fkParque
        left outer JOIN evento e ON s.idSensor = e.fkSensor 
        left outer JOIN (
            select fkParque ,AVG(Avaliacao) as estrelas 
                from avaliacaoParque ap where fkParque = ${Parque}
                group by fkParque) as avalia ON (idParque = avalia.fkparque)
    where parque.idParque = ${Parque} 
    `;

    sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
        .then(resultado => {
            res.json(resultado);
        }).catch(erro => {
            console.error(erro);
            res.status(500).send(erro.message);
        });

});

router.get('/eventos/:Cliente', function(req, res, next) {

    const Cliente = req.params.Cliente;

    console.log(`Recuperando os eventos dos parques`);

    const instrucaoSql = `select parqueEventos.*, CECOUNT.quantidade ,   FORMAT(dataEventos,'dd/MM/yyyy ') as data, FORMAT(dataEventos,'HH:mm') as hora , parque.nome, CE.fkCliente 
	    from parqueEventos
	    inner  join parque on fkParque = idParque
	    LEFT OUTER JOIN (select COUNT(fkParqueEventos ) as quantidade, fkParqueEventos from clienteEventos group by fkparqueEventos) CECOUNT on CECOUNT.fkParqueEventos = idParqueEventos 
	    LEFT OUTER JOIN (select fkcliente, fkParqueEventos from clienteEventos where fkcliente = ${Cliente}) CE on CE.fkParqueEventos = idParqueEventos 
	    where dataEventos >= GETDATE ( ) 
	    order by dataEventos  desc`;

    sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
        .then(resultado => {
            res.json(resultado);
        }).catch(erro => {
            console.error(erro);
            res.status(500).send(erro.message);
        });

});

router.get('/todosParques', function(req, res, next) {
    const instrucaoSql = `select * from parque order by idParque desc`;

    sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
        .then(resultado => {
            res.json(resultado);
        }).catch(erro => {
            console.error(erro);
            res.status(500).send(erro.message);
        });

});

router.get('/todosClientes', function(req, res, next) {
    const instrucaoSql = `select * from cliente order by idCliente desc`;

    sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
        .then(resultado => {
            res.json(resultado);
        }).catch(erro => {
            console.error(erro);
            res.status(500).send(erro.message);
        });

});

router.get('/todosEventos', function(req, res, next) {
    const instrucaoSql = `select idParqueEventos, nome, img_parque, tituloEvento, descricao, FORMAT(dataEventos,'dd/MM/yyyy HH:mm') as dataHora from parqueEventos, parque where fkParque = idParque order by dataEventos desc;`;

    sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
        .then(resultado => {
            res.json(resultado);
        }).catch(erro => {
            console.error(erro);
            res.status(500).send(erro.message);
        });

});
 
router.get('/create', function(req, res, next) {

    console.log(`Recuperando a última leitura`);

    const instrucaoSql = `select * from parque order by idParque desc`;

    sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
        .then(resultado => {
            res.json(resultado);
        }).catch(erro => {
            console.error(erro);
            res.status(500).send(erro.message);
        });

});


module.exports = router;
