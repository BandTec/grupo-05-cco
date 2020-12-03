package br.com.bandtec.Conexoes;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoBanco {

    public static JdbcTemplate jdbcTemplate;
    public static BasicDataSource dataSource;

    public final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public final static String URL = "jdbc:mysql://localhost/humildificadores?useTimezone=true&serverTimezone=UTC";
    public final static String USERNAME = "ProjetoAPI";
    public final static String PASSWORD = "urubu100";

    public ConexaoBanco() {
        Conectar();
    };

    public void Conectar(){
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
