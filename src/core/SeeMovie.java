package core;

import java.util.Date;

import jade.content.AgentAction;

public class SeeMovie implements AgentAction {
	private Date date;
	private Movie movie;
	
	public SeeMovie(Movie mOVIE) {
		super();
		movie = mOVIE;
	}
	public SeeMovie(Date mOVIE_DATE, Movie mOVIE) {
		super();
		date = mOVIE_DATE;
		movie = mOVIE;
	}
	public Movie getMOVIE() {
		return movie;
	}
	public void setMOVIE(Movie mOVIE) {
		movie = mOVIE;
	}
	public Date getMOVIE_DATE() {
		return date;
	}
	public void setMOVIE_DATE(Date mOVIE_DATE) {
		date = mOVIE_DATE;
	}
}
