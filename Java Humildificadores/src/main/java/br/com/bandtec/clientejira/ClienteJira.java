package br.com.bandtec.clientejira;
import br.com.bandtec.Conexoes.ConexaoBanco;
import br.com.bandtec.Conexoes.Monitoramento;
import br.com.bandtec.clientejira.modelo.Issue;
import br.com.bandtec.telas.DadosParques;
import br.com.bandtec.telegram.BotTelegram;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class ClienteJira {

    Timer timer;
    ConexaoBanco conexaoAWS = new ConexaoBanco();
        List<DadosParques> consultaParques = conexaoAWS.jdbcTemplate.query("select nome, idParque from parque",
                new BeanPropertyRowMapper(DadosParques.class));

        ClienteJiraApi clienteJiraApi = new ClienteJiraApi(
                "humildifica.atlassian.net",
                "201grupo4c@bandtec.com.br",
                "ElJVjLEk4h8vJ1N7FfisD5F0",
                0);
        BotTelegram bot = new BotTelegram();
        Issue novaIssue = new Issue();
        Integer lastId = 0;
        Double valorComponente = 0.0;
        Integer IdUltimo; 
        
        

    public ClienteJira(int seconds) {
        
        List<Monitoramento> ultimoId = conexaoAWS.jdbcTemplate.query(
                "SELECT idMetrica from leituras l  order by idMetrica desc limit 1;", 
                new BeanPropertyRowMapper(Monitoramento.class));
        
        ArrayList<Integer> ultimoid = new ArrayList<>();
        for (Monitoramento monitoramento : ultimoId) {
            ultimoid.add(monitoramento.getIdMetrica());
        }
        IdUltimo = ultimoid.get(0);
        System.out.println(ultimoid.get(0));
        
        
        timer = new Timer();
        timer.schedule(new RemindTask(),
                       0,        //initial delay
                       1*1000);  //subsequent rate
    }

    class RemindTask extends TimerTask {
        int numWarningBeeps = 3;
        public void run() {
            if (numWarningBeeps > 0) {
                for (DadosParques consultaParque : consultaParques) {
                List<Monitoramento> consultaMonitoramento = conexaoAWS.jdbcTemplate.query(
                        "SELECT lei.idMetrica, lei.valor, com.nome as nome, par.nome as parque, par.idParque ,maq.usuario, lei.momento, con.limiteAlerta \n"
                        + "FROM parque par\n"
                        + "left outer join maquinas maq ON par.idParque = maq.fkParque \n"
                        + "left outer join configuracao con ON maq.idMaquina = con.fkMaquina \n"
                        + "left outer join leituras lei ON con.idConfiguracao = lei.fkConfiguracao\n"
                        + "left outer join componentes com ON lei.fkComponente = com.idComponente where lei.idMetrica > ? order by lei.idMetrica ASC ",
                        new BeanPropertyRowMapper(Monitoramento.class), IdUltimo);
                for (Monitoramento monitoramento : consultaMonitoramento) {
                    Double valorAlerta = monitoramento.getLimiteAlerta();
                    if (lastId < monitoramento.getIdMetrica()) {
                        lastId = monitoramento.getIdMetrica();
                        
                        System.out.println(lastId);
                        System.out.println("Maquina: " + monitoramento.getUsuario());
                        System.out.println("COMPONENTE :" + monitoramento.getNome());
                        valorComponente = Double.parseDouble(monitoramento.getValor());
                        System.out.println("LEITURA: "+monitoramento.getNome()+":"+valorComponente);
                        System.out.println("ALERTA: "+monitoramento.getLimiteAlerta());
                        
                        System.out.println("\n");
                        
                        if (valorAlerta == null) {
                            monitoramento.setLimiteAlerta(10000.0);
                        }
                        if (valorComponente >= monitoramento.getLimiteAlerta()) {
                            System.out.println("ALERTA: "+monitoramento.getLimiteAlerta());
                            novaIssue.setProjectKey("TES");
                            novaIssue.setSummary("Problema na maquina " + monitoramento.getUsuario());
                            novaIssue.setDescription("O seu componente " + monitoramento.getNome() + " excedeu o limite registrado, confira e repare. ");
                            novaIssue.setLabels("alerta-" + monitoramento.getNome());
                            try {
                                clienteJiraApi.criarIssue(novaIssue);
                            } catch (IOException ex) {
                                Logger.getLogger(ClienteJira.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            conexaoAWS.jdbcTemplate.execute(String.format(
                                    "insert into chamados values (null, '%s', '%s')", monitoramento.getUsuario(), monitoramento.getNome()));
                            
                            List<Monitoramento> chamados = conexaoAWS.jdbcTemplate.query(
                                    "select idChamado from chamados order by idChamado desc limit 1;", 
                                    new BeanPropertyRowMapper(Monitoramento.class));
                            ArrayList<Integer> chamado = new ArrayList<>();
                            for (Monitoramento listachamados : chamados) {
                                chamado.add(listachamados.getIdChamado());
                            }
                            
                            Integer chamadinho = chamado.get(0);
                            
                            bot.chamadoTelegram(monitoramento.getNome(), monitoramento.getUsuario(), monitoramento.getParque(), chamadinho);
                        }
                    }
                }

            }
                
            } else {
                System.out.println("Time's up!");
                //timer.cancel(); // Not necessary because
                                  // we call System.exit
                System.exit(0);   // Stops the AWT thread 
                                  // (and everything else)
            }
        }
    }

    
}
