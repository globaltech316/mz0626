package org.example.toolrental.util;

import org.example.toolrental.model.ToolType;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateCalculator {
    public static boolean isChargeableDay(LocalDate date, ToolType toolType) {
        if (isHoliday(date) && !toolType.isHolidayCharge()) {
            return false;
        }
        if (isWeekend(date) && !toolType.isWeekendCharge()) {
            return false;
        }
        return toolType.isWeekdayCharge();
    }

    public static boolean isHoliday(LocalDate date) {
        return isIndependenceDay(date) || isLaborDay(date);
    }

    public static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    private static boolean isIndependenceDay(LocalDate date) {
        LocalDate independenceDay = LocalDate.of(date.getYear(), 7, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }
        return date.equals(independenceDay);
    }

    private static boolean isLaborDay(LocalDate date) {
        LocalDate firstMondayOfSeptember = LocalDate.of(date.getYear(), 9, 1);
        while (firstMondayOfSeptember.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstMondayOfSeptember = firstMondayOfSeptember.plusDays(1);
        }
        return date.equals(firstMondayOfSeptember);
    }
}
