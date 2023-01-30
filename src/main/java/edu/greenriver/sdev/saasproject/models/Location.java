package edu.greenriver.sdev.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Creates a Location object.
 * @author Keny Dutton-Gillespie
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location
{
    private String city;
    private String state;
    private String country;
    private double latitude;
    private double longitude;

    /**
     * Retrieves the reported city the encounter happened in
     * @return the city of the encounter
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Sets the city of the encounter
     * @param city the city of the encounter
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Retrieves the reported state the encounter happened in
     * @return the state of the encounter
     */
    public String getState()
    {
        return state;
    }

    /**
     * Sets the state of the encounter
     * @param state the state of the encounter
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * Retrieves the reported country the encounter happened in
     * @return the country of the encounter
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * Sets the country of the encounter
     * @param country the country of the encounter
     */
    public void setCountry(String country)
    {
        this.country = country;
    }

    /**
     * Retrieves the reported latitude coordinate the encounter happened in
     * @return the latitude coordinate of the encounter
     */
    public double getLatitude()
    {
        return latitude;
    }

    /**
     * Sets the latitude coordinate the encounter happened in
     * @param latitude the latitude coordinate of the encounter
     */
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    /**
     * Retrieves the reported longitude coordinate the encounter happened in
     * @return the longitude coordinate of the encounter
     */
    public double getLongitude()
    {
        return longitude;
    }

    /**
     * Sets the longitude coordinate the encounter happened in
     * @param longitude the longitude coordinate of the encounter
     */
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return "Location{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
