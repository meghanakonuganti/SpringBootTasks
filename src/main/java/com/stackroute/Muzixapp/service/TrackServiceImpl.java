package com.stackroute.Muzixapp.service;

import com.stackroute.Muzixapp.domain.Track;
import com.stackroute.Muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.Muzixapp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrackServiceImpl implements TrackService{
    TrackRepository trackRepository;
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository=trackRepository;
    }
    @Override
    public Track saveTrack(Track track)  throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getTrackId())){ ;
            throw new TrackAlreadyExistsException("track already exists");
        }
        Track savedTrack = trackRepository.save(track);
        if(savedTrack==null){
            throw new TrackAlreadyExistsException("Track already exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks()
    {
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException
    {
        if(!trackRepository.findById(id).isPresent()) {
            throw new TrackNotFoundException("Does not exist");
        }
        return trackRepository.getOne(id);
    }

    @Override
    public void deleteTrack(int id){
        trackRepository.deleteById(id);
    }
    @Override
    public Track updateTrack(Track track){
        return trackRepository.save(track);
    }

    @Override
    public List<Track> findByName(String name) {
        return trackRepository.findByName(name);
    }

}
