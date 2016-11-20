package fsm;

import java.util.Vector;

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
		
		BehaviourFinalState bfs = new BehaviourFinalState();
		bfs.setDataStore(ds);
		BehaviourReceiveZeuthen brz = new BehaviourReceiveZeuthen();
		brz.setDataStore(ds);
		BehaviourSendPropose bsp = new BehaviourSendPropose(mov);
		bsp.setDataStore(ds);
		BehaviourSendResponse bsr = new BehaviourSendResponse(mov);
		bsr.setDataStore(ds);
		BehaviourSendZeuthen bsz = new BehaviourSendZeuthen(mov);
		bsz.setDataStore(ds);
		BehaviourWaitPropose bwp = new BehaviourWaitPropose();
		bwp.setDataStore(ds);
		BehaviourWaitResponse bwr = new BehaviourWaitResponse();
		bwr.setDataStore(ds);

		
		//Seteo los estados de comienzo de los agentes, dependiendo del tipo que es. TIMOTEO
		if(tipoAgente.equals("Responder")){
			
			//Estado de comienzo del agente Responder
			this.registerFirstState(bwps, "Start Responder");
		}
		else if(tipoAgente.equals("Initiator")){
			//Estado de comienzo del agente Initiator
			this.registerFirstState(bstp, "Start Initiator");
		}
		
		
		//Agrego los diferentes estados a la FSM.
		this.registerState( bsp , "Send Propose");
		this.registerState(bwr, "Wait Response");
		this.registerState(bsz,"Send Zeuthen");
		this.registerState(brz, "Receive Zeuthen");
		this.registerState(bwp, "Wait Propose");
		this.registerState(bsr, "Send Response");
		this.registerLastState(bfs, "Final State");
		//this.registerState(bwps, "Start Init");

		//Por cada waite, para resetear.
		String[] toBeReset = {"Wait Propose", "Send Propose", "Wait Response", "Send Zeuthen", "Receive Zeuthen"};
		
		
		//Defino transicion de estados.
		this.registerTransition("Send Propose", "Wait Response", 0);
		this.registerTransition("Send Propose", "Final State", 1);
		this.registerTransition("Wait Response", "Final State", 2);
		this.registerTransition("Wait Response", "Send Zeuthen", 3);
		this.registerTransition("Send Zeuthen", "Receive Zeuthen", 4);
		this.registerTransition("Receive Zeuthen", "Send Propose", 5, toBeReset);
		this.registerTransition("Receive Zeuthen", "Wait Propose", 6);
		this.registerTransition("Wait Propose", "Send Response", 7);
		this.registerTransition("Send Response", "Send Zeuthen", 8);
		this.registerTransition("Send Response", "Final State", 9);
		this.registerTransition("Start Initiator", "Wait Response", 10);
		
		this.registerTransition("Wait Propose", "Final State", 12);
		//this.registerTransition("Wait Propose", "Wait Propose", 13);
		//this.registerTransition("Wait Response", "Wait Response", 14);
		//this.registerTransition("Receive Zeuthen", "Receive Zeuthen", 15);
		//this.registerTransition("Start Responder", "Start Responder", 16);
		this.registerTransition("Start Responder", "Send Response", 17);
	}
}
