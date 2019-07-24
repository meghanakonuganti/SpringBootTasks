package com.stackroute.Muzixapp.repository;

import com.stackroute.Muzixapp.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {
}
