package org.example.readers;

import com.sun.jdi.InvalidTypeException;

import java.util.List;

public class StatisticLongData {
    List<SingleLongData> dataList;

    public StatisticLongData(List<SingleLongData> dataList){
        this.dataList = dataList;
    }

    public Long getValueByYear(Integer i) {
        for (SingleLongData element : dataList) {
            if (element.getYear() == i) return element.getValue();
        }
         return StatisticLongDataReader.LONG_VALUE_NOT_FOUND;
    }
}

