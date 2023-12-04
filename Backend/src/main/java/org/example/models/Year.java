package org.example.models;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import jakarta.persistence.*;
import org.example.readers.WordBankXMLReader;

@Entity
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private long year;
    @Nullable
    private Long gdp;
    @Nullable
    private Float life_expectancy;
    @Nullable
    private Float poverty_headcount;
    @Nullable
    private Float unemployment;
    @Nullable
    private Float inflation;
    @Nullable
    private Long family_benefits;
    @Nullable
    private Float average_retirement;
    @Nullable
    private Float bad_household_situation;
    @Nullable
    private Long crimes;
    @Nullable
    private Long hospital_patients;
    @Nullable
    private Long psychiatric_patients;
    @Nullable
    private Long suicide_attempts;

    @Nullable
    private Long population;

    public Year(long year) {
        this.year = year;
    }

    public Year() {
    }

    public void setGdp(Long gdp) {
        if(gdp != WordBankXMLReader.LONG_VALUE_NOT_FOUND)this.gdp = gdp;
        else this.gdp = null;
    }

    public void setLife_expectancy(float life_expectancy) {
        if(life_expectancy != WordBankXMLReader.FLOAT_VALUE_NOT_FOUND)this.life_expectancy = life_expectancy;
        else this.life_expectancy = null;
    }

    public void setPoverty_headcount(float poverty_headcount) {
        if(poverty_headcount != WordBankXMLReader.FLOAT_VALUE_NOT_FOUND)this.poverty_headcount = poverty_headcount;
        else this.poverty_headcount = null;
    }

    public void setUnemployment(Float unemployment) {
        if(unemployment != WordBankXMLReader.FLOAT_VALUE_NOT_FOUND)this.unemployment = unemployment;
        else this.unemployment = null;
    }

    public void setInflation(Float inflation) {
        if(inflation != WordBankXMLReader.FLOAT_VALUE_NOT_FOUND)this.inflation = inflation;
        else this.inflation = null;
    }

    public void setFamily_benefits(Long family_benefits) {
        if(family_benefits != WordBankXMLReader.LONG_VALUE_NOT_FOUND)this.family_benefits = family_benefits;
        else this.family_benefits = null;
    }

    public void setAverage_retirement(Float average_retirement) {
        if(average_retirement != WordBankXMLReader.FLOAT_VALUE_NOT_FOUND)this.average_retirement = average_retirement;
        else this.average_retirement = null;
    }

    public void setBad_household_situation(float bad_household_situation){
        if(bad_household_situation != WordBankXMLReader.FLOAT_VALUE_NOT_FOUND)this.bad_household_situation = bad_household_situation;
        else this.bad_household_situation = null;
    }

    public void setCrimes(Long crimes){
        if(crimes != WordBankXMLReader.LONG_VALUE_NOT_FOUND)this.crimes = crimes;
        else this.crimes = null;
    }

    public void setHospital_patients(Long hospital_patients){
        if(hospital_patients != WordBankXMLReader.LONG_VALUE_NOT_FOUND)this.hospital_patients = hospital_patients;
        else this.hospital_patients = null;
    }

    public void setPsychiatric_patients(Long psychiatric_patients){
        if(psychiatric_patients != WordBankXMLReader.LONG_VALUE_NOT_FOUND)this.psychiatric_patients = psychiatric_patients;
        else this.psychiatric_patients = null;
    }

    public void setSuicide_attempts(Long suicide_attempts){
        if(suicide_attempts != WordBankXMLReader.LONG_VALUE_NOT_FOUND)this.suicide_attempts = suicide_attempts;
        else this.suicide_attempts = null;
    }

    public void setPopulation(Long population){
        if(population != WordBankXMLReader.LONG_VALUE_NOT_FOUND)this.population = population;
        else this.population = null;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public Long getPopulation() {
        return population;
    }
}
