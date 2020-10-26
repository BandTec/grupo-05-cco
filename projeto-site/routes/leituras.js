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

    const instrucaoSql = `select idParque, nome, imgParque, (avaliacao / quantidadeclassificacao) as estrelas 
                            from parque order by idParque desc`;

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

    const instrucaoSql = `select parque.*, (avaliacao / quantidadeclassificacao) as estrelas, temperatura, umidade from parque, sensor, evento
    where idParque = ${Parque} and idParque = fkParque and idSensor = fkSensor`;

    sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
        .then(resultado => {
            res.json(resultado);
        }).catch(erro => {
            console.error(erro);
            res.status(500).send(erro.message);
        });

});


module.exports = router;