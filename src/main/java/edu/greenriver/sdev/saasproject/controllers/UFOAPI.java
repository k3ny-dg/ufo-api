package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.models.Date;
import edu.greenriver.sdev.saasproject.models.Location;
import edu.greenriver.sdev.saasproject.models.UFO;
import edu.greenriver.sdev.saasproject.services.UFOService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A controller for the UFOService
 * @author Keny Dutton-Gillespie
 * @version 1.0
 */
@RestController
@RequestMapping("ufos")
public class UFOAPI
{
    private UFOService service;

    /**
     * Constructor for the UFO service
     * @param service the SpringBoot service
     */
    public UFOAPI(UFOService service)
    {
        this.service = service;
    }

    // GET (READ)

    /**
     * Retrieves a list of all UFO objects
     * @return all created UFOs
     */
    //http://localhost:8080/ufos
    @GetMapping("/all")
    public ResponseEntity<List<UFO>> allUFOs()
    {
        return new ResponseEntity<>(service.allUFOs(), HttpStatus.OK);
    }

    /**
     * Retrieves a list of all Location objects
     * @return all Location objects
     */
    //http://localhost:8080/ufos/locations
    @GetMapping("/locations")
    public ResponseEntity<List<Location>> allLoc()
    {
        return new ResponseEntity<>(service.allLocations(), HttpStatus.OK);
    }


    /**
     * Retrieves all created Date objects
     * @return all Date objects
     */
    //http://localhost:8080/ufos/encounters
    @GetMapping("/encounters")
    public ResponseEntity<List<Date>> allDates()
    {
        return new ResponseEntity<>(service.allDates(), HttpStatus.OK);
    }


    /**
     * Retrieves the UFO object with a given id
     * @param id the UFO id
     * @return the UFO object determined by its id
     */
    //http://localhost:8080/ufos/{id}
    @GetMapping("{id}")
    public ResponseEntity<UFO> getUfoById(@PathVariable int id)
    {
        // if the id doesn't exist - 404
        if(service.findUFOById(id) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // if the id does exist - 200
        return new ResponseEntity<>(service.findUFOById(id), HttpStatus.OK);
    }


    /**
     * Retrieves a Location object with a given city
     * @param city the city parameter in the Location object
     * @return the Location object determined by the city
     */
    //http://localhost:8080/ufos/locations/{city}
    @GetMapping("locations/{city}")
    public ResponseEntity<Location>locationByCity(@PathVariable String city)
    {
        // if there is no coordinates match - 404
        if(service.findLocByCity(city) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // if there is a coordinates match - 200
        return new ResponseEntity<>(service.findLocByCity(city), HttpStatus.OK);
    }

    /**
     * Retrieves a Date object, given its findValue
     * @param findValue the full date object constructed as (YYMMDDHHMM)
     * @return the Date object determined by its findValue
     */
    //http://localhost:8080/ufos/encounters/{findValue}
    @GetMapping("encounters/{findValue}")
    public ResponseEntity<Date> dateByDate(@PathVariable String findValue)
    {
         // if there is no date match - 404
        if(service.findDateByValue(findValue) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // if there is a date match - 200
        return new ResponseEntity<>(service.findDateByValue(findValue), HttpStatus.OK);
    }

    /**
     * Adds a new UFO object and gives a response entity
     * to report if the request was completed
     * @param ufo the new UFO object
     * @return the response entity and the created object, if successful
     */
    // POST (CREATE)
    //http://localhost:8080/ufos/add-ufo
    @PostMapping("add-ufo")
    public ResponseEntity<UFO> addUFO(@RequestBody UFO ufo)
    {
        // if the input is not a valid date - 400
        if(!service.isValidUFO(ufo))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // if the input is valid - 201
        return new ResponseEntity<>(service.addUFO(ufo), HttpStatus.CREATED);
    }

    /**
     * Adds a new Location object and gives a response entity
     * to report if the request was completed
     * @param location the new Location object
     * @return the response entity and the created object, if successful
     */
    //http://localhost:8080/ufos/add-loc
    @PostMapping("add-loc")
    public ResponseEntity<Location> addLocation(@RequestBody Location location)
    {
        // if the input is not a valid date - 400
        if(!service.isValidLocation(location))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // if the input is valid - 201
        return new ResponseEntity<>(service.addLocation(location), HttpStatus.CREATED);
    }


    /**
     * Adds a new Date object and gives a response entity
     * to report if the request was completed
     * @param date the new Date object
     * @return the response entity and the created object, if successful
     */
    //http://localhost:8080/ufos/add-date
    @PostMapping("add-date")
    public ResponseEntity<Date> addDate(@RequestBody Date date)
    {
        // if the input is not a valid date - 400
        if(!service.isValidDate(date))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // if the input is valid - 201
        return new ResponseEntity<>(service.addEncounterDate(date), HttpStatus.CREATED);
    }

    /**
     * Updates a new UFO object and gives a response entity
     * to report if the request was successful
     * @param updatedUFO the UFO object being updated
     * @return the response entity and the updated object, if successful
     */
    //http://localhost:8080/ufos/update-ufo
    // PUT (UPDATE)
    @PutMapping("update-ufo")
    public ResponseEntity<UFO> updateUFO(@RequestBody UFO updatedUFO)
    {
        if(service.findUFOById(updatedUFO.getId()) == null)
        {
            // if the id cannot be found - 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // if the id is found - 200
        return new ResponseEntity<>(service.updateUFO(updatedUFO),HttpStatus.OK);
    }


    /**
     * Updates a Location object and gives a response entity
     * to report if the request was successful
     * @param updatedLoc the Location object being updated
     * @return the response entity and the updated object, if successful
     */
    //http://localhost:8080/ufos/update-loc
    @PutMapping("update-loc")
    public ResponseEntity<Location> updateLocation(@RequestBody Location updatedLoc)
    {
        // if you can't find the location by its coordinates
        if(service.findLocByCity(updatedLoc.getCity()) == null)
        {
            // return - 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // if the updated location is not valid
        else if (!service.isValidLocation(updatedLoc))
        {
            // return - 400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // if it is found, return - 200
        return new ResponseEntity<>(service.updateLocation(updatedLoc), HttpStatus.OK);
    }


    /**
     * Updates a Date object and gives a response entity
     * to report if the request was successful
     * @param updatedDate the Date object being updated
     * @return the response entity and the updated object, if successful
     */
    //http://localhost:8080/ufos/update-date
    @PutMapping("update-date")
    public ResponseEntity<Date> updateDate(@RequestBody Date updatedDate)
    {
        // if you can't find the date by the day/mo/year
        if(service.findDateByValue(updatedDate.getFindValue()) == null)
        {
            // return - 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //if the updated date is not valid
        else if (!service.isValidDate(updatedDate))
        {
            //return - 400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // if it is found and valid, return - 200
        return new ResponseEntity<>(service.updateDate(updatedDate), HttpStatus.OK);
    }

    // DELETE (DELETE)

    /**
     * Deletes a UFO object, given an id
     * and gives a response entity
     * to report if the request was successful
     * @param ufoId the id of the UFO object being deleted
     * @return the response entity if the request was successful
     */
    //http://localhost:8080/ufos/del/{ufoId}
    @DeleteMapping("/del/{ufoId}")
    public ResponseEntity<Integer> deleteUFO(@PathVariable int ufoId)
    {
        if(service.findUFOById(ufoId) == null)
        {
            // if the id cannot be found - 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // if it was found
        // delete the ufo object and return - 200
        service.deleteUFO(ufoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes a Location object, given a city
     * and gives a response entity
     * to report if the request was successful
     * @param city the city of the Location object being deleted
     * @return the response entity if the request was successful
     */
    //http://localhost:8080/ufos/del-loc/{city}
    @DeleteMapping("/del-loc/{city}")
    public ResponseEntity<Location> deleteLocation(@PathVariable String city)
    {
        // if the location coordinates cannot be found - 404
        if(service.findLocByCity(city) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // if it was found
        // delete the given location and return - 200
        service.deleteLocation(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes a Date object, given a findValue
     * and gives a response entity
     * to report if the request was successful
     * @param findValue the findValue of the Date object being deleted
     * @return the response entity if the request was successful
     */
    @DeleteMapping("del-date/{findValue}")
    public ResponseEntity<Date> deleteDate(@PathVariable String findValue)
    {
    // if the location coordinates cannot be found - 404
        if(service.findDateByValue(findValue) == null)
    {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
        // if it was found
        // delete the given date and return - 200
        service.deleteEncounterDate(findValue);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    public String toString()
    {
        return "UFOAPI{" +
                "service=" + service +
                '}';
    }
}
