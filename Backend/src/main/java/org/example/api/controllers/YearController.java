package org.example.api.controllers;

import org.example.api.dto.YearFloatDTO;
import org.example.api.dto.YearLongDTO;
import org.example.api.repository.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/years")
public class YearController {
    private YearRepository yearRepository;

    @Autowired
    public YearController(YearRepository yearRepository) {
        this.yearRepository = yearRepository;
    }

    @GetMapping("/population")
    public List<YearLongDTO> getYearAndPopulation() {
        return yearRepository.findYearAndPopulation();
    }

    @GetMapping("/gdp")
    public List<YearLongDTO> getYearAndGDP() {
        return yearRepository.findYearAndGDP();
    }

    @GetMapping("/crimes")
    public List<YearLongDTO> getYearAndCrimes() {
        return yearRepository.findYearAndCrimes();
    }

    @GetMapping("/familyBenefits")
    public List<YearLongDTO> getYearAndFamilyBenefits() {
        return yearRepository.findYearAndFamilyBenefits();
    }

    @GetMapping("/hospitalPatients")
    public List<YearLongDTO> getYearAndHospitalityPatients() {
        return yearRepository.findYearAndHospitalityPatients();
    }

    @GetMapping("/psychiatricPatients")
    public List<YearLongDTO> getYearAndPsychiatricPatients() {
        return yearRepository.findYearAndPsychiatricPatients();
    }

    @GetMapping("/suicideAttempts")
    public List<YearLongDTO> getYearAndSuicideAttempts() {
        return yearRepository.findYearAndSuicideAttempts();
    }

    @GetMapping("/averageRetirement")
    public List<YearFloatDTO> getYearAndAverageRetirement() {
        return yearRepository.findYearAndAverageRetirement();
    }

    @GetMapping("/badHouseholdSituation")
    public List<YearFloatDTO> getYearAndBadHouseholdSituation() {
        return yearRepository.findYearAndBadHouseholdSituation();
    }

    @GetMapping("/inflation")
    public List<YearFloatDTO> getYearAndInflation() {
        return yearRepository.findYearAndInflation();
    }

    @GetMapping("/lifeExpectancy")
    public List<YearFloatDTO> getYearAndLifeExpectancy() {
        return yearRepository.findYearAndLifeExpectancy();
    }

    @GetMapping("/povertyHeadcount")
    public List<YearFloatDTO> getYearAndPovertyHeadcount() {
        return yearRepository.findYearAndPovertyHeadcount();
    }

    @GetMapping("/unemployment")
    public List<YearFloatDTO> getYearAndUnemployment() {
        return yearRepository.findYearAndUnemployment();
    }
}