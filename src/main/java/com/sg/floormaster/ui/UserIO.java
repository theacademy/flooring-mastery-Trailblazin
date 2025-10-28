package com.sg.floormaster.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Seun :D
 */
public interface UserIO {
    void print(String message);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);


    int readInt(String prompt, int min, String ignoreChar);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);
    BigDecimal readDecimal(String prompt);
    BigDecimal readDecimal(String prompt, int min);
    LocalDate readDate(String prompt);
    LocalDate readDate(String prompt, LocalDate currOrderDate);
    String readName(String prompt);

    String readString(String prompt);
    String readString(String prompt, List<String> validStrs);

}
