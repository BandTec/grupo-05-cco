var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Usuario = require('../models').Usuario;


router.post('/criar-parque', function(req, res, next) {
    console.log('Criando um usuário');

    Usuario.create({
        email: req.body.inputEmail,
        senha: req.body.inputPassword,
        nome: req.body.inputNome,
        cpf: req.body.inputCpf,
        ddn: req.body.inputArea,
        login: req.body.inputImgParque,
        senha: req.body.inputLocalizacao,
    }).then(resultado => {
        console.log(`Registro criado: ${resultado}`)
        res.send(resultado);
    }).catch(erro => {
        console.error(erro);
        res.status(500).send(erro.message);
    });
});

module.exports = route;
