package core;

import jade.content.Predicate;

public class IsMyZeuthen implements Predicate{

<<<<<<< HEAD
	private Movie movie;
	
	public IsMyZeuthen() {
		super();
	}
=======
	private float value;

	/*public IsMyZeuthen(float value) {
		super();
		this.value = value;
	}*/
>>>>>>> eb77d9178e9c5499cf24590ba55bfdb61e3e07c6

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie mov) {
		this.movie = mov;
	}
}
