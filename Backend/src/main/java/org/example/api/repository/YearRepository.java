package org.example.api.repository;

import org.example.api.dto.YearFloatDTO;
import org.example.api.dto.YearLongDTO;
import org.example.models.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YearRepository extends JpaRepository<Year, Long> {
    @Query("SELECT new org.example.api.dto.YearLongDTO(y.year, y.population) FROM Year y")
    List<YearLongDTO> findYearAndPopulation();

    @Query("SELECT new org.example.api.dto.YearLongDTO(y.year, y.gdp) FROM Year y")
    List<YearLongDTO> findYearAndGDP();

    @Query("SELECT new org.example.api.dto.YearLongDTO(y.year, y.crimes) FROM Year y")
    List<YearLongDTO> findYearAndCrimes();

    @Query("SELECT new org.example.api.dto.YearLongDTO(y.year, y.family_benefits) FROM Year y")
    List<YearLongDTO> findYearAndFamilyBenefits();

    @Query("SELECT new org.example.api.dto.YearLongDTO(y.year, y.hospital_patients) FROM Year y")
    List<YearLongDTO> findYearAndHospitalityPatients();

    @Query("SELECT new org.example.api.dto.YearLongDTO(y.year, y.psychiatric_patients) FROM Year y")
    List<YearLongDTO> findYearAndPsychiatricPatients();

    @Query("SELECT new org.example.api.dto.YearLongDTO(y.year, y.suicide_attempts) FROM Year y")
    List<YearLongDTO> findYearAndSuicideAttempts();

    @Query("SELECT new org.example.api.dto.YearFloatDTO(y.year, y.average_retirement) FROM Year y")
    List<YearFloatDTO> findYearAndAverageRetirement();

    @Query("SELECT new org.example.api.dto.YearFloatDTO(y.year, y.bad_household_situation) FROM Year y")
    List<YearFloatDTO> findYearAndBadHouseholdSituation();

    @Query("SELECT new org.example.api.dto.YearFloatDTO(y.year, y.inflation) FROM Year y")
    List<YearFloatDTO> findYearAndInflation();

    @Query("SELECT new org.example.api.dto.YearFloatDTO(y.year, y.life_expectancy) FROM Year y")
    List<YearFloatDTO> findYearAndLifeExpectancy();

    @Query("SELECT new org.example.api.dto.YearFloatDTO(y.year, y.poverty_headcount) FROM Year y")
    List<YearFloatDTO> findYearAndPovertyHeadcount();

    @Query("SELECT new org.example.api.dto.YearFloatDTO(y.year, y.unemployment) FROM Year y")
    List<YearFloatDTO> findYearAndUnemployment();
}