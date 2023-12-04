package org.example.readers;

public interface StatisticDataReader<T> {
    public static final Float FLOAT_VALUE_NOT_FOUND = -999F;
    public static final Long LONG_VALUE_NOT_FOUND = 0L;
    //public StatisticData<T> toStatisticData();
    public String getFirstYear();
}
