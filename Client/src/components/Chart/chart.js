import React from 'react';
import { Line } from 'react-chartjs-2';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    BarElement,
    Title,
    Tooltip,
    Legend
} from 'chart.js';
import chartTrendline from 'chartjs-plugin-trendline';

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    BarElement,
    Title,
    Tooltip,
    Legend,
    chartTrendline
);

const Chart = ({ chartData, cutYear }) => {
    if (chartData.data == null) {
        return <div>Loading chart...</div>
    }
    // Filtrujemy wartości null
    const filteredData = chartData.data.filter((item) => item.column !== null && item.year >= cutYear - 3);
    const years = filteredData.map((item) => item.year);
    const columnData = filteredData.map((item) => item.column);
    const labelName = chartData.label

    var ymax = Math.floor(Math.max(...columnData) * 1.2);
    if (Math.max(...columnData) < 1) {
        ymax = Math.max(...columnData) * 1.2;
    }

    var hasYear2004 = years.includes(2004);
    var hasYear2016 = years.includes(2016);
    var hasYear2019 = years.includes(2019);
    // var hasYear2019 = Math.max(...years) > 2019

    const filteredChartData = {
        labels: years,
        datasets: [
            {
                type: 'line',
                label: labelName,
                data: columnData,
                borderColor: 'rgb(255, 99, 132)',
                backgroundColor: 'rgba(255, 99, 132, 0.1)',
                trendlineLinear: {
                    colorMin: "red",
                    colorMax: "blue",
                    lineStyle: "dotted",
                    width: 2,
                }
            },
            {
                type: 'bar',
                label: 'Wstąpienie Polski do Unii Europejskiej',
                data: (cutYear === 2016 || cutYear === 2019) ? null : (hasYear2004 ? [{ x: 2004, y: ymax }] : null),
                borderColor: 'rgb(29, 62, 98)',
                backgroundColor: 'rgba(29, 62, 98, 0.5)',
                maxBarThickness: 3,
            },
            {
                type: 'bar',
                label: 'Wprowadzenie 500+',
                data: (cutYear === 2004 || cutYear === 2019) ? null : (hasYear2016 ? [{ x: 2016, y: ymax }] : null),
                borderColor: 'rgb(75, 192, 192)',
                backgroundColor: 'rgba(75, 192, 192, 0.5)',
                maxBarThickness: 3,
            },
            {
                type: 'bar',
                label: 'Reforma emerytalna',
                data: (cutYear === 2004 || cutYear === 2016) ? null : (hasYear2019 ? [{ x: 2019, y: ymax }] : null),
                borderColor: 'rgb(175, 12, 92)',
                backgroundColor: 'rgba(175, 12, 92, 0.5)',
                maxBarThickness: 3,
            }
        ]
    }

    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Wykres danych z Polski',
            },
            tooltip: {
                filter: function (tooltipItem) {
                    return tooltipItem.datasetIndex === 0
                }
            }
        },
        scales: {
            y: {
                max: ymax
            },
        },
    };

    return (
        <Line data={filteredChartData} options={options}></Line>
    );
}

export default Chart;
