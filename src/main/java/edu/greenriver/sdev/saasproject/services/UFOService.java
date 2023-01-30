package edu.greenriver.sdev.saasproject.services;

import edu.greenriver.sdev.saasproject.models.Date;
import edu.greenriver.sdev.saasproject.models.Location;
import edu.greenriver.sdev.saasproject.models.UFO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class UFOService
{
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
                    .monthSighted(12)
                    .daySighted(31)
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

    // GET requests (read)
    public List<UFO> allUFOs()
    {
        return ufos;
    }

    public List<Location> allLocations()
    {
        return locations;
    }

    public List<Date> allDates()
    {
        return encounterDates;
    }
    public List<Location> recordedLocations(){
        return locations;
    }
    public List<Date> encounterDates(){
        return encounterDates;
    }

    public UFO findUFOById(int id){
        return ufos.stream().filter(ufo -> ufo.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Location findLocByCity(String city){
        return locations.stream().filter(location -> location.getCity().equalsIgnoreCase(city))
                .findFirst()
                .orElse(null);
    }

    public Date findDateByValue(String findValue)
    {
        return encounterDates.stream().filter(date -> date.getFindValue().equals(findValue))
                .findFirst()
                .orElse(null);
    }


    // POST requests (create)
    public UFO addUFO(UFO newUFO){

        ufos.add(newUFO);
        return newUFO;
    }

    public void addUFO(@RequestParam int id, @RequestParam String shape,
                      @RequestParam String description,
                      @RequestParam double encounterLength)
    {
        UFO ufo = new UFO(id, shape, description, encounterLength);
        ufos.add(ufo);
    }

    public Location addLocation(Location newLocation){

        locations.add(newLocation);
        return newLocation;
    }

    public void addLocation(@RequestParam String city, @RequestParam String state,
                            @RequestParam String country, @RequestParam double latitude,
                            @RequestParam double longitude)
    {
        Location location = new Location(city, state, country, latitude, longitude);
        locations.add(location);
    }

    public Date addEncounterDate(Date encounterDate){

        encounterDates.add(encounterDate);
        return encounterDate;
    }

    public void addEncounterDate(@RequestParam int yearSighted, @RequestParam int monthSighted,
                                 @RequestParam int daySighted, @RequestParam int hourSighted,
                                 @RequestParam int minuteSighted, @RequestParam String findValue)
    {
        Date date = new Date(yearSighted, monthSighted, daySighted, hourSighted, minuteSighted, findValue);
        encounterDates.add(date);
    }

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

    // DELETE requests (delete)
    public void deleteUFO(int id)
    {
        ufos = new ArrayList<>(
                ufos.stream().
                        filter(ufo -> ufo.getId() != id)
                        .toList()
        );
    }

    public void deleteLocation(String city)
    {
        locations = new ArrayList<>(
                locations.stream().
                        filter(location -> !location.getCity().equalsIgnoreCase(city))
                        .toList()
        );
    }

    public void deleteEncounterDate(String findValue)
    {
        encounterDates = new ArrayList<>(
                encounterDates.stream()
                        .filter(date -> !date.getFindValue().equals(findValue))
                        .toList()
        );
    }

    public boolean isValidUFO(UFO ufo)
    {
        return ufo.getId() != 0;
    }

    public boolean isValidLocation(Location location)
    {
        return location.getLatitude() != 0 && location.getLongitude() !=0;
    }

    public boolean isValidDate(Date date)
    {
        return date.getDaySighted() > 0 && date.getDaySighted() < 32
                && date.getMonthSighted() > 0 && date.getMonthSighted() < 13
                && date.getYearSighted() < 2023 && date.getYearSighted() > 1900;
    }

}
