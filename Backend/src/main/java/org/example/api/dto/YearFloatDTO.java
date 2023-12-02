package org.example.api.dto;

public class YearFloatDTO {
    private long year;
    private Float column;

    public YearFloatDTO(long year, Float column) {
        this.year = year;
        this.column = column;
    }

    public YearFloatDTO() {

    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public Float getColumn() {
        return column;
    }

    public void setColumn(Float column) {
        this.column = column;
    }
}
