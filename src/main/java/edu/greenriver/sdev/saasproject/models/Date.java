package edu.greenriver.sdev.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Creates a Date object.
 * @author Keny Dutton-Gillespie
 * @version 1.0
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Date
{
    private int yearSighted;
    private int monthSighted;
    private int daySighted;
    private int hourSighted;
    private int minuteSighted;
    private String findValue;

    /**
     * A string with the date in YYYYMMDDHHMM format
     * @return the findValue
     */
    public String getFindValue()
    {
        return findValue;
    }

    /**
     * Sets the findValue
     * @param value A string with the date in YYYYMMDDHHMM format
     */
    public void setFindValue(String value)
    {
        this.findValue = value;
    }

    /**
     * Returns the year of the encounter
     * @return the reported year of the encounter
     */
    public int getYearSighted()
    {
        return yearSighted;
    }

    /**
     * Sets the year of the encounter
     * @param yearSighted the year of the encounter
     */
    public void setYearSighted(int yearSighted)
    {
        this.yearSighted = yearSighted;
    }

    /**
     * Returns the month of the encounter
     * @return the reported month of the encounter
     */
    public int getMonthSighted()
    {
        return monthSighted;
    }

    /**
     * Set the month of the encounter
     * @param monthSighted the month of the encounter
     */
    public void setMonthSighted(int monthSighted)
    {
        this.monthSighted = monthSighted;
    }

    /**
     * Returns the day of the encounter
     * @return the reported day of the encounter
     */
    public int getDaySighted()
    {
        return daySighted;
    }

    /**
     * Sets the day of the encounter
     * @param daySighted the day of the encounter
     */
    public void setDaySighted(int daySighted)
    {
        this.daySighted = daySighted;
    }

    /**
     * Returns the hour of the encounter
     * @return the reported hour of the encounter
     */
    public int getHourSighted()
    {
        return hourSighted;
    }

    /**
     * Sets the hour of the encounter
     * @param hourSighted the hour of the encounter
     */
    public void setHourSighted(int hourSighted)
    {
        this.hourSighted = hourSighted;
    }

    /**
     * Returns the reported minute of the encounter
     * @return the minute of the encounter
     */
    public int getMinuteSighted()
    {
        return minuteSighted;
    }

    /**
     * Sets the minute of the encounter
     * @param minuteSighted the minute of the encounter
     */
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
