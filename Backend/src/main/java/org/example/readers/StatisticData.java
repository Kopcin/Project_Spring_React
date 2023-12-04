package org.example.readers;

import com.sun.jdi.InvalidTypeException;

import java.util.List;

public class StatisticData<T> {
    List<SingleData<T>> dataList;

    public StatisticData(List<SingleData<T>> dataList){
        this.dataList = dataList;
    }

    private Class getType(){
        return dataList.get(0).getValue().getClass();
    }

    public T getValueByYear(Integer i) {
        for (SingleData<T> element : dataList) {
            if (element.getYear() == i) return element.getValue();
        }
        if(this.getType() == Long.class) return (T) StatisticDataReader.LONG_VALUE_NOT_FOUND;
        if(this.getType() == Float.class) return (T) StatisticDataReader.FLOAT_VALUE_NOT_FOUND;
        throw new RuntimeException();
    }
}

