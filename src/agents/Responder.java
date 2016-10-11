package agents;

import jade.core.Agent;
import stateMachines.ResponderStateMachine;

public class Responder extends Agent {
	
	protected void setup()	{
		this.addBehaviour(new ResponderStateMachine());
		System.out.println("El agente Responder "+this.getAID().getName()+" se encuentra activo.");
	}
}