package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.Year;
import org.example.readers.GUSCSVReader;
import org.example.readers.StatisticData;
import org.example.readers.WordBankXMLReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

        // JPA stuff
        System.out.println("JPA project");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager em = factory.createEntityManager();
        Year j1;

        WordBankXMLReader<Long> gdpReader = new WordBankXMLReader("src/main/resources/data/gdp_currentlcu.xml", Long.class);
        WordBankXMLReader<Float> povReader = new WordBankXMLReader("src/main/resources/data/poverty_headcount_perc.xml", Float.class);
        WordBankXMLReader<Float> lifeExpectancyReader = new WordBankXMLReader("src/main/resources/data/life_expectancy_at_birth.xml",Float.class);
        WordBankXMLReader<Float> unemplaymentReader = new WordBankXMLReader("src/main/resources/data/unemployment.xml",Float.class);
        WordBankXMLReader<Float> inflationReader = new WordBankXMLReader("src/main/resources/data/inflation.xml",Float.class);
        WordBankXMLReader<Long> populationReader = new WordBankXMLReader("src/main/resources/data/population.xml",Long.class);
        GUSCSVReader<Long> aofbReader = new GUSCSVReader<Long>("src/main/resources/data/amount_of_family benefits_paid.csv", Long.class);
        GUSCSVReader<Float> avRetirementReader = new GUSCSVReader<Float>("src/main/resources/data/avarage_retirement.csv", Float.class);
        GUSCSVReader<Float> badHousSit = new GUSCSVReader<Float>("src/main/resources/data/bad_household_situation.csv", Float.class);
        GUSCSVReader<Long> crimesReader = new GUSCSVReader<Long>("src/main/resources/data/crimes.csv", Long.class);
        GUSCSVReader<Long> hospPacReader = new GUSCSVReader<Long>("src/main/resources/data/hospital_patients.csv", Long.class);
        GUSCSVReader<Long> psychPacReader = new GUSCSVReader<Long>("src/main/resources/data/psychiatric_patients.csv", Long.class);
        GUSCSVReader<Long> suicideAttReader = new GUSCSVReader<Long>("src/main/resources/data/suicide_attempts_per_100k.csv",Long.class);

        StatisticData<Long> aofbData = aofbReader.toStatisticData();

        for (Integer i = 1960; i <= 2022; i++) {
            em.getTransaction().begin();
            j1 = new Year(i);
            j1.setGdp(gdpReader.getLongValueByYear(i));
            j1.setLife_expectancy(lifeExpectancyReader.getFloatValueByYear(i));
            j1.setPoverty_headcount(povReader.getFloatValueByYear(i));
            j1.setUnemployment(unemplaymentReader.getFloatValueByYear(i));
            j1.setInflation(inflationReader.getFloatValueByYear(i));
            j1.setFamily_benefits(aofbData.getValueByYear(i));
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