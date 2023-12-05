package org.example.readers;

public interface StatisticLongDataReader {
    public static final Long LONG_VALUE_NOT_FOUND = 0L;
    public Long getValueByYear(Integer year);
}
