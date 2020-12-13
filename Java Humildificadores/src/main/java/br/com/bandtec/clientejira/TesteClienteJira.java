package br.com.bandtec.clientejira;

import br.com.bandtec.Conexoes.ConexaoBanco;
import br.com.bandtec.Conexoes.Monitoramento;
import br.com.bandtec.telas.DadosParques;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class TesteClienteJira {



    public static void main(String[] args) {
        ConexaoBanco conexaoAWS = new ConexaoBanco();
        List<DadosParques> consultaParques = conexaoAWS.jdbcTemplate.query("select nome, idParque from parque",
                new BeanPropertyRowMapper(DadosParques.class));
        
        Integer lastId = 0;
        while (true) {
            for (DadosParques consultaParque : consultaParques) {
                List<Monitoramento> consultaMonitoramento = conexaoAWS.jdbcTemplate.query("select idMetrica, valor, momento, nome, limiteAlerta, usuario, fkParque \n"
                        + "from leituras lei, componentes com, configuracao con, maquinas \n"
                        + "where lei.fkConfiguracao = ? and lei.fkComponente = idComponente"
                        + " and idConfiguracao = lei.fkConfiguracao and idMaquina = fkMaquina"
                        + " order by idMetrica asc;", new BeanPropertyRowMapper(Monitoramento.class), consultaParque.getIdParque());
                for (Monitoramento monitoramento : consultaMonitoramento) {
                    System.out.println(lastId);
                    if(lastId < monitoramento.getIdMetrica()){
                        lastId = monitoramento.getIdMetrica();
                    }
                }
                
            }
        }
    }

}
