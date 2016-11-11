package core;

import java.util.Date;

import jade.content.Concept;

public class Movie implements Concept{
	
	private String name;
	private Date year;
	private String director;
	private String actors;
	
	public Movie(String movName) {
		super();
		this.name = movName;
		director = "";
		actors = "";
	}	
	
	public String getName() {
		return name;
	}
	
	public void setMovie(String movName) {
		name = movName;
	}
	
	public Date getYear() {
		return year;
	}
	
	public void setYear(Date movYear) {
		year = movYear;
	}
	
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String movDirector) {
		director = movDirector;
	}
	
	public String getActors() {
		return actors;
	}
	
	public void setActors(String movActors) {
		actors = movActors;
	}
}