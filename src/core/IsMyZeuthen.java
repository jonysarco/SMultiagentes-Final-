package core;

import jade.content.Predicate;

public class IsMyZeuthen implements Predicate{

	private Float value;
	
	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public IsMyZeuthen() {
		super();
	}

}
