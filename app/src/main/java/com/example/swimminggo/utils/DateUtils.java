package com.example.swimminggo.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public String fullNameDay(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return "Thứ hai";
            case Calendar.TUESDAY:
                return "Thứ ba";
            case Calendar.WEDNESDAY:
                return "Thứ tư";
            case Calendar.THURSDAY:
                return "Thứ năm";
            case Calendar.FRIDAY:
                return "Thứ sáu";
            case Calendar.SATURDAY:
                return "Thứ bảy";
            default:
                return "Chủ nhật";
        }
    }

    public String coverToDateString(int day, int month, int year) {
        return year + "-" + month + "-" + day;
    }

    public Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }
}
