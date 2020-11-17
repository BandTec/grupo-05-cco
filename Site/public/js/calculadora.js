function calcular() {

    result.style.display = 'block'
    pessoas.innerHTML = imp_app.value;

    var ganhosDia = Number(cli_anu.value) * Number(tx_clique.value);
    dia.innerHTML = ganhosDia.toFixed(2);

    var ganhosMes = Number(cli_anu.value) * Number(tx_clique.value) * 30;
    mes.innerHTML = ganhosMes.toFixed(2);

    var ganhosAno = Number(cli_anu.value) * Number(tx_clique.value) * 366;
    ano.innerHTML = ganhosAno.toFixed(2);

}

function voltar() {
    document.location.reload(true);
}