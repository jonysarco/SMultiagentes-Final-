package behaviors;

import core.IsMyZeuthen;
import core.Movie;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourWaitPropose extends Behaviour	{

	private boolean fin = false;
	private int estado;
	private static final Integer Clave = 1;
	
	public BehaviourWaitPropose(){
		fin = false;
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		//System.out.println(myAgent.getLocalName() + " esta esperando la propuesta de una pelicula ---- BehaviourWaitPropose " );
		
		ACLMessage mensaje = myAgent.receive();
		
		 if(mensaje != null)
		 {
			 fin = true;
			 ContentElement ce;
			 if(mensaje.getPerformative() == ACLMessage.PROPOSE){
				 try {
					ce = myAgent.getContentManager().extractContent(mensaje);
					IsMyZeuthen zeuthen = (IsMyZeuthen) ce;
					Movie mov = zeuthen.getMovie();
					System.out.println(myAgent.getLocalName() + " recibió un mensaje Propose "+mov.getName()+" ---- BehaviourWaitPropose " );
		           	estado = 7; //paso al estado SendResponse para evaluar la proposición
				 } catch (CodecException | OntologyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 else if(mensaje.getPerformative() == ACLMessage.CANCEL){
				 System.out.println("El agente " + myAgent.getLocalName() + " recibió un mensaje Cancel ---- BehaviourWaitPropose " );
				 estado = 12; //paso al estado final porque no hubo acuerdo
				 getDataStore().put("decision", false); 				 
			 }   
         }
		 else{
	         estado=13; // me quedo esperando a que me llegue una propuesta
	         System.out.println(myAgent.getLocalName() +": se queda ciclando  ----  BehaviourWaitPropose ");
	         block();                 
	      	 }
	}

	@Override
	public boolean done() {
		return fin;
	}
	
	@Override
	public int onEnd() {
		return estado;
	}

}
