let login_usuario;
let nome_usuario;

function redirecionar_login() {
    window.location.href = 'login.html';
}

function verificar_autenticacao() {
    login_usuario = sessionStorage.login_usuario_meuapp;
    nome_usuario = sessionStorage.nome_usuario_meuapp;

    if (login_usuario == undefined) {
        loginDashboard.style.display = 'inline';
        cadastroDashboard.style.display = 'inline';
    } else {
        lista.innerHTML = '<li><a href="index.html">Início</a></li> <li><a href="dashboard.html">Dashboard</a></li> <li class="barra">|</li> <li><a href="#" id="b_usuario"></a></li> <li ><a href="#" onclick="logoff()">Sair</a></li>';
        b_usuario.innerHTML = nome_usuario;
        esconderStart.style.display = 'none';
        validar_sessao();
    }

}

function logoff() {
    finalizar_sessao();
    sessionStorage.clear();
    redirecionar_login();
}

function validar_sessao() {
    fetch(`/usuarios/sessao/${login_usuario}`, { cache: 'no-store' })
        .then(resposta => {
            if (resposta.ok) {
                resposta.text().then(texto => {
                    console.log('Sessão :) ', texto);
                });
            } else {
                console.error('Sessão :.( ');
                logoff();
            }
        });
}

function finalizar_sessao() {
    fetch(`/usuarios/sair/${login_usuario}`, { cache: 'no-store' });
}