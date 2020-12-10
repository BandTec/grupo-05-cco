package br.com.bandtec.clientejira;

import br.com.bandtec.Conexoes.ConexaoBanco;
import br.com.bandtec.Conexoes.Monitoramento;
import br.com.bandtec.clientejira.modelo.Issue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class DemoDeUsoClienteApi {

    public static void main(String[] args) throws IOException {
        // Este "gson" é opcional. Apenas para imprimir os objetos na saída padrão, caso queira.

        System.out.println("NOVOS");
        ClienteJiraApi clienteJiraApi = new ClienteJiraApi(
                "humildifica.atlassian.net",
                "201grupo4c@bandtec.com.br",
                "ElJVjLEk4h8vJ1N7FfisD5F0",
                0
        );

//        Issue issue = clienteJiraApi.getIssue("DT-5");
//        System.out.println("Issue recuperada: " + gson.toJson(issue));
        // Exemplo de objeto para novo chamado (Issue)
        Issue novaIssue = new Issue();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ConexaoBanco conexao = new ConexaoBanco();
        Integer lastId = 0;
        Double valorComponente = 0.0;
        Integer contador = 0;
        while (true) {
            
            List<Monitoramento> consulta
            = conexao.jdbcTemplate.query("select limiteAlerta,"
                    + " maquinas.usuario, componentes.nome from parque, maquinas, configuracao, "
                    + "componentes where idParque = fkParque and idParque = ? "
                    + "and idMaquina = fkMaquina and idComponente = fkComponente;",
                    
                new BeanPropertyRowMapper(Monitoramento.class));
            for (Monitoramento consultinha : consulta) {
                if (lastId < consultinha.getIdMetrica()) {
                    lastId = consultinha.getIdMetrica();
                    System.out.println(
                            "Id: " + lastId + ", Media Temperatura: " + consultinha.getValor());
                    valorComponente = Double.parseDouble(consultinha.getValor());
                    if (valorComponente >= consultinha.getLimiteAlerta()) {
                        if (contador == 0) {
                            novaIssue.setProjectKey("TES");
                            novaIssue.setSummary("Problema na maquina "+consultinha.getUsuario());
                            novaIssue.setDescription("O seu componente excedeu o limite registrado, confira e repare. "+consultinha.getNome());
                            novaIssue.setLabels("alerta-"+consultinha.getNome());
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
