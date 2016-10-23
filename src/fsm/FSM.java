package fsm;

import java.util.Vector;

import behaviors.BehaviourEvaluatePropose;
import behaviors.BehaviourFinalState;
import behaviors.BehaviourReceiveZeuthen;
import behaviors.BehaviourSendPropose;
import behaviors.BehaviourSendResponse;
import behaviors.BehaviourSendZeuthen;
import behaviors.BehaviourStartPropose;
import behaviors.BehaviourWaitPropose;
import behaviors.BehaviourWaitProposeStart;
import behaviors.BehaviourWaitResponse;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.FSMBehaviour;
import agents.PeliVal;

public class FSM extends FSMBehaviour	{
	
	public FSM(Vector<PeliVal> mov, String tipoAgente, String responder)	{
		
		//Defino Data Store
		DataStore ds = new DataStore();

				
		//Defino los comportamientos de la maquina de estados.
		
		//Nuevos estados creados TIMOTEO
		BehaviourWaitProposeStart bwps = new BehaviourWaitProposeStart();
		bwps.setDataStore(ds);
		BehaviourStartPropose bstp = new BehaviourStartPropose(mov, responder);
		bstp.setDataStore(ds);
		
		
		BehaviourEvaluatePropose bep = new BehaviourEvaluatePropose();
		bep.setDataStore(ds);
		BehaviourFinalState bfs = new BehaviourFinalState();
		bfs.setDataStore(ds);
		BehaviourReceiveZeuthen brz = new BehaviourReceiveZeuthen();
		brz.setDataStore(ds);
		BehaviourSendPropose bsp = new BehaviourSendPropose();
		bsp.setDataStore(ds);
		BehaviourSendResponse bsr = new BehaviourSendResponse();
		bsr.setDataStore(ds);
		BehaviourSendZeuthen bsz = new BehaviourSendZeuthen();
		bsz.setDataStore(ds);
		BehaviourWaitPropose bwp = new BehaviourWaitPropose();
		bwp.setDataStore(ds);
		BehaviourWaitResponse bwr = new BehaviourWaitResponse();
		bwr.setDataStore(ds);

		
		//Seteo los estados de comienzo de los agentes, dependiendo del tipo que es. TIMOTEO
		if(tipoAgente.equals("Responder")){
			//Estado de comienzo del agente Responder
			this.registerFirstState(bwps, "StartResponder");
		}
		else if(tipoAgente.equals("Initiator")){
			//Estado de comienzo del agente Initiator
			this.registerFirstState(bstp, "StartInitiator");
		}
		
		
		//Agrego los diferentes estados a la FSM.
		this.registerFirstState( bsp , "Send Propose");
		this.registerState(bwr, "Wait Response");
		this.registerState(bsz,"Send Zeuthen");
		this.registerState(brz, "Receive Zeuthen");
		this.registerState(bwp, "Wait Propose");
		this.registerState(bsr, "Send Response");
		this.registerState(bfs, "Final State");

		//Defino transicion de estados.
		this.registerTransition("Send Propose", "Wait Response", 0);
		this.registerTransition("Send Propose", "Final State", 1);
		this.registerTransition("Wait Response", "Final State", 2);
		this.registerTransition("Wait Response", "Send Zeuthen", 3);
		this.registerTransition("Send Zeuthen", "Receive Zeuthen", 4);
		this.registerTransition("Receive Zeuthen", "Send Propose", 5);
		this.registerTransition("Receive Zeuthen", "Wait Propose", 6);
		this.registerTransition("Wait Propose", "Send Response", 7);
		this.registerTransition("Send Response", "Send Zeuthen", 8);
		this.registerTransition("Send Response", "Final State", 9);
		
	}
}
