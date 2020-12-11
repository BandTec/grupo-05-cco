


let login_admin;
let nome_admin;

function redirecionar_loginAdmin() {
    window.location.href = 'login-admin.html';
}

function verificar_autenticacaoAdmin() {
    login_admin = sessionStorage.login_admin_meuapp;
    nome_admin = sessionStorage.nome_admin_meuapp;

    if (login_admin == undefined) {
        redirecionar_loginAdmin()
    } else {
        if (login_admin != 'augusto@humildificadores.com.br') {
            biling_nav.style.display = 'none';
        }
        b_usuario.innerHTML = nome_admin;
        validar_sessaoAdmin();
    }

}

function logoffAdmin() {
    finalizar_sessaoAdmin();
    sessionStorage.clear();
    redirecionar_loginAdmin();
}

function validar_sessaoAdmin() {
    fetch(`/admin/sessao/${login_admin}`, { cache: 'no-store' })
        .then(resposta => {
            if (resposta.ok) {
                resposta.text().then(texto => {
                    console.log('Sessão :) ', texto);
                });
            } else {
                console.error('Sessão :.( ');
                logoffAdmin();
            }
        });
}

function finalizar_sessaoAdmin() {
    fetch(`/admin/sair/${login_admin}`, { cache: 'no-store' });
}

