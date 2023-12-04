package org.example.api.repository;

import org.example.api.dto.YearFloatDTO;
import org.example.api.dto.YearLongDTO;
import org.example.models.Year;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class YearRepositoryIntegrationTest {
    @Autowired
    private YearRepository repository;

    @Test
    public void testFindYearAndPopulation() {
        // Given
        Year year = new Year(2022);
        year.setPopulation(1000000L);
        repository.saveAndFlush(year);

        // When
        List<YearLongDTO> result = repository.findYearAndPopulation();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getYear()).isEqualTo(2022);
        assertThat(result.get(0).getColumn()).isEqualTo(1000000L);
    }

    @Test
    public void testFindYearAndLifeExpectancy() {
        // Given
        Year year = new Year(1984);
        year.setLife_expectancy(80.23F);
        repository.saveAndFlush(year);

        // When
        List<YearFloatDTO> result = repository.findYearAndLifeExpectancy();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getYear()).isEqualTo(1984);
        assertThat(result.get(0).getColumn()).isEqualTo(80.23F);
    }

    @Test
    public void testFindYearAndUnemployment() {
        // Given
        Year year = new Year(1999);
        year.setUnemployment(5.0F);
        repository.saveAndFlush(year);

        // When
        List<YearFloatDTO> result = repository.findYearAndUnemployment();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getYear()).isEqualTo(1999);
        assertThat(result.get(0).getColumn()).isEqualTo(5.0F);
    }
}
