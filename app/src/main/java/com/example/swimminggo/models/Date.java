package com.example.swimminggo.models;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Date {
    int day, month, year;
    String fullName;

    public Date(Calendar calendar) {
        this.day = calendar.get(Calendar.DATE);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.year = calendar.get(Calendar.YEAR);
        this.fullName = getName(calendar);
    }

    public Date(String date){
        List<Integer> ints = Arrays.stream(date.split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        this.day = ints.get(2);
        this.month = ints.get(1);
        this.year = ints.get(0);
    }

    public int calculateAge(){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        return ageInt;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    public String toFormatRequest() {
        return year + "-" + month + "-" + day;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private String getName(Calendar calendar) {
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.MONDAY:
                return "Thứ 2";
            case Calendar.TUESDAY:
                return "Thứ 3";
            case Calendar.WEDNESDAY:
                return "Thứ 4";
            case Calendar.THURSDAY:
                return "Thứ 5";
            case Calendar.FRIDAY:
                return "Thứ 6";
            case Calendar.SATURDAY:
                return "Thứ 7";
        }
        return "C.Nhật";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return day == date.day &&
                month == date.month &&
                year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}
