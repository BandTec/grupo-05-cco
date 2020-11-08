/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.jose.api_banco_local;

import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Aluno
 */
public class Teste {
    public static void main(String[] args) {
        
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        // exemplo para MySql: "com.mysql.cj.jdbc.Driver"
        dataSource.
                setUrl(
                        "jdbc:mysql://localhost:3306/projetoapi?useTimezone=true&serverTimezone=UTC");
        // exemplo para MySql: "jdbc:mysql://localhost:3306/meubanco"
        dataSource.setUsername("root");
        dataSource.setPassword("bandtec");
        
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        //System.out.println(jdbcTemplate.queryForList("select * from pytohms"));
        
        List<DadosBancoLocal> dadosBanco = jdbcTemplate.query("select * from pytohms",
                new BeanPropertyRowMapper(DadosBancoLocal.class));
        
        for (DadosBancoLocal dados : dadosBanco) {
            System.out.println("---------------------------------------------");
            System.out.println("ID:" + dados.getId());
            System.out.println("user_desktop:" + dados.getUser_desktop());
            System.out.println("placa_mae:" + dados.getPlaca_mae());
            System.out.println("cpu_count:" + dados.getCpu_count());
            System.out.println("clock_1:" + dados.getClock_1());
            System.out.println("clock_2:" + dados.getClock_2());
            System.out.println("cpu_media_temperatura:" + dados.getCpu_media_temperatura());
            System.out.println("cpu_media_percent:" + dados.getCpu_media_percent());
            System.out.println("cpu_media_clock:" + dados.getCpu_media_clock());
            System.out.println("memory_load:" + dados.getMemory_load());
            System.out.println("memory_use:" + dados.getMemory_load());
            System.out.println("memory_available:" + dados.getMemory_available());
            System.out.println("video_card:" + dados.getVideo_card());
            System.out.println("---------------------------------------------");
        }
        
    }
}
