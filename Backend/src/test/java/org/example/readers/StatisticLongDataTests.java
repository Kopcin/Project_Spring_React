package org.example.readers;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

//Tests in this file are for TDD
public class StatisticLongDataTests {

    StatisticLongData data;

    @BeforeEach
    public void initiateList(){
        data = new GUSCSVLongReader("src/main/resources/data/psychiatric_patients.csv")
                .toStatisticData();
    }

    @Test
    public void getSizeTest(){
        Assertions.assertEquals(9,data.getSize());
    }

    @Test
    public void getValueByYearTest(){
        Assertions.assertEquals(163661L,data.getValueByYear(2020));
    }

    @Test
    public void getValueFromInvalidYearTest(){
        Assertions.assertEquals(StatisticDataReader.LONG_VALUE_NOT_FOUND, data.getValueByYear(1990));
    }

    @Test
    public void getTotalFromExistingYearsTest(){
        Assertions.assertEquals(data.getTotalBetweenYear(2019,2021),535483L);
    }

    @Test
    public void getTotalFromNoDataYearsExceptionTest(){
        Throwable throwable = Assertions.assertThrows(NullPointerException.class, () -> data.getTotalBetweenYear(1992,2000));
    }

    @Test
    public void getTotalFromInvalidYearsExceptionTest(){
        Throwable throwable = Assertions.assertThrows(InvalidParameterException.class, () -> data.getTotalBetweenYear(2009,2001));
    }
}
