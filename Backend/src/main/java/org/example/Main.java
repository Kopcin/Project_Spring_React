package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.Year;
import org.example.readers.*;
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
        StatisticLongDataReader[] longRaeders = new StatisticLongDataReader[]
                {
                        new WordBankXMLLongReader("src/main/resources/data/gdp_currentlcu.xml"),
                        new WordBankXMLLongReader("src/main/resources/data/population.xml"),
                        new GUSCSVLongReader("src/main/resources/data/amount_of_family benefits_paid.csv"),
                        new GUSCSVLongReader("src/main/resources/data/crimes.csv"),
                        new GUSCSVLongReader("src/main/resources/data/hospital_patients.csv"),
                        new GUSCSVLongReader("src/main/resources/data/psychiatric_patients.csv"),
                        new GUSCSVLongReader("src/main/resources/data/suicide_attempts_per_100k.csv")
                };

        StatisticFloatDataReader[] floatReaders = new StatisticFloatDataReader[]{
                new WordBankXMLFloatReader("src/main/resources/data/poverty_headcount_perc.xml"),
                new WordBankXMLFloatReader("src/main/resources/data/life_expectancy_at_birth.xml"),
                new WordBankXMLFloatReader("src/main/resources/data/unemployment.xml"),
                new WordBankXMLFloatReader("src/main/resources/data/inflation.xml"),
                new GUSCSVFloatReader("src/main/resources/data/avarage_retirement.csv"),
                new GUSCSVFloatReader("src/main/resources/data/bad_household_situation.csv")

            };

        // Automatic Resource Management for EntityManager
        try (factory; EntityManager entityManager = factory.createEntityManager()) {
            for (int year = 1960; year <= 2022; year++) {
                entityManager.getTransaction().begin();
                Year yearData = createYearWithData(year, longRaeders, floatReaders);
                entityManager.persist(yearData);
                entityManager.getTransaction().commit();
            }
        }
    }

    private static Year createYearWithData(int yearValue, StatisticLongDataReader[] longRaeders ,StatisticFloatDataReader[] floatReaders) {

        Year yearData = new Year(yearValue);
        yearData.setGdp(longRaeders[0].getValueByYear(yearValue));
        yearData.setLife_expectancy(floatReaders[1].getValueByYear(yearValue));
        yearData.setPoverty_headcount(floatReaders[0].getValueByYear(yearValue));
        yearData.setUnemployment(floatReaders[2].getValueByYear(yearValue));
        yearData.setInflation(floatReaders[3].getValueByYear(yearValue));
        yearData.setFamily_benefits(longRaeders[2].getValueByYear(yearValue));
        yearData.setAverage_retirement(floatReaders[4].getValueByYear(yearValue));
        yearData.setBad_household_situation(floatReaders[5].getValueByYear(yearValue));
        yearData.setCrimes(longRaeders[3].getValueByYear(yearValue));
        yearData.setHospital_patients(longRaeders[4].getValueByYear(yearValue));
        yearData.setPsychiatric_patients(longRaeders[5].getValueByYear(yearValue));
        yearData.setSuicide_attempts(longRaeders[6].getValueByYear(yearValue));
        yearData.setPopulation(longRaeders[2].getValueByYear(yearValue));

        return yearData;
    }
/*
    public static void loadData(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager entityManager = factory.createEntityManager();
        Year year;

        WordBankXMLLongReader gdpReader = new WordBankXMLLongReader("src/main/resources/data/gdp_currentlcu.xml");
        WordBankXMLFloatReader povReader = new WordBankXMLFloatReader("src/main/resources/data/poverty_headcount_perc.xml");
        WordBankXMLFloatReader lifeExpectancyReader = new WordBankXMLFloatReader("src/main/resources/data/life_expectancy_at_birth.xml");
        WordBankXMLFloatReader unemplaymentReader = new WordBankXMLFloatReader("src/main/resources/data/unemployment.xml");
        WordBankXMLFloatReader inflationReader = new WordBankXMLFloatReader("src/main/resources/data/inflation.xml");
        WordBankXMLLongReader populationReader = new WordBankXMLLongReader("src/main/resources/data/population.xml");

        GUSCSVLongReader aofbReader = new GUSCSVLongReader("src/main/resources/data/amount_of_family benefits_paid.csv");
        GUSCSVFloatReader avRetirementReader = new GUSCSVFloatReader("src/main/resources/data/avarage_retirement.csv");
        GUSCSVFloatReader badHousSit = new GUSCSVFloatReader("src/main/resources/data/bad_household_situation.csv");
        GUSCSVLongReader crimesReader = new GUSCSVLongReader("src/main/resources/data/crimes.csv");
        GUSCSVLongReader hospPacReader = new GUSCSVLongReader("src/main/resources/data/hospital_patients.csv");
        GUSCSVLongReader psychPacReader = new GUSCSVLongReader("src/main/resources/data/psychiatric_patients.csv");
        GUSCSVLongReader suicideAttReader = new GUSCSVLongReader("src/main/resources/data/suicide_attempts_per_100k.csv");

        for (Integer yearIterator = 1960; yearIterator <= 2022; yearIterator++) {
            entityManager.getTransaction().begin();
            year = new Year(yearIterator);
            year.setGdp(gdpReader.getValueByYear(yearIterator));
            year.setLife_expectancy(lifeExpectancyReader.getValueByYear(yearIterator));
            year.setPoverty_headcount(povReader.getValueByYear(yearIterator));
            year.setUnemployment(unemplaymentReader.getValueByYear(yearIterator));
            year.setInflation(inflationReader.getValueByYear(yearIterator));
            year.setFamily_benefits(aofbReader.getValueByYear(yearIterator));
            year.setAverage_retirement(avRetirementReader.getValueByYear(yearIterator));
            year.setBad_household_situation(badHousSit.getValueByYear(yearIterator));
            year.setCrimes(crimesReader.getValueByYear(yearIterator));
            year.setHospital_patients(hospPacReader.getValueByYear(yearIterator));
            year.setPsychiatric_patients(psychPacReader.getValueByYear(yearIterator));
            year.setSuicide_attempts(suicideAttReader.getValueByYear(yearIterator));
            year.setPopulation(populationReader.getValueByYear(yearIterator));

            entityManager.persist(year);
            entityManager.getTransaction().commit();
        }

        entityManager.close();
        factory.close();
    }
 */
}