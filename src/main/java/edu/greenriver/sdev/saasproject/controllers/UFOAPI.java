package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.models.Date;
import edu.greenriver.sdev.saasproject.models.Location;
import edu.greenriver.sdev.saasproject.models.UFO;
import edu.greenriver.sdev.saasproject.services.UFOService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.List;

@RestController
@RequestMapping("ufos")
public class UFOAPI
{
    private UFOService service;

    public UFOAPI(UFOService service)
    {
        this.service = service;
    }

    // GET (READ)

    //http://localhost:8080/ufos
    @GetMapping("")
    public ResponseEntity<List<UFO>> allUFOs()
    {
        return new ResponseEntity<>(service.allUFOs(), HttpStatus.OK);
    }

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

    @GetMapping("location/{latitude}-{longitude}")
    public ResponseEntity<Location>locationByCoordinates(@PathVariable int latitude,
                                          @PathVariable int longitude)
    {
        // if there is no coordinates match - 404
        if(service.findLocByCoordinates(latitude, longitude) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // if there is a coordinates match - 200
        return new ResponseEntity<>(service.findLocByCoordinates(latitude, longitude), HttpStatus.OK);
    }

    @GetMapping("date/{year}-{month}-{day}")
    public ResponseEntity<Date> dateByDate(@PathVariable int year, int month, int day)
    {
        // if there is no date match - 404
        if(service.findDateByDate(year, month, day) != null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // if there is a date match - 200
        return new ResponseEntity<>(service.findDateByDate(year, month, day), HttpStatus.OK);
    }

    // POST (CREATE)
    @PostMapping("add-ufo")
    public ResponseEntity<UFO> addUFO(@RequestBody UFO ufo)
    {
        // if the input is not a valid date - 400
        if(service.isValidUFO(ufo))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // if the input is valid - 201
        return new ResponseEntity<>(service.addUFO(ufo), HttpStatus.CREATED);
    }

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

    // PUT (UPDATE)
    @PutMapping("")
    public ResponseEntity<UFO> updateUFO(@RequestBody UFO updatedUFO)
    {
        if(service.findUFOById(updatedUFO.getId()) == null)
        {
            // if the id cannot be found - 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if (!service.isValidUFO(updatedUFO))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // if the id is found - 200
        return new ResponseEntity<>(service.updateUFO(updatedUFO),HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Location> updateLocation(@RequestBody Location updatedLoc)
    {
        // if you can't find the location by its coordinates
        if(service.findLocByCoordinates(updatedLoc.getLatitude(), updatedLoc.getLongitude()) == null)
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

    @PutMapping("")
    public ResponseEntity<Date> updateDate(@RequestBody Date updatedDate)
    {
        // if you can't find the date by the day/mo/year
        if(service.findDateByDate(updatedDate.getDaySighted(), updatedDate.getMonthSighted(),
                updatedDate.getYearSighted()) == null)
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
    @DeleteMapping("")
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

    public ResponseEntity<Location> deleteLocation(@PathVariable double latitude,
                               @PathVariable double longitude)
    {
        // if the location coordinates cannot be found - 404
        if(service.findLocByCoordinates(latitude,longitude) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // if it was found
        // delete the given location and return - 200
        service.deleteLocation(latitude, longitude);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Date> deleteDate(@PathVariable int year,
                           @PathVariable int month,
                           @PathVariable int day)
    {
    // if the location coordinates cannot be found - 404
        if(service.findDateByDate(year, month, day) == null)
    {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
        // if it was found
        // delete the given date and return - 200
        service.deleteEncounterDate(year, month, day);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
