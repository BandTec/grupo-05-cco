/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bandtec.telas;

import br.com.bandtec.Conexoes.ConexaoBancoAzure;
import br.com.bandtec.TesteJChart.LineChart;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class TelasParques extends JFrame {

    ConexaoBancoAzure conexao = new ConexaoBancoAzure();
    // TelasParques tela = new TelaDashboard();

    public TelasParques() {

        setLayout(new GridLayout(3, 2, 10, 10));

        setTitle("Parques");
        setSize(1200, 900);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<DadosParques> lista
                = conexao.jdbcTemplate.query(
                "select nome, idParque from parque", new BeanPropertyRowMapper(DadosParques.class));

//        for (int i = 0; i < lista.size(); i++) {
        for (DadosParques dados : lista) {

            JPanel painel = new JPanel();
            painel.setBackground(Color.decode("#303030"));
            painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel label = new JLabel("label1");
            label.setText(dados.getNome());
            label.setForeground(Color.decode("#f1f1f1"));

            JButton botao = new JButton("Visualizar Dados");
            botao.setSize(40, 40);
            botao.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // aqui vai a ação 
                        LineChart chart = new LineChart(dados.getNome(), "Temperatura CPU "+dados.getNome());
                        chart.setVisible(true);
                    }
                });

            System.out.println(dados.getNome());
            painel.add(label);
            painel.add(botao, BorderLayout.SOUTH);
            painel.setLayout(new GridLayout(4, 1));
            add(painel);
        }
//            break;
//        }
    }

    public static void main(String[] args) {
        new TelasParques();
    }
}
