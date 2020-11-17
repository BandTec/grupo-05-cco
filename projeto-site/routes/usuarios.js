var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Usuario = require('../models').Usuario;
var Eventos = require('../models').Eventos;
var Parques = require('../models').Parques;

let sessoes = [];
let sessoes_admin = [];

/* Recuperar usuário por login e senha */
router.post('/autenticar', function(req, res, next) {
    console.log('Recuperando usuário por login e senha');

    var login = req.body.login; // depois de .body, use o nome (name) do campo em seu formulário de login
    var senha = req.body.senha; // depois de .body, use o nome (name) do campo em seu formulário de login	

    let instrucaoSql = `select * from cliente where email='${login}' and senha='${senha}'`;
    console.log(instrucaoSql);

    sequelize.query(instrucaoSql, {
        model: Usuario
    }).then(resultado => {
        console.log(`Encontrados: ${resultado.length}`);

        if (resultado.length == 1) {
            sessoes.push(resultado[0].dataValues.email);
            console.log('sessoes: ', sessoes);
            res.json(resultado[0]);
        } else if (resultado.length == 0) {
            res.status(403).send('Login e/ou senha inválido(s)');
        } else {
            res.status(403).send('Mais de um usuário com o mesmo login e senha!');
        }

    }).catch(erro => {
        console.error(erro);
        res.status(500).send(erro.message);
    });
});

/* Recuperar dados dos Admins (login e senha)*/
router.post('/admin', function(req, res, next) {
    console.log('Recuperando usuário por login e senha dos admins');

    var login = req.body.login; // depois de .body, use o nome (name) do campo em seu formulário de login
    var senha = req.body.senha; // depois de .body, use o nome (name) do campo em seu formulário de login	

    let instrucaoSql = `select * from cliente where email='${login}' and senha='${senha}'`;
    console.log(instrucaoSql);

    sequelize.query(instrucaoSql, {
        model: Usuario
    }).then(resultado => {
        console.log(`Encontrados: ${resultado.length}`);

        if (resultado.length == 1) {
            sessoes_admin.push(resultado[0].dataValues.email);
            console.log('sessoes: ', sessoes_admin);
            res.json(resultado[0]);
        } else if (resultado.length == 0) {
            res.status(403).send('Login e/ou senha inválido(s)');
        } else {
            res.status(403).send('Mais de um usuário com o mesmo login e senha!');
        }

    }).catch(erro => {
        console.error(erro);
        res.status(500).send(erro.message);
    });
});


/* Cadastrar usuário */
router.post('/cadastrar', function(req, res, next) {
    console.log('Criando um usuário');

    Usuario.create({
        nome: req.body.nome,
        rua: req.body.rua,
        cidade: req.body.cidade,
        estado: req.body.estado,
        ddn: req.body.ddn,
        login: req.body.login,
        senha: req.body.senha,
        preferenciaTemp: req.body.preferenciaTemp,
        preferenciaUmid: req.body.preferenciaUmid
    }).then(resultado => {
        console.log(`Registro criado: ${resultado}`)
        res.send(resultado);
    }).catch(erro => {
        console.error(erro);
        res.status(500).send(erro.message);
    });
});


/* Verificação de usuário */
router.get('/sessao/:login', function(req, res, next) {
    let login = req.params.login;
    console.log(`Verificando se o usuário ${login} tem sessão`);

    let tem_sessao = false;
    for (let u = 0; u < sessoes.length; u++) {
        if (sessoes[u] == login) {
            tem_sessao = true;
            break;
        }
    }

    if (tem_sessao) {
        let mensagem = `Usuário ${login} possui sessão ativa!`;
        console.log(mensagem);
        res.send(mensagem);
    } else {
        res.sendStatus(403);
    }

});

    /* verificacao do admin */

router.get('/sessaoAdmin/:login_admin', function(req, res, next) {
    let login = req.params.login_admin;
    console.log(`Verificando se o usuário ${login} tem sessão`);

    let tem_sessao = false;
    for (let u = 0; u < sessoes_admin.length; u++) {
        if (sessoes_admin[u] == login) {
            tem_sessao = true;
            break;
        }
    }

    if (tem_sessao) {
        let mensagem = `Usuário ${login} possui sessão ativa!`;
        console.log(mensagem);
        res.send(mensagem);
    } else {
        res.sendStatus(403);
    }

});

/* Logoff do admin */
router.get('/sair/:login-admin', function(req, res, next) {
    let login = req.params.login;
    console.log(`Finalizando a sessão do usuário ${login}`);
    let nova_sessoes = []
    for (let u = 0; u < sessoes_admin.length; u++) {
        if (sessoes_admin[u] != login) {
            nova_sessoes.push(sessoes_admin[u]);
        }
    }
    sessoes_admin = nova_sessoes;
    res.send(`Sessão do usuário ${login} finalizada com sucesso!`);
});


/* Logoff de usuário */
router.get('/sair/:login', function(req, res, next) {
    let login = req.params.login;
    console.log(`Finalizando a sessão do usuário ${login}`);
    let nova_sessoes = []
    for (let u = 0; u < sessoes.length; u++) {
        if (sessoes[u] != login) {
            nova_sessoes.push(sessoes[u]);
        }
    }
    sessoes = nova_sessoes;
    res.send(`Sessão do usuário ${login} finalizada com sucesso!`);
});


/* Recuperar todos os usuários */
router.get('/', function(req, res, next) {
    console.log('Recuperando todos os usuários');
    Usuario.findAndCountAll().then(resultado => {
        console.log(`${resultado.count} registros`);

        res.json(resultado.rows);
    }).catch(erro => {
        console.error(erro);
        res.status(500).send(erro.message);
    });
});

/* Registrar Evento */
router.post('/registrar-evento', function(req, res, next) {
    console.log('Registrando um evento');

    Eventos.create({
        img_evento: req.body.inputimg,
        tituloEvento: req.body.inputTituloEvento,
        descricao: req.body.inputDescricao,
        dataEventos: req.body.inputDataEventos,
        fkParque: req.body.inputfkParque
    }).then(resultado => {
        console.log(`Registro criado: ${resultado}`)
        res.send(resultado);
    }).catch(erro => {
        console.error(erro);
        res.status(500).send(erro.message);
    });
});

/* Registrar Parque */
router.post('/registrar-parque', function(req, res, next) {
    console.log('Registrando um parque');

    Parques.create({
        nome: req.body.inputNome,
        cpf: req.body.inputCpf,
        area: req.body.inputArea,
        img_parque: req.body.inputImgParque,
        localizacao: req.body.inputLocalizacao
    }).then(resultado => {
        console.log(`Registro criado: ${resultado}`)
        res.send(resultado);
    }).catch(erro => {
        console.error(erro);
        res.status(500).send(erro.message);
    });
});

module.exports = router;