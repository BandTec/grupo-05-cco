package br.com.bandtec.Conexoes;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoBanco {

    public static JdbcTemplate jdbcTemplate;
    public static BasicDataSource dataSource;

    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://54.236.25.105/humildificadores?useTimezone=true&serverTimezone=UTC";
    public static String USERNAME = "root";
    public static String PASSWORD = "urubu100";

    public ConexaoBanco() {
        Conectar();
    };
    
    public ConexaoBanco(String driver, String url, String username, String password) {
        this.DRIVER = driver;
        this.URL = url;
        this.USERNAME = username;
        this.PASSWORD = password;
    }
    
    

    public void Conectar(){
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
