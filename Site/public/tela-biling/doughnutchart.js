var config = {
    type: 'doughnut',
    data: {
        datasets: [{
            data: [
                0.62,
                0.21,
                0.17
            ],
            backgroundColor: [
                'red',
                'lightgreen',
                'aqua'
            ],
            label: 'Serviços de Cloud'
        }],
        labels: [
            `EBS: data[0]`,
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
            text: 'Custo de Serviços'
        },
        animation: {
            animateScale: true,
            animateRotate: true
        }
    }
};

window.onload = function() {
    var doug = document.getElementById('services').getContext('2d');
    window.myDoughnut = new Chart(doug, config);
};

