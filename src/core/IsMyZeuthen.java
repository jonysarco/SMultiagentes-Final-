package core;

import jade.content.Predicate;

public class IsMyZeuthen implements Predicate{

	private Movie movie;
	
	public IsMyZeuthen() {
		super();
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie mov) {
		this.movie = mov;
	}
}
