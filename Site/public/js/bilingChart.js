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
var valores = [];
console.log("valores: ",valores)
let dataAtual = new Date;
let mesAtual = dataAtual.getMonth();
var miniBarMonths = [(mesAtual - 1), mesAtual, (mesAtual + 1)];
var jurosMonths = [];

var jurosBarChartData = {
    labels: jurosMonths,
    datasets: [{
        label: 'Simulação de juros',
        backgroundColor: [
            'lightblue'
        ],
        borderColor: 'transparent',
        hoverBorderColor: 'black',
        borderWidth: 3,
        data: valores
    }]
};

var forecastBarChartData = {
    labels: [],
    datasets: [{
        label: 'Forecast',
        backgroundColor: [
            'lightgreen',
        ],
        borderColor: 'transparent',
        hoverBorderColor: 'black',
        borderWidth: 3,
        data: valores
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
    labels: months,
    datasets: [{
        label: 'Linha Temporal',
        backgroundColor: 'red',
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
                label:'Testando valores',
                display: true,
                labels: {
                    fontColor: 'rgb(255, 99, 132)'
                }
            },
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