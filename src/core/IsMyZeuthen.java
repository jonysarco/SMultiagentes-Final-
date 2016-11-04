package core;

import jade.content.Predicate;

public class IsMyZeuthen implements Predicate{

	private float value;

	public IsMyZeuthen(float value) {
		super();
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	
}
