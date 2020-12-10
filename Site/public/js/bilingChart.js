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

function aleatorio() {
    var dados = parseInt(Math.random() * 101);
    if (dados < 20) {
        dados = 30;
    };
    return dados;
};

var MONTHS = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
var VALORES = [60, 68, 85, 45, 57, 95, 60, 90, 50, 80];
var miniBarMonths = [MONTHS[8], MONTHS[9], MONTHS[10]];
var jurosMonths = [MONTHS[9], MONTHS[10], MONTHS[11]]

var jurosBarChartData = {
    labels: jurosMonths,
    datasets: [{
        label: 'Simulação de juros',
        backgroundColor: [
            'lightblue',
            'lightblue',
            'blue'
        ],
        borderColor: 'transparent',
        hoverBorderColor: 'black',
        borderWidth: 3,
        data: [
            VALORES[8],
            VALORES[9],
            VALORES[10]
        ]
    }]
};

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
        data: [
            VALORES[9],
            VALORES[10],
            VALORES[11]
        ]
    }]
};

var config = {
    type: 'doughnut',
    data: {
        datasets: [{
            data: [
                62,
                21,
                17
            ],
            backgroundColor: [
                'red',
                'lightgreen',
                'aqua'
            ]
        }],
        labels: [
            'EBS' ,
            'S3',
            'EC2',
        ]
    },
    options: {
        responsive: true,
        legend: {
            position: 'top',
        },
        title: {
            display: true,
            text: 'Custo de Serviços de Cloud'
        },
        animation: {
            animateScale: true,
            animateRotate: true
        }
    }
};

var barChartData = {
    labels: MONTHS,
    datasets: [{
        label: 'Linha Temporal',
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
            'lightgreen' 
        ],
        borderColor: 'transparent',
        hoverBorderColor: 'black',
        borderWidth: 3,
        data: VALORES
    }]
};


function plotarGraficos() {
    var ctx = document.getElementById('timeseries').getContext('2d');
    window.myBar = new Chart(ctx, {
        type: 'bar',
        data: barChartData,
        options: {
            responsive: true,
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Time series'
            },
            scales: {
                yAxes: [{
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
                display: true,
                text: 'mini'
            },
            scales: {
                yAxes: [{
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
                display: true,
                text: 'mini'
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
    var doug = document.getElementById('services').getContext('2d');
    window.myDoughnut = new Chart(doug, config);
};