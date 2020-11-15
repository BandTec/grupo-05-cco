function validarAcessoAdmin() {
    var emailAcesso = usuarioAdmin.value;
    var senhaAcesso = senhaAdmin.value;
    if (emailAcesso == "admin@admin.com" && senhaAcesso == "admin") {
        alert("Você vai ser redirecionado...");
        window.location = "dashboard.html";
    } else {
        alert("Usuário ou Senha Inválidos");
    }
}