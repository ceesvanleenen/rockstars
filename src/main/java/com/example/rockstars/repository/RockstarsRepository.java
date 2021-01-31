package com.example.rockstars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rockstars.model.Song;

public interface RockstarsRepository extends JpaRepository<Song, Integer > {
}



