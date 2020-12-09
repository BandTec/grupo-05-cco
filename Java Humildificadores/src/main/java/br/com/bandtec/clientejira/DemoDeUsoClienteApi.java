package br.com.bandtec.clientejira;

import br.com.bandtec.Conexoes.ConexaoBanco;
import br.com.bandtec.Conexoes.Monitoramento;
import br.com.bandtec.clientejira.modelo.Issue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class DemoDeUsoClienteApi {

    public static void main(String[] args) throws IOException {
        // Este "gson" é opcional. Apenas para imprimir os objetos na saída padrão, caso queira.

        System.out.println("NOVOS");
        ClienteJiraApi clienteJiraApi = new ClienteJiraApi(
                "humildifica.atlassian.net",
                "201grupo4c@bandtec.com.br",
                "ElJVjLEk4h8vJ1N7FfisD5F0"
        );

//        Issue issue = clienteJiraApi.getIssue("DT-5");
//        System.out.println("Issue recuperada: " + gson.toJson(issue));
        // Exemplo de objeto para novo chamado (Issue)
        Issue novaIssue = new Issue();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ConexaoBanco conexao = new ConexaoBanco();
        Integer lastId = 0;
        Double temperatura = 0.0;
        Integer contador = 0;
        while (true) {
            List<Monitoramento> consulta
                    = conexao.jdbcTemplate.query("select valor from leituras, componentes where fkComponente = idComponente and nome = 'cpu_media_temperatura'",
                            new BeanPropertyRowMapper(Monitoramento.class));
            for (Monitoramento consultinha : consulta) {
                if (lastId < consultinha.getIdMetrica()) {
                    lastId = consultinha.getIdMetrica();
                    System.out.println(
                            "Id: " + lastId + ", Media Temperatura: " + consultinha.getValor());
                    temperatura = Double.parseDouble(consultinha.getValor());
                    if (temperatura >= 76.0) {
                        if (contador == 0) {
                            novaIssue.setProjectKey("TES");
                            novaIssue.setSummary("Problema na temperatura da CPU!");
                            novaIssue.setDescription("Voc� precisar resfriar agora a sua CPU!");
                            novaIssue.setLabels("alerta-cpu", "alerta-disco");
                            clienteJiraApi.criarIssue(novaIssue);
                            System.out.println("Issue criada: " + gson.toJson(novaIssue));
                            contador++;
                        }
                    }
                }
            }
        }
    }
}
