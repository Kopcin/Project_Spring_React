package org.example.readers;

public interface StatisticFloatDataReader {
    public static final Float FLOAT_VALUE_NOT_FOUND = -999F;

    public Float getValueByYear(Integer year);
}
