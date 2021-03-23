package com.example.worddef_fragment.other.time;

import java.util.Calendar;

public class TimeOperator {

    private Calendar calendar;

    public TimeOperator(Calendar calendar, long id) {
        this.calendar = calendar;
        setTimeInMil(id);
    }

    private void setTimeInMil(long timeMil) {
        calendar.setTimeInMillis(timeMil);
    }

    public int getYear(long timeMil) {
       return  calendar.get(Calendar.YEAR);
    }

    public int getMnth(long timeMil) {
        return calendar.get(Calendar.MONTH);
    }

    public int getDay(long timeMil) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getHour(long timeMil) {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMin(long timeMil) {
        return calendar.get(Calendar.MINUTE);
    }

     public int getSec(long timeMil) {
        return calendar.get(Calendar.SECOND);
    }
}
