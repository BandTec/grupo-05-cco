function alterar(botao) {
    if (botao == 1) {
        bForecast.style.display = 'block';
        forecast.style.display = 'none';

        bJuros.style.display = 'none';
        juros.style.display = 'block';
    } else {
        bJuros.style.display = 'block';
        juros.style.display = 'none';

        bForecast.style.display = 'none';
        forecast.style.display = 'block';
    }
}

var months = ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'];

var monthsTime = [];
var valores = [];


var jurosMonths = ["Outubro", "Novembro", "Dezembro"];

var jurosBarChartData = {
    labels: jurosMonths,
    datasets: [{
        label: 'Valor não a ser pago',
        backgroundColor: 'lightblue',
        borderColor: 'transparent',
        hoverBorderColor: 'black',
        borderWidth: 3,
        data: [
            3.21
        ]
    }]
};

var miniBarMonths = [];

var forecastBarChartData = {
    labels: miniBarMonths,
    datasets: [{
        label: 'Forecast',
        backgroundColor: [
            'lightgreen',
            'lightgreen',
            'green'
        ],
        borderColor: 'transparent',
        hoverBorderColor: 'black',
        borderWidth: 3,
        data: []
    }]
};

let dadosDoughnut = [];

var config = {
    datasets: [{
        data: dadosDoughnut,
        backgroundColor: [
            'red',
            'lightgreen',
            'aqua'
        ]
    }],
    labels: [
        'EBS',
        'S3',
        'EC2',
    ]
};

var barChartData = {
    labels: [],
    datasets: [{
        label: 'Valor Mensal ($)',
        backgroundColor: [
            'red',
            'red',
            'red',
            'red',
            'red',
            'red',
            'red',
            'red',
            'red',
            'red',
            'lightgreen',
            'lightgreen',
        ],
        borderColor: 'transparent',
        hoverBorderColor: 'black',
        borderWidth: 3,
        data: valores
    }]
};


function plotarGraficos() {
    var ctx = document.getElementById('timeseries').getContext('2d');
    window.myBar = new Chart(ctx, {
        type: 'bar',
        data: barChartData,
        options: {
            legend: {
                label: 'Valores',
                display: true,
                labels: {
                    xLabel: 'Dólar ($)',
                    fontColor: 'rgb(255, 99, 132)'
                }
            },
            responsive: true,
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Gastos dos últimos meses em AWS cloud'
            },
            scales: {
                yAxes: [{
                    scaleLabel: {
                        display: true,
                        labelString: 'Dólar ($)'
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }],
                xAxes: [{
                    scaleLabel: {
                        display: true,
                        labelString: 'Meses',
                    }
                }]
            }
        }
    });
    var ctx = document.getElementById('juros').getContext('2d');
    window.myBar = new Chart(ctx, {
        type: 'bar',
        data: jurosBarChartData,
        options: {
            responsive: true,
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Valor a ser pago com juros'
            },
            scales: {
                yAxes: [{
                    scaleLabel: {
                        display: true,
                        labelString: 'Dólar ($)',
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }],
                xAxes: [{
                    scaleLabel: {
                        display: true,
                        labelString: 'Meses',
                    }
                }]
            }
        }
    });
    var ctx = document.getElementById('forecast').getContext('2d');
    window.myBar = new Chart(ctx, {
        type: 'bar',
        data: forecastBarChartData,
        options: {
            responsive: true,
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Previsão de custo do mês seguinte'
            },
            scales: {
                yAxes: [{
                    scaleLabel: {
                        display: true,
                        labelString: 'Previsão de custo',
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }],
                xAxes: [{
                    scaleLabel: {
                        display: true,
                        labelString: 'Meses',
                    }
                }]
            }
        }
    });
    var doug = document.getElementById('services').getContext('2d');
    window.myDoughnut = new Chart(doug, {
        type: 'doughnut',
        data: config,
        options: {
            responsive: true,
            legend: {
                position: 'bottom',
            },
            title: {
                display: true,
                text: 'Distribuição do custo do mês atual em serviços'
            },
            animation: {
                animateScale: true,
                animateRotate: true
            }
        }
    });
};