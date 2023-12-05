package org.example.readers;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GUSCSVFloatReader implements StatisticFloatDataReader {

    private List<List<String>> records;
    private String inputFile;
    private CSVReader csvReader;

    // Class type;

    public GUSCSVFloatReader(String filePath){
        //this.type = type;
        this.records = new ArrayList<List<String>>();
        this.inputFile = filePath;
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            this.csvReader = new CSVReaderBuilder(bufferedReader).withCSVParser(parser).build();
            String[] values;
            while((values = csvReader.readNext()) != null){
                records.add(Arrays.asList(values));
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();;
        }
        records = trimData();
    }

    private List<List<String>> trimData(){
        List<List<String>> trimed = new ArrayList<List<String>>();
        List<String> row = new ArrayList<String>();
        int i =0;
        for (List<String> record : records) {
            for (String value : record) {
                if(i>1){
                    row.add(value);
                }
                i++;
            }
            trimed.add(row);
            row = new ArrayList<String>();
            i=0;
        }

        String[] splitedRow;
        row = trimed.get(0);
        List<String> newRow = new ArrayList<String>();
        i = 0;
        for(String r : row){
            if (r != "") {
                System.out.println(r);
                splitedRow = r.split(";");
                newRow.add(splitedRow[splitedRow.length-2]);
            }
        }
        trimed.set(0,newRow);

        return trimed;
    }

    //This method can be used for debugging
    public void printRecords() {
        for (List<String> record : records) {
            for (String value : record) {
                System.out.print(value + "   ");
            }
            System.out.println();
        }
    }

    public String getFirstYear(){
        return records.get(0).get(0);
    }

    public Float getValueByYear(Integer year){
        List<String> years = records.get(0);
        String currentYear;
        int i = 0;
        while(true){
            try {
                currentYear = years.get(i);
            }
            catch (IndexOutOfBoundsException e){
                return StatisticFloatDataReader.FLOAT_VALUE_NOT_FOUND;
            }
            if(currentYear.equals(String.valueOf(year))){
                try {
                    return Float.valueOf(records.get(1).get(i));
                }
                catch (NumberFormatException e){
                    return StatisticFloatDataReader.FLOAT_VALUE_NOT_FOUND;
                }
            }
            i++;
        }
    }


}
