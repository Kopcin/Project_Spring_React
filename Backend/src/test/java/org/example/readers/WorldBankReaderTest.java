package org.example.readers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorldBankReaderTest {
    private WordBankXMLLongReader reader;

    @BeforeEach
    public void initiateReader(){
        reader = new WordBankXMLLongReader("src/main/resources/data/gdp_currentlcu.xml");
    }

    @Test
    public void TestValueOf2020(){
        Assertions.assertEquals(reader.getValueByYear(2020),2337672000000L,"Should be 2337672000000");
    }

    @Test
    public void TestNonExistingData(){
        Assertions.assertEquals(reader.getValueByYear(2022), StatisticLongDataReader.LONG_VALUE_NOT_FOUND, "Should not find value and return LONG_VALUE_NOT_FOUND");
    }

    @Test
    public void TestInvalidFile(){
        try {
            Throwable e = Assertions.assertThrows(Exception.class, () ->
                    new WordBankXMLLongReader("InvlaidPath"));
        }
        catch (Exception e)
        {
            System.out.println("error occured while reading file");
        }
    }
}
