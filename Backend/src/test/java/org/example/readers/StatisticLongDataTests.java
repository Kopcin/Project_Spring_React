package org.example.readers;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

//Tests in this file are for TDD
public class StatisticLongDataTests {

    StatisticLongData data;

    @BeforeEach
    public void initatateList(){
        data = new GUSCSVLongReader("src/main/resources/data/psychiatric_patients.csv")
                .toStatisticData();
    }

    @Test
    public void getSizeTest(){
        Assertions.assertEquals(data.getSize(),9);
    }

    @Test
    public void getValueByYearTest(){
        Assertions.assertEquals(data.getValueByYear(2020),163661);
    }

    @Test
    public void getValueFromInvalidYearTest(){
        Assertions.assertEquals(data.getValueByYear(1990), StatisticDataReader.LONG_VALUE_NOT_FOUND);
    }

    @Test
    public void getTotalFromExistingYearsTest(){
        Assertions.assertEquals(data.getTotalBetweenYear(2019,2021),535483);
    }

    @Test
    public void getTotalFromNoDataYearsExeptionTest(){
        Throwable throwable = Assertions.assertThrows(NullPointerException.class, () -> data.getTotalBetweenYear(2000,1992));
    }

    @Test
    public void getTotalFromIvalidYearsExeptionTest(){
        Throwable throwable = Assertions.assertThrows(InvalidParameterException.class, () -> data.getTotalBetweenYear(2009,2001));
    }
}
