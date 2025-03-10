-- Projekt Integracja systemów --

Skład grupy:
Mateusz Korzeniowski, Kamil Kopciński

Temat:
Porównanie danych ekonomicznych z danymi dotyczącymi dobrostanu polaków z dokładnością do roku.

Opis:
Celem projektu jest stworzenie strony internetowaj, która pozwoli użytkowikowi zbadać dane ekonomiczne ("np. inflacja") i porównać je z danymi na temat dobrostanu i jakości życia polaków ("np średnią długości życia").
Dane są pobierane z zapisanych na serwerze plików .xml lub .csv i zapisywane do bazy danych sql. Dane z bazy są wczytywane za pomącą zapytań do servera REST. Frontend jest wykonany przy użyciu szkieletu React.
Dane na stronie są wyświetlane na wykresie i automatycznie dobiera zakres w zależności od dostępności danych.

Podział pracy:
- Mateusz Korzeniowski: ORM, Readery, Przygotowanie danych
- Kamil Kopciński: Frontend i Backend

Wykorzystane technologie i biblioteki:
-Java:
	hibernate.orm.core
	javax.xml.parsers.javax.api
	mysql.connector.java
	opencsv
	SpringBoot: m.in.: org.springframework.boot

-JavaScript:
	-Node.js 18
	-React:
		axios
		chart.js
		react-chartjs-2

Konfiguracja:
-Do działania aplikacji jest wymagana usługa bazodanowa MySQL i Apache aktywowane w XAMPP i przy pomocy MyPHPAdmin utworzyć tabelę o nazwie "jpa"
-Przenieść folder projektu do htdocs
-Otworzyć i Uruchomić folder ORM w IDE odpowiednim dla Javy, najlepiej IntelliJ IDEA
-Przejść do folderu client i uruchomić za pomocą poleceń Node.js: 'npm install', następnie 'npm start', powinna uruchomić się strona w przeglądarce na adresie http://localhost:3000
-Wszystkie pliki z danymi są przechowywane w ścieżce względnej projektu src/main/resources/data/. W razie próby aktualizacji danych należy je umieścić w tym folderze pod tą samą nazwą

Źródła danych:
World bank: https://www.worldbank.org - pliki danych w formacie XML
Główny urząd statystyczny: https://bdl.stat.gov.pl/bdl/dane/podgrup/temat - pliki danych w formacie CSV
