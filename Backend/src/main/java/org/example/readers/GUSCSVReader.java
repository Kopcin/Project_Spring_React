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

public class GUSCSVReader implements StatisticDataReader {

    private List<List<String>> records;
    private String file;
    private CSVReader csvReader;

    // Class type;

    public GUSCSVReader(String filePath){
        //this.type = type;
        char separator = ';';
        this.records = new ArrayList<List<String>>();
        this.file = filePath;
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            this.csvReader = new CSVReaderBuilder(br).withCSVParser(parser).build();
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
        List<String> newrow = new ArrayList<String>();
        i = 0;
        for(String r : row){
            if (r != "") {
                System.out.println(r);
                splitedRow = r.split(";");
                newrow.add(splitedRow[splitedRow.length-2]);
            }
        }
        trimed.set(0,newrow);

        return trimed;
    }

    /*
    @Override
    public StatisticData<T> toStatisticData() {
        ArrayList<SingleData> data = new ArrayList<SingleData>();

        for (Integer i = 1960; i <= 2022; i++){
            if(this.type == Long.class)data.add(new SingleData(i,this.getLongValueByYear(i)));
            if(this.type == Float.class)data.add(new SingleData(i,this.getFloatValueByYear(i)));
        }
        return  new StatisticData(data);
    }
     */


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


    public Long getLongValueByYear(Integer year){
        List<String> years = records.get(0);
        String currentYear;
        int i = 0;
        while(true){
            try {
                currentYear = years.get(i);
            }
            catch (IndexOutOfBoundsException e){
                return StatisticDataReader.LONG_VALUE_NOT_FOUND;
            }
            if(currentYear.equals(String.valueOf(year))){
                try {
                    return Long.valueOf(records.get(1).get(i));
                }
                catch (NumberFormatException e){
                    return StatisticDataReader.LONG_VALUE_NOT_FOUND;
                }
            }
            i++;
        }
    }


    public Float getFloatValueByYear(Integer year){
        List<String> years = records.get(0);
        String currentYear;
        int i = 0;
        while(true){
            try {
                currentYear = years.get(i);
            }
            catch (IndexOutOfBoundsException e){
                return WordBankXMLReader.FLOAT_VALUE_NOT_FOUND;
            }
            if(currentYear.equals(String.valueOf(year))){
                try {
                    return Float.valueOf(records.get(1).get(i));
                }
                catch (NumberFormatException e){
                    return WordBankXMLReader.FLOAT_VALUE_NOT_FOUND;
                }
            }
            i++;
        }
    }


}
