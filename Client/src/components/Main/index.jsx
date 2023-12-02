import { useState } from "react"
import axios from "axios"
import styles from "./styles.module.css"
import React from 'react';
import Chart from '../Chart/chart.js';
import Switch from "react-switch";

const Main = () => {
    const [chartData, setChartData] = useState({})
    const [toggled1, setToggled1] = useState(false);
    const [toggled2, setToggled2] = useState(false);
    const [toggled3, setToggled3] = useState(false);
    const [cutYear, setCutYear] = useState(null);

    const handleGetData = async (apiUrl, label) => {
        try {
            const response = await axios.get(apiUrl);
            const data = response.data;

            setChartData({ data, label });
            // alert(JSON.stringify(data)); // do testu
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    const handleToggle2004 = () => {
        setToggled1(!toggled1);
        if (toggled1) { setCutYear(null) }
        else {
            setCutYear(2004)
            setToggled2(false)
            setToggled3(false)
        }
    };

    const handleToggle2016 = () => {
        setToggled2(!toggled2);
        if (toggled2) { setCutYear(null) }
        else {
            setCutYear(2016)
            setToggled1(false)
            setToggled3(false)
        }
    };

    const handleToggle2019 = () => {
        setToggled3(!toggled3);
        if (toggled3) { setCutYear(null) }
        else {
            setCutYear(2019)
            setToggled1(false)
            setToggled2(false)
        }
    };

    return (
        <div className={styles.main_container}>
            <nav className={styles.navbar}>
                <h1>MySite</h1>
            </nav>
            <div className={styles.content}>
                <div></div>
                <div className={styles.buttonDiv}>
                    <button className={styles.green_btn} onClick={() => handleGetData('http://localhost:8080/api/years/population', 'Populacja (całkowita)')}>
                        populacja
                    </button>
                    <button className={styles.green_btn} onClick={() => handleGetData('http://localhost:8080/api/years/gdp', 'GDP (zł)')}>
                        GDP
                    </button>
                    <button className={styles.green_btn} onClick={() => handleGetData('http://localhost:8080/api/years/crimes', 'Przestępstwa (całkowita)')}>
                        przestępstwa
                    </button>
                    <button className={styles.green_btn} onClick={() => handleGetData('http://localhost:8080/api/years/familyBenefits', 'Świadczenia rodzinne (całkowite w zł)')}>
                        świadczenia rodzinne
                    </button>
                    <button className={styles.green_btn} onClick={() => handleGetData('http://localhost:8080/api/years/hospitalPatients', 'Pacjenci szpitali (całkowite)')}>
                        pacjenci szpitali
                    </button>
                    <button className={styles.green_btn} onClick={() => handleGetData('http://localhost:8080/api/years/psychiatricPatients', 'Pacjenci psychiatryczni (całkowite)')}>
                        pacjenci psychiatryczni
                    </button>
                    <button className={styles.green_btn} onClick={() => handleGetData('http://localhost:8080/api/years/suicideAttempts', 'Próby samobójcze na 100k osób')}>
                        próby samobójcze
                    </button>
                </div>

                <div className={styles.chartAndSwitch}>
                    <div className={styles.chart_container}>
                        <Chart chartData={chartData} cutYear={cutYear} />
                    </div>
                    <div>
                        <div className={styles.switchDiv}>
                            <p>ogranicz do wstąpienia do Unii</p>
                            <Switch
                                onChange={handleToggle2004}
                                checked={toggled1}
                            />
                        </div>
                        <div className={styles.switchDiv}>
                            <p>ogranicz do 500+</p>
                            <Switch
                                onChange={handleToggle2016}
                                checked={toggled2}
                            />
                        </div>
                        <div className={styles.switchDiv}>
                            <p>ogranicz do emerytur</p>
                            <Switch
                                onChange={handleToggle2019}
                                checked={toggled3}
                            />
                        </div>
                    </div>
                </div>

                <div className={styles.buttonDiv}>
                    <button className={styles.green_btn} onClick={() => handleGetData('http://localhost:8080/api/years/averageRetirement', 'Średnia emerytura (zł)')}>
                        średnia emerytura
                    </button>
                    <button className={styles.green_btn} onClick={() => handleGetData('http://localhost:8080/api/years/badHouseholdSituation', 'Zła sytuacja w gospodarstwie domowym (%)')}>
                        zła sytuacja w gospodarstwie domowym
                    </button>
                    <button className={styles.green_btn} onClick={() => handleGetData('http://localhost:8080/api/years/inflation', 'Inflacja (%)')}>
                        inflacja
                    </button>
                    <button className={styles.green_btn} onClick={() => handleGetData('http://localhost:8080/api/years/lifeExpectancy', 'Oczekiwana długość życia w momencie urodzenia (lata)')}>
                        oczekiwana długość życia
                    </button>
                    <button className={styles.green_btn} onClick={() => handleGetData('http://localhost:8080/api/years/povertyHeadcount', 'Wskaźnik ubóstwa (%)')}>
                        wskaźnik ubóstwa
                    </button>
                    <button className={styles.green_btn} onClick={() => handleGetData('http://localhost:8080/api/years/unemployment', 'Odsetek bezrobocia (%)')}>
                        odsetek bezrobocia
                    </button>
                </div>
            </div>

            <div>
                <h2 className={styles.centerStyle}>Istotne wydarzenia</h2>
            </div>
            <div className={styles.eventContainer}>

                <div className={styles.polishEvents}>
                    <h3>Wstąpienie Polski do Unii Europejskiej w 2004 roku</h3>
                    <p>W ramach członkostwa w UE, Polska korzysta z wielu korzyści, takich jak dostęp do jednolitego rynku, funduszy strukturalnych i spójności, współpraca w obszarze bezpieczeństwa i obrony, ochrona praw człowieka oraz udział w kształtowaniu polityki europejskiej.</p>
                </div>
                <div className={styles.polishEvents}>
                    <h3>Wprowawdzenie 500+</h3>
                    <p>Wprowadzenie programu "Rodzina 500 plus" w Polsce było jednym z najważniejszych wydarzeń w polityce społecznej kraju w ostatnich latach. Program został uruchomiony 1 kwietnia 2016 roku i miał na celu poprawę sytuacji materialnej rodzin oraz wsparcie wychowywania dzieci.</p>
                </div>
                <div className={styles.polishEvents}>
                    <h3>Reforma emerytalna z 2019 roku w Polsce</h3>
                    <p>Reforma emerytalna, wprowadzona w Polsce w 2019 roku, miała na celu wprowadzenie istotnych zmian w systemie emerytalnym kraju. Głównym celem reformy było poprawienie stabilności finansowej systemu emerytalnego, zwiększenie wysokości emerytur oraz dostosowanie systemu do zmieniających się warunków demograficznych i społecznych.</p>
                </div>
            </div>
        </div>
    )
}

export default Main
