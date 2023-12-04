package org.example.readers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GUSReaderTest {
    private GUSCSVReader reader;

    @BeforeEach
    public void initiateReader(){
        reader = new GUSCSVReader("src/main/resources/data/crimes.csv");
    }

    @Test
    public void TestValueOf2020(){
        Assertions.assertEquals(reader.getLongValueByYear(2020),765408L,"Should be 765408");
    }

    @Test
    public void TestNonExistingData(){
        Assertions.assertEquals(reader.getLongValueByYear(1990), StatisticDataReader.LONG_VALUE_NOT_FOUND, "Should not find value and return LONG_VALUE_NOT_FOUND");
    }

    @Test
    public void TestInvalidFile(){
        try {
            Throwable e = Assertions.assertThrows(Exception.class, () ->
                    new GUSCSVReader("InvlaidPath"));
        }
        catch (Exception e)
        {
            System.out.println("error occured while reading file");
        }
    }
}
