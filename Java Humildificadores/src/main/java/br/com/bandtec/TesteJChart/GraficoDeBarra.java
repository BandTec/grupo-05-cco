    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bandtec.TesteJChart;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraficoDeBarra {
    // criar o dataset
    
    public CategoryDataset createDataset(ArrayList<Pessoa> listaDePessoas) {
    
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        
        for (Pessoa pessoa : listaDePessoas) {
            dataSet.addValue(pessoa.getCpu_media_temperatura(), pessoa.getNome(), "");
        }
        return dataSet;
        
    }
    
    // criar o grafico de barras
    public JFreeChart createBarChart(CategoryDataset dataSet) {
        
        JFreeChart graficoBarras = ChartFactory.createLineChart(
        "Temperatura", 
        "",
        "°C",
        dataSet,
        PlotOrientation.VERTICAL,
        true,
        false,
        false);
        
        return graficoBarras;
        
    }
    // criar o grafico completo
    public ChartPanel criarGrafico(ArrayList<Pessoa> listaDePessoas) {
    
        CategoryDataset dataSet = this.createDataset(listaDePessoas);
        
        JFreeChart grafico = this.createBarChart(dataSet);
        
        ChartPanel painelDoGrafico = new ChartPanel(grafico);
        painelDoGrafico.setPreferredSize(new Dimension(400, 400));
        
        return painelDoGrafico;
        
    }
}
