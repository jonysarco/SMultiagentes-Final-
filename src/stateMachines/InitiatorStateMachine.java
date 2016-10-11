package stateMachines;
import java.util.Vector;

import behaviors.BehaviourInitiatorFinalState;
import behaviors.BehaviourInitiatorPropose;
import behaviors.BehaviourInitiatorWait;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.FSMBehaviour;

public class InitiatorStateMachine extends FSMBehaviour{
	
	public InitiatorStateMachine(Vector<String> mov)	{
		DataStore dataStore = new DataStore();
		
		//Definicion de estados
		BehaviourInitiatorPropose propose = new BehaviourInitiatorPropose(mov);
		propose.setDataStore(dataStore);
		
		BehaviourInitiatorWait waitResponse = new BehaviourInitiatorWait();
		waitResponse.setDataStore(dataStore);

		BehaviourInitiatorFinalState finalState = new BehaviourInitiatorFinalState();
		finalState.setDataStore(dataStore);
		
		//Registrar estados
		this.registerFirstState(propose, "Propose");
		this.registerState(waitResponse, "Waiting");
		this.registerLastState(finalState, "Final");
		
		//Definir transiciones
		this.registerTransition("Propose", "Waiting", 0);
		this.registerTransition("Propose", "Final", 1);
		
		this.registerTransition("Waiting", "Propose", 0);
		this.registerTransition("Waiting", "Final", 1);
	}
	
}
