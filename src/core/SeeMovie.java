package core;

import java.util.Date;
import jade.content.AgentAction;

public class SeeMovie implements AgentAction {
	//Para enviarlo en el manager, enviarlo como una action, con id del agente el see movie correspondiente.
	private Date date;
	private Movie movie;
	
	public SeeMovie(Movie mOVIE) {
		super();
		movie = mOVIE;
	}
	
	public SeeMovie(Date movDate, Movie mov) {
		super();
		date = movDate;
		movie = mov;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie mov) {
		movie = mov;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date movDate) {
		date = movDate;
	}
}
