package br.com.bandtec.telas;

import br.com.bandtec.TesteJChart.LineChart;
import java.awt.Color;
import java.awt.GridLayout;
import javax.sound.sampled.Line;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaDashboard extends JFrame{
 
    
    
    public TelaDashboard (String nome) {
        
        setLayout(new GridLayout(1,2, 5,5));
        setSize(400, 400);
        setTitle(nome);
        setLocationRelativeTo(null);
        
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        LineChart chart = new LineChart("Dashboard", "Grafico "+nome);
        
        JLabel label = new JLabel("label1");
        label.setText(nome);
        label.setForeground(Color.decode("#000"));
        
        
        
        painel.add(label);
        painel.add(chart);
        
        add(painel);
    }
    
}
