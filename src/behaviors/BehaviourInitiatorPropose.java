package behaviors;
import java.util.Vector;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.util.leap.ArrayList;

public class BehaviourInitiatorPropose extends Behaviour {

	private int countMovies, state;
	private Vector<String> movies;
	private boolean end;
	private static final Integer Key = 1;
	
	public BehaviourInitiatorPropose(Vector<String> mov) {
		// TODO Auto-generated constructor stub
		countMovies = 0;
		this.movies = mov;
		this.state = 0;
		end = false;
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		 AID id = new AID();
         id.setLocalName("Responder");
         if ( countMovies < movies.size() )	{  
		     //Creo el mensaje 
        	 ACLMessage messagePropose = new ACLMessage(ACLMessage.PROPOSE);
        	 messagePropose.setSender(myAgent.getAID());
        	 messagePropose.setLanguage("Español");
        	 messagePropose.addReceiver(id);
        	 messagePropose.setContent(movies.get(countMovies));
        	 messagePropose.setConversationId("AB-1");
        	 messagePropose.setReplyWith("A-001");
        	 countMovies++;
        	 //Envio el mensaje
        	 myAgent.send(messagePropose);
        	 state = 0;
         }
         else	{
        	 ACLMessage messageReject = new ACLMessage(ACLMessage.CANCEL);
        	 messageReject.setSender(myAgent.getAID());
        	 messageReject.setLanguage("Español");
        	 messageReject.addReceiver(id);
        	 messageReject.setContent("Fallo la conversacion"); 
        	 getDataStore().put(Key, messageReject); //Almaceno el mensaje que llego 
        	 state = 1;
         }
         end = true;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return end;
	}

	@Override
	public int onEnd() {
		// TODO Auto-generated method stub
		return state;
	}
}
