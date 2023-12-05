package org.example.readers;

import com.sun.jdi.InvalidTypeException;

import java.security.InvalidParameterException;
import java.util.List;

public class StatisticLongData {
    List<SingleLongData> dataList;

    public StatisticLongData(List<SingleLongData> dataList){
        this.dataList = dataList;
    }

    public Long getValueByYear(int i) {
        for (SingleLongData element : dataList) {
            if (element.getYear() == i) return element.getValue();
        }
         return StatisticLongDataReader.LONG_VALUE_NOT_FOUND;
    }

    public int getSize(){
        System.out.println(dataList.size());
        return dataList.size();
    }

    public Long getTotalBetweenYear(int firstYear, int lastYear){
        if(firstYear>lastYear){
            throw new InvalidParameterException();
        }
        Long value;
        Long total = 0L;
        for(int i=firstYear; i<=lastYear; i++){
            value = this.getValueByYear(i);
            if(value == StatisticDataReader.LONG_VALUE_NOT_FOUND){
                throw new NullPointerException();
            }
            total += value;
        }
        return total;
    }
}

