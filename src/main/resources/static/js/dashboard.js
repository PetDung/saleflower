/* globals Chart:false, feather:false */

(function () {

    feather.replace({ 'aria-hidden': 'true' })

    // Graphs
    let ctx = document.getElementById('myChart')
    // eslint-disable-next-line no-unused-vars
    let myChart = new Chart(ctx, {

        type: 'line',
        data: {
            labels: [
                'sunday',
                '2',
                '3',
                '4',
                '5',
                '6',
                '7',
                '8',
                '9',
                '10',
                '11',
                '12',
            ],
            datasets: [{
                data: [
                    1,
                    2,
                    3,
                    4,
                    5,
                    6,
                    7,
                    8,
                    9,
                    10,
                    11,
                    12
                ],
                lineTension: 0,
                backgroundColor: 'transparent',
                borderColor: '#007bff',
                borderWidth: 4,
                pointBackgroundColor: '#007bff'
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: false
                    }
                }]
            },
            legend: {
                display: false
            }
        }
    })
})()
