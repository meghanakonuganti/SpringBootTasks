package com.stackroute.Muzixapp.controller;

import com.stackroute.Muzixapp.domain.Track;
import com.stackroute.Muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.Muzixapp.service.TrackService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v2")
public class TrackController {
     private TrackService trackService;
    @Autowired
    public TrackController(TrackService trackService)
    {
        this.trackService=trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException{
        ResponseEntity responseEntity;
        try{
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        }catch(TrackAlreadyExistsException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("tracks")
    public ResponseEntity<?> getTracks(){
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable(value="id") int id) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        try{
            trackService.getTrackById(id);
            responseEntity=new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        }catch(TrackNotFoundException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.OK);
        }
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable(value="id") int id){
        trackService.deleteTrack(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("track")
    public ResponseEntity<Track> updateTrack(@RequestBody Track track){
        trackService.updateTrack(track);
        return new ResponseEntity<Track>(track,HttpStatus.OK);
    }

}
