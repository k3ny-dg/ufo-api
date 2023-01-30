package edu.greenriver.sdev.saasproject.services;

import edu.greenriver.sdev.saasproject.models.Date;
import edu.greenriver.sdev.saasproject.models.Location;
import edu.greenriver.sdev.saasproject.models.UFO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a service layer for the UFO API
 * @author Keny Dutto-Gillespie
 * @version 1.0
 */
@Service
public class UFOService
{
    public static final int NUM_DAYS_MONTH = 31;
    public static final int NO_MO_YEAR = 12;
    public static final int LATEST_YEAR = 2023;
    public static final int EARLIEST_YEAR = 1900;
    private List<UFO> ufos = new ArrayList<>(List.of(
            UFO.builder()
                    .id(001)
                    .shape("disk")
                    .description("Large UFO over Mt. ILIAMNA Alaska")
                    .encounterLength(300.0)
                    .build(),
            UFO.builder()
                    .id(002)
                    .shape("changing")
                    .description("We could observe red lights dancing across the underside red lights were very bright and very active.")
                    .encounterLength(21600.0)
                    .build(),
            UFO.builder()
                    .id(003)
                    .shape("changing")
                    .description("INTENSE AMBER-ORANGE HONEYCOMB SHAPED DUAL HORIZONTAL LIGHT;&#44 SILENT DECENT....")
                    .encounterLength(600.0)
                    .build(),
            UFO.builder()
                    .id(004)
                    .shape("cigar")
                    .description("I explained away the first time I thought I seen this object but twice weeks apart...I&#39m certain now")
                    .encounterLength(15.0)
                    .build()
    ));

    private List<Location> locations = new ArrayList<>(List.of(
            Location.builder()
                    .city("Anchor Point")
                    .state("AK")
                    .country("US")
                    .latitude(59.7766667)
                    .longitude(-151.8313889)
                    .build(),
            Location.builder()
                    .city("Anchorage")
                    .state("AK")
                    .country("US")
                    .latitude(61.2180556)
                    .longitude(-149.9002778)
                    .build(),
            Location.builder()
                    .city("Erie")
                    .state("PA")
                    .country("US")
                    .latitude(42.1291667)
                    .longitude(-80.0852778)
                    .build(),
            Location.builder()
                    .city("Rockford")
                    .state("MI")
                    .country("US")
                    .latitude(43.12)
                    .longitude(-85.56)
                    .build()
    ));
    private List<Date> encounterDates = new ArrayList<>(List.of(
            Date.builder()
                    .yearSighted(2005)
                    .monthSighted(5)
                    .daySighted(24)
                    .hourSighted(18)
                    .minuteSighted(30)
                    .findValue("200505241830")
                    .build(),
            Date.builder()
                    .yearSighted(2000)
                    .monthSighted(NO_MO_YEAR)
                    .daySighted(NUM_DAYS_MONTH)
                    .hourSighted(21)
                    .minuteSighted(0)
                    .findValue("20001231210")
                    .build(),
            Date.builder()
                    .yearSighted(1984)
                    .monthSighted(1)
                    .daySighted(21)
                    .hourSighted(21)
                    .minuteSighted(0)
                    .findValue("198401212100")
                    .build(),
            Date.builder()
                    .yearSighted(2011)
                    .monthSighted(2)
                    .daySighted(23)
                    .hourSighted(5)
                    .minuteSighted(13)
                    .findValue("20110223513")
                    .build()
    ));

    /**
     * Retrieves all UFO objects
     * @return all UFO objects
     */
    // GET requests (read)
    public List<UFO> allUFOs()
    {
        return ufos;
    }

    /**
     * Retrieves all Location objects
     * @return all Location objects
     */
    public List<Location> allLocations()
    {
        return locations;
    }

    /**
     * Retrieves all Date objects
     * @return all Date objects
     */
    public List<Date> allDates()
    {
        return encounterDates;
    }

    /**
     * Retrieves a UFO object with a given id
     * @param id the ID of the UFO object
     * @return the UFO object with the given id
     */
    public UFO findUFOById(int id){
        return ufos.stream().filter(ufo -> ufo.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves a Location object with a given city
     * @param city the city in the Location object
     * @return the Location with the given city
     */
    public Location findLocByCity(String city){
        return locations.stream().filter(location -> location.getCity().equalsIgnoreCase(city))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves a Date object with a given findValue
     * @param findValue the findValue in the Date object (YYYYMMDDHHMM)
     * @return the Date object with the given findValue
     */
    public Date findDateByValue(String findValue)
    {
        return encounterDates.stream().filter(date -> date.getFindValue().equals(findValue))
                .findFirst()
                .orElse(null);
    }


    /**
     * Creates a new UFO object
     * @param newUFO the UFO object being created
     * @return the newly created UFO object
     */
    // POST requests (create)
    public UFO addUFO(UFO newUFO){

        ufos.add(newUFO);
        return newUFO;
    }

    /**
     * Adds a new UFO object with the given parameters
     * @param id the UFO's id num
     * @param shape the reported shape of the UFO
     * @param description a description of the encounter
     * @param encounterLength the length of the encounter in seconds
     */
    public void addUFO(@RequestParam int id, @RequestParam String shape,
                      @RequestParam String description,
                      @RequestParam double encounterLength)
    {
        UFO ufo = new UFO(id, shape, description, encounterLength);
        ufos.add(ufo);
    }

    /**
     * Creates a new Location object
     * @param newLocation the Location object being created
     * @return the newly created Location object
     */
    public Location addLocation(Location newLocation){

        locations.add(newLocation);
        return newLocation;
    }

    /**
     * Creates a Location object with the given parameters
     * @param city the Location's city
     * @param state the Location's state
     * @param country the Location's country
     * @param latitude the Location's latitude coordinate
     * @param longitude the Location's longitude coordinate
     */
    public void addLocation(@RequestParam String city, @RequestParam String state,
                            @RequestParam String country, @RequestParam double latitude,
                            @RequestParam double longitude)
    {
        Location location = new Location(city, state, country, latitude, longitude);
        locations.add(location);
    }

    /**
     * Creates a new Date object
     * @param encounterDate the encounter date being created
     * @return the newly created Date object
     */
    public Date addEncounterDate(Date encounterDate){

        encounterDates.add(encounterDate);
        return encounterDate;
    }

    /**
     * @param yearSighted the year the encounter took place in
     * @param monthSighted the month the encounter took place in
     * @param daySighted the day the encounter took place on
     * @param hourSighted the hour the encounter took place during
     * @param minuteSighted the minute the encounter took place on
     * @param findValue a String that returns the Date in YYYYMMDDHHMM format
     */
    public void addEncounterDate(@RequestParam int yearSighted, @RequestParam int monthSighted,
                                 @RequestParam int daySighted, @RequestParam int hourSighted,
                                 @RequestParam int minuteSighted, @RequestParam String findValue)
    {
        Date date = new Date(yearSighted, monthSighted, daySighted, hourSighted, minuteSighted, findValue);
        encounterDates.add(date);
    }

    /**
     * Updates a UFO object with new values
     * @param updatedUFO the UFO object with updated values
     * @return the updated UFO object
     */
    // PUT requests (update)
    public UFO updateUFO(UFO updatedUFO)
    {
        UFO found = findUFOById(updatedUFO.getId());
        if (found != null)
        {
            found.setShape(updatedUFO.getShape());
            found.setDescription(updatedUFO.getDescription());
            found.setEncounterLength(updatedUFO.getEncounterLength());
        }
        return updatedUFO;
    }

    /**
     * Updates a Location object with new values
     * @param updatedLocation the Location object with updated values
     * @return the updated Location object
     */
    public Location updateLocation(Location updatedLocation)
    {
        Location found = findLocByCity(updatedLocation.getCity());
        if (found != null)
        {
            found.setCity(updatedLocation.getCity());
            found.setState(updatedLocation.getState());
            found.setCountry(updatedLocation.getCountry());
        }
        return updatedLocation;
    }

    /**
     * Updates a Date object with new values
     * @param updatedDate the Location object with updated values
     * @return the updated Date object
     */
    public Date updateDate(Date updatedDate)
    {
        Date found = findDateByValue(updatedDate.getFindValue());
        if (found != null)
        {
            found.setYearSighted(updatedDate.getYearSighted());
            found.setMonthSighted(updatedDate.getMonthSighted());
            found.setDaySighted(updatedDate.getDaySighted());
            found.setHourSighted(updatedDate.getHourSighted());
            found.setMinuteSighted(updatedDate.getMinuteSighted());
        }
        return updatedDate;
    }

    /**
     * Deletes a UFO object with a given id
     * @param id the id of the UFO object to be deleted
     */
    // DELETE requests (delete)
    public void deleteUFO(int id)
    {
        ufos = new ArrayList<>(
                ufos.stream().
                        filter(ufo -> ufo.getId() != id)
                        .toList()
        );
    }

    /**
     * Deletes a Location object with a given id
     * @param city the city of the Location object to be deleted
     */
    public void deleteLocation(String city)
    {
        locations = new ArrayList<>(
                locations.stream().
                        filter(location -> !location.getCity().equalsIgnoreCase(city))
                        .toList()
        );
    }

    /**
     * Deletes a Date object with a given findValue
     * @param findValue the findValue (YYYYMMDDHHMM) of the Date object to be deleted
     */
    public void deleteEncounterDate(String findValue)
    {
        encounterDates = new ArrayList<>(
                encounterDates.stream()
                        .filter(date -> !date.getFindValue().equals(findValue))
                        .toList()
        );
    }

    /**
     * Returns true if there is a non-zero value for a given UFO object
     * @param ufo the UFO object
     * @return true if the UFO with a given is found
     */
    public boolean isValidUFO(UFO ufo)
    {
        return ufo.getId() != 0;
    }

    /**
     * Returns true if the latitude/longitude coordinates are non-zero
     * @param location the Location object
     * @return true if the Location has coordinates
     */
    public boolean isValidLocation(Location location)
    {
        return location.getLatitude() != 0 && location.getLongitude() !=0;
    }

    /**
     * Returns true if the Day, Month, and Year are possible values
     * @param date the Date object
     * @return true if the Date is within the given parameters
     */
    public boolean isValidDate(Date date)
    {
        return date.getDaySighted() > 0 && date.getDaySighted() <= NUM_DAYS_MONTH
                && date.getMonthSighted() > 0 && date.getMonthSighted() <= NO_MO_YEAR
                && date.getYearSighted() < LATEST_YEAR && date.getYearSighted() > EARLIEST_YEAR;
    }

    @Override
    public String toString()
    {
        return "UFOService{" +
                "ufos=" + ufos +
                ", locations=" + locations +
                ", encounterDates=" + encounterDates +
                '}';
    }
}
