/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bandtec.telas;

import br.com.bandtec.Conexoes.ConexaoBanco;
import br.com.bandtec.Conexoes.ConexaoBancoAzure;
import br.com.bandtec.Conexoes.Monitoramento;
import br.com.bandtec.TesteJChart.LineChart;
import br.com.bandtec.clientejira.ClienteJiraApi;
import br.com.bandtec.clientejira.modelo.Issue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import jdk.internal.net.http.common.Demand;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class TelasParques extends JFrame {

    ConexaoBancoAzure conexao = new ConexaoBancoAzure();
    // TelasParques tela = new TelaDashboard();

    public TelasParques() {        

        setLayout(new GridLayout(3, 2, 10, 10));

        setTitle("Parques");
        setSize(1200, 700);
        setLocationRelativeTo(null);

        List<DadosParques> lista
                = conexao.jdbcTemplate.query(
                        "select nome, idParque from parque;",
                        new BeanPropertyRowMapper(DadosParques.class));
        
        ConexaoBanco conexao = new ConexaoBanco();
        
        for (DadosParques dados : lista) {
            
            JPanel painel = new JPanel();
            painel.setLayout(new FlowLayout());
            painel.setBackground(Color.decode("#303030"));
            painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel label = new JLabel("label1");
            Font myFont = new Font("Sans Serif", Font.BOLD, 25);
            label.setFont(myFont);
            label.setText(dados.getNome());
            label.setForeground(Color.decode("#f1f1f1"));
            
            JLabel labelzinha = new JLabel("");
            labelzinha.setText("");

            JButton botao = new JButton("Visualizar Dados");
            botao.setSize(40, 40);
            
            
            botao.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    LineChart chart = new LineChart("", dados.getNome(), dados.getIdParque());
                    chart.setVisible(true);
                    chart.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    chart.setDefaultCloseOperation(NORMAL);
                    chart.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            });

            System.out.println(dados.getNome());
            painel.add(label);
            painel.add(labelzinha);
            painel.add(botao, BorderLayout.SOUTH);
            painel.setLayout(new GridLayout(3, 1));
            add(painel);
            
        }
    }
    
    public static void main(String[] args) throws IOException {
        new TelasParques();
        
    }
    
}
