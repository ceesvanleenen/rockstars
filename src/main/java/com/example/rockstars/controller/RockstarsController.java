package com.example.rockstars.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rockstars.model.Song;
import com.example.rockstars.service.RockstarsService;

@RestController
@RequestMapping("/rockstars")
public class RockstarsController {

	@Autowired
	RockstarsService rockstarsService;

	@GetMapping("")
	public List<Song> getAllRockstars() {
		return rockstarsService.getAllRockstars();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Song> getRockstarById(@PathVariable Integer id) {
		try {
			Song song = rockstarsService.getRockstar(id);
			return new ResponseEntity<Song>(song, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/genre/{genre}")
	public List<Song> getGenre(@PathVariable String genre) {
		List<Song> newList = new ArrayList<Song>();
		
		for(Song song : rockstarsService.getAllRockstars()) {
			if (song.getGenre().equalsIgnoreCase(genre)) {
				newList.add(song);
			}
		}		
		return newList; 
	}

	@GetMapping("/before2016")
	public List<Song> getSongsBefore2016() {
		List<Song> newList = new ArrayList<Song>();
		
		for(Song song : rockstarsService.getAllRockstars()) {
			if (song.getYear() < 2016) {
				newList.add(song);
			}
		}		
		return newList; 
	}

	@PostMapping("/")
	public void addRockstar(@RequestBody Song song) {
		List<Song> currentList = rockstarsService.getAllRockstars();

		for(Song songFromList : currentList) {
			if (song.getName().equalsIgnoreCase(songFromList.getName())) {
				System.out.println("The name of this band already exist");
			} else {
				rockstarsService.saveRockstar(song);						
			}
		}		
	}

	@PostMapping("/addList")
	public void addRockstarList(@RequestBody List<Song> songs) {
		rockstarsService.batchAuthors(songs);		
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateRockstar(@RequestBody Song song, @PathVariable Integer id) {
		try {
			@SuppressWarnings("unused")
			Song newSong = rockstarsService.getRockstar(id);
			song.setId(id);
			rockstarsService.saveRockstar(song);
			return new ResponseEntity<Song>(song, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public void deleteRockstar(@PathVariable Integer id) {
		rockstarsService.deleteRockstar(id);
	}
	
}
