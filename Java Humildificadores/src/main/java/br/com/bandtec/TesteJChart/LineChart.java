/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bandtec.TesteJChart;

import br.com.bandtec.Conexoes.Componentes;
import br.com.bandtec.Conexoes.Monitoramento;
import br.com.bandtec.Conexoes.ConexaoBanco;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author Aluno
 */
public class LineChart extends JFrame {

    ConexaoBanco conexao = new ConexaoBanco();
    JComboBox combo = new JComboBox();
    String selectCombo;

    public LineChart(String applicationTitle, String chartTitle, Integer fkParque) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Valor", "Componente",
                createDataset(fkParque),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);

        JPanel painel = new JPanel();
        painel.setSize(560, 30);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 300));
//        setContentPane(chartPanel);
        setSize(580, 375);
        setLocationRelativeTo(null);
        List<Componentes> listaComponentes = conexao.jdbcTemplate.query("select fkMaquina, componentes.nome from parque, maquinas, configuracao, componentes where"
                + " idParque = fkParque and idParque = ? and idMaquina = fkMaquina and idComponente = fkComponente;", new BeanPropertyRowMapper(Componentes.class), fkParque);
        for (Componentes lista : listaComponentes) {
            combo.addItem(lista.getNome());
            System.out.println("ID: " + lista.getFkMaquina() + " - Nome: " + lista.getNome());
        }
        combo.setSize(100, 30);
        add(painel);
        painel.add(combo);
        painel.add(chartPanel);
    }

    private DefaultCategoryDataset createDataset(Integer id) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        combo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // aqui vai a ação 
                selectCombo = combo.getSelectedItem().toString();
                System.out.println(selectCombo);

                List<Monitoramento> consultaTemperaturas = conexao.jdbcTemplate.query("select idMetrica, valor, momento, fkParque from leituras,\n"
                        + "componentes, maquinas where fkParque = ? and fkComponente = idComponente and nome = ? order by idMetrica asc limit 20;",
                        new BeanPropertyRowMapper(Monitoramento.class), id, selectCombo);

                Double valor;
                Integer lastId = 0;
                for (Monitoramento listaTemp : consultaTemperaturas) {
                    for (int i = 0; i < consultaTemperaturas.size(); i++) {
                        if (lastId < listaTemp.getIdMetrica()) {
                            lastId = listaTemp.getIdMetrica();
                            System.out.println(
                                    "Id: " + lastId + ", Media Temperatura: " + listaTemp.getValor());
                            valor = Double.parseDouble(listaTemp.getValor());
                            dataset.addValue(valor,
                                    "",
                                    listaTemp.getMomento());
                        }
                    }
                }
            }
        });
        return dataset;
    }

    public static void main(String[] args) {
        LineChart chart = new LineChart(
                "Grafico Monitoramento",
                "Componente",
                0);

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
