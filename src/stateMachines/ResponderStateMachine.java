package stateMachines;

import behaviors.BehaviourResponderConfirm;
import behaviors.BehaviourResponderFinal;
import behaviors.BehaviourResponderWait;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.FSMBehaviour;

public class ResponderStateMachine extends FSMBehaviour {

		
	DataStore dataStateResponder = new DataStore();	
	
	public ResponderStateMachine() {
		super();
		
	    BehaviourResponderWait waitResponder = new BehaviourResponderWait();
		waitResponder.setDataStore(dataStateResponder);
			
		BehaviourResponderConfirm confirmResponder = new BehaviourResponderConfirm();
		confirmResponder.setDataStore(dataStateResponder);
		
		BehaviourResponderFinal finalResponder = new BehaviourResponderFinal();
		finalResponder.setDataStore(dataStateResponder);
		
		//Registrar Estados
		this.registerFirstState(waitResponder,"waitResp");
		this.registerState(confirmResponder, "confirmResp");
		this.registerState(finalResponder, "finalResp");
		
		this.registerTransition("waitResp", "confirmResp",0 ); 	//Voy al estado siguiente para analizar la pelicula  
		this.registerTransition("waitResp", "waitResp",1 ); 	//Me quedo esperando que llegue un mensaje
		this.registerTransition("waitResp", "finalResp", 2);	//Voy a estado final por rechazo
				
		this.registerTransition("confirmResp", "finalResp",1); 	//Voy a estado final
		this.registerTransition("confirmResp", "waitResp",0 ); 	//Vuelvo a esperar otra propuesta
		
		this.registerDefaultTransition("waitResp", "confirmResp");
	}
}

