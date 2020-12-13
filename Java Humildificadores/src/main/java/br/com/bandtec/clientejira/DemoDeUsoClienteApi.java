package br.com.bandtec.clientejira;

import br.com.bandtec.Conexoes.ConexaoBanco;
import br.com.bandtec.Conexoes.Monitoramento;
import br.com.bandtec.clientejira.modelo.Issue;
import br.com.bandtec.telas.DadosParques;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class DemoDeUsoClienteApi {

    ClienteJiraApi clienteJiraApi = new ClienteJiraApi(
            "humildifica.atlassian.net",
            "201grupo4c@bandtec.com.br",
            "ElJVjLEk4h8vJ1N7FfisD5F0",
            0
    );
    Issue novaIssue = new Issue();

    public void jira() throws IOException {

        ConexaoBanco conexao = new ConexaoBanco();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Integer lastId = 0;
        Double valorComponente = 0.0;

        Integer contador = 0;
        List<DadosParques> lista
                = conexao.jdbcTemplate.query(
                        "select nome, idParque from parque;",
                        new BeanPropertyRowMapper(DadosParques.class));
        while (true) {

            for (DadosParques dadosParques : lista) {
                List<Monitoramento> consulta = conexao.jdbcTemplate.query(
                        "select idMetrica, valor, momento, nome, limiteAlerta, usuario, fkParque from leituras l, componentes, maquinas, configuracao c\n"
                        + "where fkParque = ? and l.fkComponente = idComponente and c.fkComponente = idComponente order by idMetrica",
                        new BeanPropertyRowMapper(Monitoramento.class), dadosParques.getIdParque());

                for (Monitoramento consultinha : consulta) {
                    //if (consultinha.getNome().equals(consultinha.getNome())) {
                    for (Monitoramento componente : consulta) {
                        if (lastId < componente.getIdMetrica()) {
                            System.out.println("PARQUE DO KRL: " + dadosParques.getIdParque());
                            lastId = componente.getIdMetrica();
                            System.out.println(
                                    "Nome Componentes: " + componente.getNome() + " Id: " + lastId
                                    + ", " + componente.getValor() + "\nmaquina: " + componente.getFkParque());
                            valorComponente = Double.parseDouble(componente.getValor());
                            if (valorComponente >= consultinha.getLimiteAlerta()) {
                                if (contador == 0) {
                                    novaIssue.setProjectKey("TES");
                                    novaIssue.setSummary("Problema na maquina " + componente.getUsuario());
                                    novaIssue.setDescription("O seu componente " + consultinha.getNome() + " excedeu o limite registrado, confira e repare. ");
                                    novaIssue.setLabels("alerta-" + componente.getNome());
                                    clienteJiraApi.criarIssue(novaIssue);
                                    System.out.println("Issue criada: " + gson.toJson(novaIssue));
                                    contador++;
                                }
                            }
                        }
                    }
                    //}
                }

            }
        }
    }
}
