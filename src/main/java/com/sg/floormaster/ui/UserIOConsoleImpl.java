package com.sg.floormaster.ui;

import org.springframework.stereotype.Component;

import javax.swing.text.DateFormatter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author kylerudy
 */
@Component
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        return Double.parseDouble(sc.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double num;
        do {
            print(prompt);
            num = Double.parseDouble(sc.nextLine());
        } while (num < min || num > max);

        return num;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        return Float.parseFloat(sc.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num;
        do {
            print(prompt);
            num = Float.parseFloat(sc.nextLine());
        } while (num < min || num > max);

        return num;
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(sc.nextLine());
    }
    @Override
    public int readInt(String prompt, int min, String ignoreChar) {
        print(prompt);
        String input = sc.nextLine();
        // Used for "" case for updates
        int num = -1;

        if(input.equalsIgnoreCase(ignoreChar))
        {
            return num;
        }
        do {
            print(prompt);
            num = Integer.parseInt(input);
        } while (num < min);

        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num;
        do {
            print(prompt);
            num = Integer.parseInt(sc.nextLine());
        } while (num < min || num > max);

        return num;
    }

    @Override
    public long readLong(String prompt) {

        print(prompt);
        return Long.parseLong(sc.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long num;
        do {
            print(prompt);
            num = Long.parseLong(sc.nextLine());
        } while (num < min || num > max);

        return num;
    }
    @Override
    public LocalDate readDate(String prompt) {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = null;
        try {
            print(prompt);
            date = dateFormat.parse(sc.nextLine());
            //Convert date to local date
            return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            readDate(prompt);
        }
        return null;
    }


    @Override
    public LocalDate readDate(String prompt, LocalDate currOrderDate) {
        DateFormatter dateFormat = new DateFormatter(new SimpleDateFormat("MMDDYYYY"));
        LocalDate date = null;
        try {
            do {
                print(prompt);
                date = LocalDate.parse(sc.nextLine());
            } while (date.isBefore(currOrderDate));
            return date;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            readDate(prompt, currOrderDate);
        }
        return null;
    }

    @Override
    public String readName(String prompt) {
        String name;
        do {
            print(prompt);
            name = sc.nextLine();
        } while (!isValidCustomerName(name));
        return name;
    }

    public boolean isValidCustomerName(String name){
        if(Objects.equals(name, ""))
        {
            return true;
        }
        for(int i = 0; i< name.length(); i++){
            //Get char at each index of the name
            char c = name.charAt(i);
            // If this is NOT TRUE:
            // char is a letter or a digit
            // OR char is a dot
            // OR char is a comma
            if(!(Character.isLetterOrDigit(c) || c == '.' || c == ',')){
                return false;
            }
        }
        return true;
    }


    @Override
    public String readString(String prompt) {
        print(prompt);
        return sc.nextLine();
    }

    @Override
    public String readString(String prompt, List<String> validStrs) {
        String val;
        do {
            print(prompt);

            //Convert string to int
            val = sc.nextLine();
        } while (!isStringInList(val,validStrs));
        //Convert back to string to use for big int
        return val;
    }
    public boolean isStringInList(String str, List<String> validStrs){
        // Empty string allowed
        if(Objects.equals(str, ""))
        {
            return true;
        }
        // Return if str is in List or not
        return validStrs.contains(str);
    }

    @Override
    public BigDecimal readDecimal(String prompt) {
        print(prompt);
        try{
            BigDecimal decimal = new BigDecimal(sc.nextLine());
            return decimal;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            readDecimal(prompt);
        }
        return null;
    }


    @Override
    public BigDecimal readDecimal(String prompt, int min) {
        int val;
        try {
            do {
                print(prompt);
                //Convert string to int
                val = Integer.parseInt(sc.nextLine());
            } while (val < min);
            //Convert back to string to use for big int
            return new BigDecimal(Integer.toString(val));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            readDecimal(prompt, min);
        }
        return null;
    }

}
