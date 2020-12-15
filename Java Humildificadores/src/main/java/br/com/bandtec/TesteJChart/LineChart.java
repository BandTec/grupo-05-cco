package br.com.bandtec.TesteJChart;

import br.com.bandtec.Conexoes.Componentes;
import br.com.bandtec.Conexoes.ConexaoBanco;
import br.com.bandtec.Conexoes.Monitoramento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class LineChart extends ApplicationFrame {

    ConexaoBanco conexao = new ConexaoBanco();
    String comboSelecionado;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    JComboBox combo = new JComboBox();

    public LineChart(String applicationTitle, String chartTitle, Integer fkParque) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                "",
                "Id", "Leituras",
                createDataset(fkParque),
                PlotOrientation.VERTICAL,
                true, true, false);
        setTitle(chartTitle);
        List<Componentes> listaComponentes = conexao.jdbcTemplate.query("select fkMaquina, componentes.nome from parque, maquinas, configuracao, componentes where"
                + " idParque = fkParque and idParque = ? and idMaquina = fkMaquina and idComponente = fkComponente;", new BeanPropertyRowMapper(Componentes.class), fkParque);
        for (Componentes lista : listaComponentes) {
            combo.addItem(lista.getNome());
            System.out.println("ID: " + lista.getFkMaquina() + " - Nome: " + lista.getNome());
        }

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 530));
        setSize(1020, 600);
        JPanel painel = new JPanel();
        painel.setSize(getWidth(), getWidth());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setDefaultCloseOperation(NORMAL);
        setLocationRelativeTo(null);
        add(painel);
        JButton refresh = new JButton("RECARREGAR");
        painel.add(refresh);
        painel.add(combo);
        painel.add(chartPanel);

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                plotarGrafico(fkParque);
            }
        });
        // Plotar graficos de acordo com a combo

        combo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                plotarGrafico(fkParque);

            }
        }
        );
    }

    ArrayList<Integer> listaId = new ArrayList<>();
    ArrayList<Double> listaValores = new ArrayList<>();

    public void plotarGrafico(Integer fkParque) {
        comboSelecionado = combo.getSelectedItem().toString();
        List<Monitoramento> consultaTemperaturas = conexao.jdbcTemplate.query("SELECT idMetrica, valor, momento, fkParque from maquinas maq\n"
                + "LEFT OUTER JOIN configuracao con ON maq.idMaquina = con.fkMaquina \n"
                + "LEFT OUTER JOIN leituras lei ON con.idConfiguracao = lei.fkConfiguracao\n"
                + "LEFT OUTER JOIN componentes com ON lei.fkComponente = com.idComponente \n"
                + "where fkParque = ? and nome = ?\n"
                + "order by idMetrica desc limit 20",
                new BeanPropertyRowMapper(Monitoramento.class), fkParque, comboSelecionado);
        dataset.clear();
        listaId.clear();

        for (Monitoramento componente : consultaTemperaturas) {

            listaId.add(componente.getIdMetrica());
            listaValores.add(Double.parseDouble(componente.getValor()));

        }
        Collections.reverse(listaId);
        Collections.reverse(listaValores);
        System.out.println(listaId);
        for (int i = 0; i < listaId.size(); i++) {
            dataset.addValue(listaValores.get(i), "Leituras", listaId.get(i));
        }
    }

    private DefaultCategoryDataset createDataset(Integer id) {
        Integer contador = 0;
        while (true) {
            ArrayList<Integer> listaId = new ArrayList<>();
            ArrayList<Double> listaValores = new ArrayList<>();
            comboSelecionado = "cpu_media_temperatura";
            List<Monitoramento> consultaTemperaturas = conexao.jdbcTemplate.query(
                    "SELECT idMetrica, valor, momento, fkParque from maquinas maq\n"
                + "LEFT OUTER JOIN configuracao con ON maq.idMaquina = con.fkMaquina \n"
                + "LEFT OUTER JOIN leituras lei ON con.idConfiguracao = lei.fkConfiguracao\n"
                + "LEFT OUTER JOIN componentes com ON lei.fkComponente = com.idComponente \n"
                + "where fkParque = ? and nome = ?\n"
                + "order by idMetrica desc limit 20;",
                    new BeanPropertyRowMapper(Monitoramento.class), id, comboSelecionado);
            dataset.clear();
            listaId.clear();
            for (Monitoramento componente : consultaTemperaturas) {
                listaId.add(componente.getIdMetrica());
                listaValores.add(Double.parseDouble(componente.getValor()));
            }
            Collections.reverse(listaId);
            Collections.reverse(listaValores);
            for (int i = 0; i < listaId.size(); i++) {
                dataset.addValue(listaValores.get(i), "Leituras", listaId.get(i));
            }
            if (contador == contador) {
                contador++;
                return dataset;
            }
        }
    }

    public static void main(String[] args) {
        LineChart chart = new LineChart(
                "Monitoramento",
                "ala",
                0);

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
