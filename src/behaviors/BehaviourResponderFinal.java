package behaviors;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourResponderFinal extends Behaviour {

	private boolean end;
	private static final Integer Key = 1;
	 
	@Override
	public void action() {
		ACLMessage answer = (ACLMessage) getDataStore().get(Key);
		System.out.println("El agente "+ myAgent.getLocalName() +" acepto la película: "+ answer.getContent());
		end = true;
	}
	
	protected void takeDown() {
        System.out.println( "El agente "+myAgent.getLocalName()+" termino de ejecutarse.");
    }

	@Override
	public boolean done() {
		if(end == true)
			myAgent.doDelete();
		return end;
	}
}
