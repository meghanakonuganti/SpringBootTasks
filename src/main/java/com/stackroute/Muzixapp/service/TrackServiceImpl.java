package com.stackroute.Muzixapp.service;

import com.stackroute.Muzixapp.domain.Track;
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
    public Track saveTrack(Track track)  {
        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks()
    {

        return trackRepository.findAll();
    }
    @Override
    public Track getTrackById(int id)
    {
        return trackRepository.findById(id).get();
    }
    @Override
    public void deleteTrack(int id){
        trackRepository.deleteById(id);
    }
    @Override
    public Track updateTrack(Track track){
        return trackRepository.save(track);
    }
}
