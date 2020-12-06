package br.com.bandtec.Conexoes;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoBancoAzure {

    public JdbcTemplate jdbcTemplate;
    public BasicDataSource dataSource;

    public String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public String url = "jdbc:sqlserver://serverhumildificadores.database.windows.net:1433;"
                + "database=bancohumildificadores;encrypt=true;"
                + "trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;";
    public String username = "adminlocal";
    public String password = "Safadinhos123@";

    public ConexaoBancoAzure() {
        Conectar();
    };
    
    

    public void Conectar(){
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        jdbcTemplate = new JdbcTemplate(dataSource);
    }
}