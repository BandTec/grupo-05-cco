var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Admin = require('../models').Admin;



let sessoes_admin = [];



/* Recuperar dados dos Admins (login e senha)*/
router.post('/admin', function(req, res, next) {
    console.log('Recuperando Admin por login e senha dos admins');

    var login = req.body.login; // depois de .body, use o nome (name) do campo em seu formulário de login
    var senha = req.body.senha; // depois de .body, use o nome (name) do campo em seu formulário de login	

    let instrucaoSql = `select * from administracao where email='${login}' and senha='${senha}'`;
    console.log(instrucaoSql);

    sequelize.query(instrucaoSql, {
        model: Admin
    }).then(resultado => {
        console.log(`Encontrados: ${resultado.length}`);

        if (resultado.length == 1) {
            sessoes_admin.push(resultado[0].dataValues.email);
            console.log('sessoes: ', sessoes_admin);
            res.json(resultado[0]);
        } else if (resultado.length == 0) {
            res.status(403).send('Login e/ou senha inválido(s)');
        } else {
            res.status(403).send('Mais de um Admin com o mesmo login e senha!');
        }

    }).catch(erro => {
        console.error(erro);
        res.status(500).send(erro.message);
    });
});


/* Cadastrar Admin */


    /* verificacao do admin */

router.get('/sessao/:login_admin', function(req, res, next) {
    let login = req.params.login_admin;
    console.log(`Verificando se o Admin ${login} tem sessão`);

    let tem_sessao = false;
    for (let u = 0; u < sessoes_admin.length; u++) {
        if (sessoes_admin[u] == login) {
            tem_sessao = true;
            break;
        }
    }

    if (tem_sessao) {
        let mensagem = `Admin ${login} possui sessão ativa!`;
        console.log(mensagem);
        res.send(mensagem);
    } else {
        res.sendStatus(403);
    }

});

/* Logoff do admin */
router.get('/sair/:login-admin', function(req, res, next) {
    let login = req.params.login;
    console.log(`Finalizando a sessão do Admin ${login}`);
    let nova_sessoes = []
    for (let u = 0; u < sessoes_admin.length; u++) {
        if (sessoes_admin[u] != login) {
            nova_sessoes.push(sessoes_admin[u]);
        }
    }
    sessoes_admin = nova_sessoes;
    res.send(`Sessão do Admin ${login} finalizada com sucesso!`);
});

module.exports = router;