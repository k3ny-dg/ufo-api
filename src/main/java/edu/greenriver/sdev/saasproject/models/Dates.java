package edu.greenriver.sdev.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dates
{
    private int yearSighted;
    private int monthSighted;
    private int daySighted;
    private int hourSighted;
    private int minuteSighted;

    public int getYearSighted()
    {
        return yearSighted;
    }

    public void setYearSighted(int yearSighted)
    {
        this.yearSighted = yearSighted;
    }

    public int getMonthSighted()
    {
        return monthSighted;
    }

    public void setMonthSighted(int monthSighted)
    {
        this.monthSighted = monthSighted;
    }

    public int getDaySighted()
    {
        return daySighted;
    }

    public void setDaySighted(int daySighted)
    {
        this.daySighted = daySighted;
    }

    public int getHourSighted()
    {
        return hourSighted;
    }

    public void setHourSighted(int hourSighted)
    {
        this.hourSighted = hourSighted;
    }

    public int getMinuteSighted()
    {
        return minuteSighted;
    }

    public void setMinuteSighted(int minuteSighted)
    {
        this.minuteSighted = minuteSighted;
    }

    @Override
    public String toString()
    {
        return "Dates{" +
                "yearSighted=" + yearSighted +
                ", monthSighted=" + monthSighted +
                ", daySighted=" + daySighted +
                ", hourSighted=" + hourSighted +
                ", minuteSighted=" + minuteSighted +
                '}';
    }
}
