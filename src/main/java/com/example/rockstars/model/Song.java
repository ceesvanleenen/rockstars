package com.example.rockstars.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
 
@Entity
@Table(name = "song")
public class Song {

	private int id;
	private String name;
	private int year;
	private String artist;
	private String shortname;
	private int bpm;
	private int duration;
	private String genre;
	private String spotifyId;	
	private String album;

	public Song() {	
	}

	public Song(int id, String name, int year, String artist, String shortname, int bpm, int duration, String genre, String spotifyId, String album ) {	
		this.id = id;
		this.name = name;
		this.year = year;
		this.artist = artist;
		this.shortname = shortname;
		this.bpm = bpm;
		this.duration = duration;
		this.genre = genre;
		this.spotifyId = spotifyId;
		this.album = album;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	@JoinColumn(name="id")
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@JoinColumn
	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	@JoinColumn(name="bpm")
	public int getBpm() {
		return bpm;
	}

	public void setBpm(int bpm) {
		this.bpm = bpm;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSpotifyId() {
		return spotifyId;
	}

	public void setSpotifyId(String spotifyId) {
		this.spotifyId = spotifyId;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}
	
}