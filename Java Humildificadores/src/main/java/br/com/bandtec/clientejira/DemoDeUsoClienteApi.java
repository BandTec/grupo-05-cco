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
        
        while (true) {
            
            List<Monitoramento> consulta
            = conexao.jdbcTemplate.query(" select idMetrica, valor, momento, nome, limiteAlerta, usuario, fkParque from leituras l, componentes, maquinas, configuracao c\n" +
"where fkParque = 1 and l.fkComponente = idComponente and c.fkComponente = idComponente order by idMetrica",
                    
                new BeanPropertyRowMapper(Monitoramento.class));
            Integer contador = 0;
            for (Monitoramento consultinha : consulta) {
                if (consultinha.getNome().equals(consultinha.getNome())) {
                    for (Monitoramento componente : consulta) {
                        if (lastId < componente.getIdMetrica()) {
                            lastId = componente.getIdMetrica();
                            System.out.println(
                                    "Nome Componentes: "+componente.getNome()+" Id: " + lastId + ", " + componente.getValor());
                            valorComponente = Double.parseDouble(componente.getValor());
                            if (valorComponente >= consultinha.getLimiteAlerta()) {
                                if (contador == 0) {
                                    novaIssue.setProjectKey("TES");
                                    novaIssue.setSummary("Problema na maquina " + componente.getUsuario());
                                    novaIssue.setDescription("O seu componente "+consultinha.getNome()+" excedeu o limite registrado, confira e repare. " );
                                    novaIssue.setLabels("alerta-" + componente.getNome());
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
    }
}
