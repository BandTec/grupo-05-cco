/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bandtec.TesteJChart;

import br.com.bandtec.Conexoes.Temperatura;
import br.com.bandtec.Conexoes.ConexaoBanco;
import java.util.List;
import javax.swing.JFrame;
import org.apache.commons.dbcp2.BasicDataSource;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Aluno
 */
public class LineChart extends JFrame {

    private JdbcTemplate jdbcTemplate;
    
    public LineChart(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Leituras", "Média Temperatura CPU",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset() {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        ConexaoBanco conexao = new ConexaoBanco();
        
        List<Temperatura> consultaTemperaturas = conexao.jdbcTemplate.query(
                "select * from pytohms order by id asc limit 20", new BeanPropertyRowMapper(Temperatura.class));
        
        for (Temperatura listaTemp : consultaTemperaturas) {
            for (int i = 0; i < consultaTemperaturas.size(); i++) {
                dataset.addValue(Double.parseDouble(listaTemp.getCpu_media_temperatura()),
                "Temperatura",
                listaTemp.getId());
                System.out.println(
                        "Id: "+listaTemp.getId()+", Media Temperatura: "+listaTemp.getCpu_media_temperatura());
//                dataset.addValue(listaTemp.getId(),
//                    "Temperatura",
//                    listaTemp.getId());
            }
        }
        return dataset;
        //System.out.println(listaTemperatura);
        
//        dataset.addValue(15, "schools", "1970");
//        dataset.addValue(30, "schools", "1980");
//        dataset.addValue(60, "schools", "1990");
//        dataset.addValue(120, "schools", "2000");
//        dataset.addValue(240, "schools", "2010");
//        dataset.addValue(300, "schools", "2014");;
    }

    public static void main(String[] args) {
        LineChart chart = new LineChart(
                "Grafico Media Temperatura",
                "Média de Temperatura");
        
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }       
}

