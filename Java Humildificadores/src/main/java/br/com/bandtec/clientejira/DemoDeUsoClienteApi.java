package br.com.bandtec.clientejira;

import br.com.bandtec.Conexoes.ConexaoBanco;
import br.com.bandtec.Conexoes.Monitoramento;
import br.com.bandtec.clientejira.modelo.Issue;
import br.com.bandtec.telas.DadosParques;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class DemoDeUsoClienteApi extends JFrame {

    public DemoDeUsoClienteApi() {

        Issue novaIssue = new Issue();
        ClienteJiraApi clienteJiraApi = new ClienteJiraApi(
                "humildifica.atlassian.net",
                "201grupo4c@bandtec.com.br",
                "ElJVjLEk4h8vJ1N7FfisD5F0",
                0);
        
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
                        "select idMetrica, valor, momento, nome, limiteAlerta, usuario, fkParque"
                        + " from leituras lei, componentes com, configuracao con,"
                        + " maquinas where lei.fkConfiguracao = ? and lei.fkComponente = idComponente"
                        + " and idConfiguracao = lei.fkConfiguracao and idMaquina = fkMaquina order by idMetrica asc;",
                        new BeanPropertyRowMapper(Monitoramento.class), dadosParques.getIdParque());

                for (Monitoramento consultinha : consulta) {
                    //if (consultinha.getNome().equals(consultinha.getNome())) {
                    for (Monitoramento componente : consulta) {
                        if (lastId < componente.getIdMetrica()) {
                            System.out.println("PARQUE DO KRL: " + dadosParques.getIdParque());
                            lastId = componente.getIdMetrica();
                            System.out.println(
                                    "Nome Componentes: " + componente.getNome() + " Id: " + lastId
                                    + ", " + componente.getValor() + "\nmaquina: " + dadosParques.getIdParque());
                            valorComponente = Double.parseDouble(componente.getValor());
                            if (valorComponente >= consultinha.getLimiteAlerta()) {
                                if (contador == 0) {
                                    novaIssue.setProjectKey("TES");
                                    novaIssue.setSummary("Problema na maquina " + componente.getUsuario());
                                    novaIssue.setDescription("O seu componente " + consultinha.getNome() + " excedeu o limite registrado, confira e repare. ");
                                    novaIssue.setLabels("alerta-" + componente.getNome());
                                    try {
                                        clienteJiraApi.criarIssue(novaIssue);
                                    } catch (IOException ex) {
                                        Logger.getLogger(DemoDeUsoClienteApi.class.getName()).log(Level.SEVERE, null, ex);
                                    }
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

    public static void main(String[] args) {
        new DemoDeUsoClienteApi();
    }

}
