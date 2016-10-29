package core;

import java.util.Date;

import jade.content.Concept;

public class Movie implements Concept{
	
	private String name;
	private Date year;
	private String director;
	private String actors;
	
	public Movie(String MOVIE_NAME) {
		super();
		this.name = MOVIE_NAME;
		director="";
		actors="";
	}	
	
	public String getMOVIE_NAME() {
		return name;
	}
	public void setMOVIE_NAME(String mOVIE_NAME) {
		name = mOVIE_NAME;
	}
	public Date getMOVIE_YEAR() {
		return year;
	}
	public void setMOVIE_YEAR(Date mOVIE_YEAR) {
		year = mOVIE_YEAR;
	}
	public String getMOVIE_DIRECTOR() {
		return director;
	}
	public void setMOVIE_DIRECTOR(String mOVIE_DIRECTOR) {
		director = mOVIE_DIRECTOR;
	}
	public String getMOVIE_ACTORS() {
		return actors;
	}
	public void setMOVIE_ACTORS(String mOVIE_ACTORS) {
		actors = mOVIE_ACTORS;
	}

}