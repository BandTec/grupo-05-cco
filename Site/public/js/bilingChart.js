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

var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

var monthsTime = [];
var valores = [];


var jurosMonths = [];

var jurosBarChartData = {
    labels: jurosMonths,
    datasets: [{
        label: 'Simulação de juros',
        backgroundColor: 'lightblue',
        borderColor: 'transparent',
        hoverBorderColor: 'black',
        borderWidth: 3,
        data: valores
    }]
};

var miniBarMonths = [];

var forecastBarChartData = {
    labels: miniBarMonths,
    datasets: [{
        label: 'Forecast',
        backgroundColor: 'lightgreen',
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
        label: 'Mês',
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
                label: 'Testando valores',
                display: true,
                labels: {
                    yLabel: 'Valores',
                    fontColor: 'rgb(255, 99, 132)'
                }
            },
            responsive: true,
            legend: {
                position: 'top',
            },
            title: {
                display: false,
                text: 'Time series'
            },
            scales: {
                yAxes: [{
                    scaleLabel: {
                        display: true,
                        labelString: 'Crédito utilizado'
                    },
                    ticks: {
                        beginAtZero: true
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
                display: false,
                text: 'mini'
            },
            scales: {
                yAxes: [{
                    scaleLabel: {
                        display: true,
                        labelString: 'Valor a ser pago',
                    },
                    ticks: {
                        beginAtZero: true
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
                display: false,
                text: 'mini'
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
                text: ''
            },
            animation: {
                animateScale: true,
                animateRotate: true
            }
        }
    });
};