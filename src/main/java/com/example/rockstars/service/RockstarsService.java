package com.example.rockstars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rockstars.model.Song;
import com.example.rockstars.repository.RockstarsRepository;

@Service
@Transactional
public class RockstarsService {
 
	@Autowired
	private RockstarsRepository rockstarsRepository;	
	
	public List<Song> getAllRockstars() {
        return rockstarsRepository.findAll();
    }
 	public void saveRockstar(Song song) {
 		rockstarsRepository.save(song);
	}

 	
	public Song getRockstar(Integer id) {
		return rockstarsRepository.findById(id).get();
	}
	
	public void deleteRockstar(Integer id) {
		rockstarsRepository.deleteById(id);		
	}

	public void batchAuthors(List<Song> songs) {		
		rockstarsRepository.saveAll(songs);
	}		
	
}




