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
    if (!chartData.data) {
        return <div>Loading chart...</div>
    }

    // Filtrujemy wartości null
    const filteredData = chartData.data.filter((item) => item.column !== null && item.year >= cutYear - 3);
    
    const years = filteredData.map((item) => item.year);
    const columnData = filteredData.map((item) => item.column);
    const labelName = chartData.label

    let ymin = Math.min(...columnData) - (Math.max(...columnData) - Math.min(...columnData)) * 0.1;
    let ymax = Math.max(...columnData) * 1.2;

    const hasYear2004 = years.includes(2004);
    const hasYear2016 = years.includes(2016);
    const hasYear2019 = years.includes(2019);

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
                },
                xAxisID: 'x1',
            },
            {
                type: 'bar',
                label: 'Wstąpienie Polski do Unii Europejskiej',
                data: (cutYear === 2016 || cutYear === 2019) ? null : (hasYear2004 ? [{ x: 2004, y: 1 }] : null),
                borderColor: 'rgb(29, 62, 98)',
                backgroundColor: 'rgba(29, 62, 98, 0.5)',
                maxBarThickness: 3,
                xAxisID: 'x2',
                yAxisID: 'y2'
            },
            {
                type: 'bar',
                label: 'Wprowadzenie 500+',
                data: (cutYear === 2004 || cutYear === 2019) ? null : (hasYear2016 ? [{ x: 2016, y: 1 }] : null),
                borderColor: 'rgb(75, 192, 192)',
                backgroundColor: 'rgba(75, 192, 192, 0.5)',
                maxBarThickness: 3,
                xAxisID: 'x3',
                yAxisID: 'y2'
            },
            {
                type: 'bar',
                label: 'Reforma emerytalna',
                data: (cutYear === 2004 || cutYear === 2016) ? null : (hasYear2019 ? [{ x: 2019, y: 1 }] : null),
                borderColor: 'rgb(175, 12, 92)',
                backgroundColor: 'rgba(175, 12, 92, 0.5)',
                maxBarThickness: 3,
                xAxisID: 'x4',
                yAxisID: 'y2'
            }
        ]
    };

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
                    return tooltipItem.datasetIndex === 0;
                }
            }
        },
        scales: {
            x: {
                ticks: { stepSize: 1 },
                offset: true
            },
            x1: {
                display: false,
                offset: true
            },
            x2: {
                display: false,
            },
            x3: {
                display: false,
            },
            x4: {
                display: false,
            },
            y: { suggestedMin: ymin, suggestedMax: ymax },
            y2: { display: false }
        }
    };

    return <Line data={filteredChartData} options={options}></Line>;
}

export default Chart;
