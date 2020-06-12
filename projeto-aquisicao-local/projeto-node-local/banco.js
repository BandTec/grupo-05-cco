const configuracoes = {
    banco: {
        server: "humildificadores.database.windows.net",
        user: "adminlocal",
        password: "Safadinhos123@",
        database: "bancohumildificadores",
        options: {
            encrypt: true
        },
        pool: {
            max: 4,
            min: 1,
            idleTimeoutMillis: 30000,
            connectionTimeout: 5000
        }
    }
}

var sql = require('mssql');
sql.on('error', err => {
    console.error(`Erro de Conexão: ${err}`);
});


function conectar() {
    sql.close();
    return sql.connect(configuracoes.banco)
}

module.exports = {
    conectar: conectar,
    sql: sql
}