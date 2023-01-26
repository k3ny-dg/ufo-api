package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.models.Date;
import edu.greenriver.sdev.saasproject.models.Location;
import edu.greenriver.sdev.saasproject.models.UFO;
import edu.greenriver.sdev.saasproject.services.UFOService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<UFO> allUFOs()
    {
        return service.allUFOs();
    }

    @GetMapping("{id}")
    public UFO ufoById(@PathVariable int id)
    {
        return service.findUFOById(id);
    }

    @GetMapping("location/{latitude}-{longitude}")
    public Location locationByCoordinates(@PathVariable int latitude,
                                          @PathVariable int longitude)
    {
        return service.findLocByCoordinates(latitude, longitude);
    }

    @GetMapping("date/{year}-{month}-{day}")
    public Date dateByDate(@PathVariable int year, int month, int day)
    {
        return service.findDateByDate(year, month, day);
    }

    // POST (CREATE)
    @PostMapping("add-ufo")
    public ResponseEntity<UFO> addUFO(@RequestBody UFO ufo)
    {
        return new ResponseEntity<>(service.addUFO(ufo), HttpStatus.CREATED);
    }

    @PostMapping("add-loc")
    public ResponseEntity<Location> addLocation(@RequestBody Location location)
    {
        return new ResponseEntity<>(service.addLocation(location), HttpStatus.CREATED);
    }

    @PostMapping("add-date")
    public ResponseEntity<Date> addDate(@RequestBody Date date)
    {
        return new ResponseEntity<>(service.addEncounterDate(date), HttpStatus.CREATED);
    }

    // PUT (UPDATE)
    @PutMapping("")
    public void updateUFO(@RequestBody UFO updatedUFO)
    {
        service.updateUFO(updatedUFO);
    }

    @PutMapping("")
    public void updateLocation(@RequestBody Location updatedLoc)
    {
        service.updateLocation(updatedLoc);
    }

    @PutMapping("")
    public void updateDate(@RequestBody Date updatedDate)
    {
        service.updateDate(updatedDate);
    }

    // DELETE (DELETE)
    @DeleteMapping("")
    public void deleteUFO(@PathVariable int ufoId)
    {
        service.deleteUFO(ufoId);
    }

    public void deleteLocation(@PathVariable double latitude,
                               @PathVariable double longitude)
    {
        service.deleteLocation(latitude, longitude);
    }

    public void deleteDate(@PathVariable int year,
                           @PathVariable int month,
                           @PathVariable int day)
    {
        service.deleteEncounterDate(year, month, day);
    }


}
