package com.example.rockstars.controller;

import java.util.ArrayList;
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

	/**
	 * Methode getAllRockstars() haalt alle rockstars op uit de database
	 *  
	 * @return List<Song> een lijst van Song records.
	 */
	@GetMapping("")
	public List<Song> getAllRockstars() {
		return rockstarsService.getAllRockstars();
	}
	
	/**
	 * Methode getRockstarById haalt, o.b.v. een id, één Song record op.
	 * 
	 * @param id Integer id 
	 * @return ResponseEntity<Song> Response object van het type <Song>
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Song> getRockstarById(@PathVariable Integer id) {
		try {
			Song song = rockstarsService.getRockstar(id);
			return new ResponseEntity<Song>(song, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Methode getGenre haalt o.b.v. het type genre, en lijst op van Song objecten die hier aan voldoen.
	 * 
	 * @param genre String genre
	 * @return List<Song> een lijst van Song records.
	 */
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

	/**
	 * Methode getSongsBefore2016() haalt een lijst op van songs die vóór 2016 uitgebracht zijn.
	 * 
	 * @return List<Song> een lijst van Song records.
	 */
	
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

	/**
	 * Methode addRockstar() voegt een Song object toe aan de database.
	 * Mocht de naam van de band al bestaan, dan verschijnt er een melding in de log en wordt het record niet opgeslagen.
	 * 
	 * @param song Song het op te slagen record.
	 */
	@PostMapping("/")
	public void addRockstar(@RequestBody Song song) {
 
		boolean present = false;
		
		for(Song songFromList : rockstarsService.getAllRockstars()) {
			if (song.getName().equalsIgnoreCase(songFromList.getName())) {
				System.out.println("De naam van deze band bestaat al. Het record wordt niet opgeslagen.");
				present = true;
			} 
		}		

		if(!present) {
			rockstarsService.saveRockstar(song);						
		}
	}

	/**
	 * Methode addRockstarList slaat een lijst op van Songs.
	 * 
	 * @param songs List<Song> lijst van songs.
	 */
	@PostMapping("/addList")
	public void addRockstarList(@RequestBody List<Song> songs) {
		rockstarsService.addCompleteListOfSongs(songs);		
	}

	/**
	 * Methode updateRockstar() wijzigt een Song o.b.v. het id.
	 * 
	 * @param song Song te wijzigen record
	 * @param id Integer id
	 * @return ResponseEntity<Song>
	 */
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

	/**
	 * Methode deleteRockstar() verwijdert een record o.b.v. het id.	 * 
	 * @param id Integer id
	 */
	@DeleteMapping("/{id}")
	public void deleteRockstar(@PathVariable Integer id) {
		rockstarsService.deleteRockstar(id);
	}
	
}
