package agents;

import fsm.FSM;
import jade.core.Agent;

public class Responder extends Agent {
	
	protected void setup()	{
		this.addBehaviour(new FSM());
		System.out.println("El agente Responder "+this.getAID().getName()+" se encuentra activo.");
	}
}