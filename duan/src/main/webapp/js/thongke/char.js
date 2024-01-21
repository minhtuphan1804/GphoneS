const data = [];

const url = '/thong-ke/hien-thi';

fetch(url)
    .then(response => response.json())
    .then(data => {
        data.forEach(item => {
            data.push({
                thang: item.getThang(),
                doanhThu: item.getDoanhThu()
            });
        });
    }).catch(error => console.error(error));

const ctx = document.getElementById('myChart');

new Chart(ctx, {
    type: 'bar',
    data: {
        labels: data.map(item => item.thang),
        datasets: [{
            label: '# Doanh Thu',
            data: data.map(item => item.doanhThu),
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});