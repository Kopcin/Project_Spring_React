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
        EntityManager em = factory.createEntityManager();
        Year j1;

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

        for (Integer i = 1960; i <= 2022; i++) {
            em.getTransaction().begin();
            j1 = new Year(i);
            j1.setGdp(gdpReader.getLongValueByYear(i));
            j1.setLife_expectancy(lifeExpectancyReader.getFloatValueByYear(i));
            j1.setPoverty_headcount(povReader.getFloatValueByYear(i));
            j1.setUnemployment(unemplaymentReader.getFloatValueByYear(i));
            j1.setInflation(inflationReader.getFloatValueByYear(i));
            j1.setFamily_benefits(aofbReader.getLongValueByYear(i));
            j1.setAverage_retirement(avRetirementReader.getFloatValueByYear(i));
            j1.setBad_household_situation(badHousSit.getFloatValueByYear(i));
            j1.setCrimes(crimesReader.getLongValueByYear(i));
            j1.setHospital_patients(hospPacReader.getLongValueByYear(i));
            j1.setPsychiatric_patients(psychPacReader.getLongValueByYear(i));
            j1.setSuicide_attempts(suicideAttReader.getLongValueByYear(i));
            j1.setPopulation(populationReader.getLongValueByYear(i));

            em.persist(j1);
            em.getTransaction().commit();
        }

        em.close();
        factory.close();
    }
}