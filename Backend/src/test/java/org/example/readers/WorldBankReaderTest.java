import org.example.readers.StatisticDataReader;
import org.example.readers.WordBankXMLReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorldBankReaderTest {

    private WordBankXMLReader reader;

    @BeforeEach
    public void initiateReader(){
        reader = new WordBankXMLReader("src/main/resources/data/gdp_currentlcu.xml");
    }

    @Test
    public void TestValueOf2020(){
        Assertions.assertEquals(reader.getLongValueByYear(2020),2337672000000L,"Should be 2337672000000");
    }

    @Test
    public void TestNonExistingData(){
        Assertions.assertEquals(reader.getLongValueByYear(2022), StatisticDataReader.LONG_VALUE_NOT_FOUND, "Should not find value and return LONG_VALUE_NOT_FOUND");
    }

    @Test
    public void TestInvalidFile(){
        try {
            Throwable e = Assertions.assertThrows(Exception.class, () ->
                    new WordBankXMLReader("InvlaidPath"));
        }
        catch (Exception e)
        {
            System.out.println("error occured while reading file");
        }
    }
}
