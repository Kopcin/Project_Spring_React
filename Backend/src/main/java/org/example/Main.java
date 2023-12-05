package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.Year;
import org.example.readers.GUSCSVReader;
import org.example.readers.WordBankXMLReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

        loadData();
    }

    public static void loadData(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager entityManager = factory.createEntityManager();
        Year year;

        WordBankXMLReader gdpReader = new WordBankXMLReader("src/main/resources/data/gdp_currentlcu.xml");
        WordBankXMLReader povReader = new WordBankXMLReader("src/main/resources/data/poverty_headcount_perc.xml");
        WordBankXMLReader lifeExpectancyReader = new WordBankXMLReader("src/main/resources/data/life_expectancy_at_birth.xml");
        WordBankXMLReader unemplaymentReader = new WordBankXMLReader("src/main/resources/data/unemployment.xml");
        WordBankXMLReader inflationReader = new WordBankXMLReader("src/main/resources/data/inflation.xml");
        WordBankXMLReader populationReader = new WordBankXMLReader("src/main/resources/data/population.xml");
        GUSCSVReader aofbReader = new GUSCSVReader("src/main/resources/data/amount_of_family benefits_paid.csv");
        GUSCSVReader avRetirementReader = new GUSCSVReader("src/main/resources/data/avarage_retirement.csv");
        GUSCSVReader badHousSit = new GUSCSVReader("src/main/resources/data/bad_household_situation.csv");
        GUSCSVReader crimesReader = new GUSCSVReader("src/main/resources/data/crimes.csv");
        GUSCSVReader hospPacReader = new GUSCSVReader("src/main/resources/data/hospital_patients.csv");
        GUSCSVReader psychPacReader = new GUSCSVReader("src/main/resources/data/psychiatric_patients.csv");
        GUSCSVReader suicideAttReader = new GUSCSVReader("src/main/resources/data/suicide_attempts_per_100k.csv");

        for (Integer yearIterator = 1960; yearIterator <= 2022; yearIterator++) {
            entityManager.getTransaction().begin();
            year = new Year(yearIterator);
            year.setGdp(gdpReader.getLongValueByYear(yearIterator));
            year.setLife_expectancy(lifeExpectancyReader.getFloatValueByYear(yearIterator));
            year.setPoverty_headcount(povReader.getFloatValueByYear(yearIterator));
            year.setUnemployment(unemplaymentReader.getFloatValueByYear(yearIterator));
            year.setInflation(inflationReader.getFloatValueByYear(yearIterator));
            year.setFamily_benefits(aofbReader.getLongValueByYear(yearIterator));
            year.setAverage_retirement(avRetirementReader.getFloatValueByYear(yearIterator));
            year.setBad_household_situation(badHousSit.getFloatValueByYear(yearIterator));
            year.setCrimes(crimesReader.getLongValueByYear(yearIterator));
            year.setHospital_patients(hospPacReader.getLongValueByYear(yearIterator));
            year.setPsychiatric_patients(psychPacReader.getLongValueByYear(yearIterator));
            year.setSuicide_attempts(suicideAttReader.getLongValueByYear(yearIterator));
            year.setPopulation(populationReader.getLongValueByYear(yearIterator));

            entityManager.persist(year);
            entityManager.getTransaction().commit();
        }

        entityManager.close();
        factory.close();
    }
}