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
import br.com.bandtec.clientejira.DemoDeUsoClienteApi;
import br.com.bandtec.clientejira.modelo.Issue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import jdk.internal.net.http.common.Demand;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class TelasParques extends JFrame {

    ConexaoBancoAzure conexao = new ConexaoBancoAzure();
     public static void main(String[] args) throws IOException {
        new TelasParques();
        
    }
    // TelasParques tela = new TelaDashboard();

    public TelasParques() {        

        setLayout(new GridLayout(3, 2, 10, 10));

        setTitle("Parques");
        setSize(1200, 700);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<DadosParques> lista
                = conexao.jdbcTemplate.query(
                        "select nome, idParque from parque;",
                        new BeanPropertyRowMapper(DadosParques.class));
        
        ConexaoBanco conexao = new ConexaoBanco();
        DemoDeUsoClienteApi demoJira = new DemoDeUsoClienteApi();
        
//        for (int i = 0; i < lista.size(); i++) {
        for (DadosParques dados : lista) {
            
            JPanel painel = new JPanel();
            painel.setLayout(new FlowLayout());
            painel.setBackground(Color.decode("#303030"));
            painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel label = new JLabel("label1");
            label.setText(dados.getNome());
            label.setForeground(Color.decode("#f1f1f1"));

            JLabel label1 = new JLabel();
            label1.setText("Chamados abertos: ");
            label1.setForeground(Color.decode("#f1f1f1"));

            JButton botao = new JButton("Visualizar Dados");
            botao.setSize(40, 40);
            
            
            botao.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    LineChart chart = new LineChart("", dados.getNome(), dados.getIdParque());
                    chart.setVisible(true);
                }
            });

            System.out.println(dados.getNome());
            painel.add(label);
            painel.add(label1);
            painel.add(botao, BorderLayout.SOUTH);
            painel.setLayout(new GridLayout(3, 1));
            add(painel);
        }
        
        try {
            demoJira.jira();
            // Chamado
        } catch (IOException ex) {
            Logger.getLogger(TelasParques.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
    }
}
