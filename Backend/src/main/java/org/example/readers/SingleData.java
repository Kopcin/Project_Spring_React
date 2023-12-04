package org.example.readers;

public class SingleData<T>{
    private Integer year;
    private T value;

    public SingleData(Integer year, T value) {
        this.year = year;
        this.value = value;
    }

    public Integer getYear() {
        return year;
    }

    public T getValue() {
        return value;
    }
}
