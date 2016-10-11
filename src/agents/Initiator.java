package agents;
import java.util.Vector;

import jade.core.Agent;
import stateMachines.InitiatorStateMachine;

public class Initiator extends Agent{
	
	private Vector<String> movies;
	
	protected void setup()	{
		movies = new Vector<>();
		//Agrego peliculas al vector
		movies.add("Rambo 1");
		movies.add("Rambo 2");
		movies.add("La historia sin fin");
		movies.add("Matrix");
		movies.add("Titanic");
		movies.add("Lo que el viento se llevo");
		
		//Agrego comportamiento al agente
		addBehaviour(new InitiatorStateMachine(this.movies));
		System.out.println("El agente"+this.getAID().getName()+" se encuentra activo");
	}
}
