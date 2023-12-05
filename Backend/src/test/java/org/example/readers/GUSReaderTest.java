package org.example.readers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GUSReaderTest {
    private GUSCSVLongReader reader;

    @BeforeEach
    public void initiateReader(){
        reader = new GUSCSVLongReader("src/main/resources/data/crimes.csv");
    }

    @Test
    public void TestValueOf2020(){
        Assertions.assertEquals(reader.getValueByYear(2020),765408L,"Should be 765408");
    }

    @Test
    public void TestNonExistingData(){
        Assertions.assertEquals(reader.getValueByYear(1990), StatisticLongDataReader.LONG_VALUE_NOT_FOUND, "Should not find value and return LONG_VALUE_NOT_FOUND");
    }

    @Test
    public void TestInvalidFile(){
        try {
            Throwable e = Assertions.assertThrows(Exception.class, () ->
                    new GUSCSVLongReader("InvlaidPath"));
        }
        catch (Exception e)
        {
            System.out.println("error occured while reading file");
        }
    }
}
