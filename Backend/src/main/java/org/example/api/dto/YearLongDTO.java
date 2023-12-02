package org.example.api.dto;

public class YearLongDTO {
    private long year;
    private Long column;

    public YearLongDTO(long year, Long column) {
        this.year = year;
        this.column = column;
    }

    public YearLongDTO() {

    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public Long getColumn() {
        return column;
    }

    public void setColumn(Long column) {
        this.column = column;
    }
}
