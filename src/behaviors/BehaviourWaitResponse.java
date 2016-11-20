package behaviors;

import java.util.Vector;

import agents.PeliVal;
import core.SeeMovie;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourWaitResponse extends Behaviour {
	
	private int contador, estado;
	private boolean fin;
	private static final Integer Clave = 1;
	
	public BehaviourWaitResponse(){
		fin = false;
		//MIrar si esta bien el constructor
	}
		
	@Override
	public void action() {
		ACLMessage mensaje = myAgent.blockingReceive();  //Recibo el mensaje de respuesta del otro agente
        if (mensaje != null)
        {
        	
            //System.out.println(myAgent.getLocalName() + ": recibió el mensaje Responder:  ---- BehaviourWaitResponse");
            //System.out.println(mensaje.toString());
            
            if(mensaje.getPerformative()==ACLMessage.ACCEPT_PROPOSAL){ 
	           	//Pasar a estado final
            	System.out.println(myAgent.getLocalName() + ": recibió un mensaje de aceptacion :  ---- BehaviourWaitResponse");
            	String movie = "Pelicula"; 
            	ContentElement ce;
				try {
					ce = myAgent.getContentManager().extractContent(mensaje);
					Action action = (Action) ce;
					SeeMovie mov = (SeeMovie) action.getAction();
		           	getDataStore().put(movie, mov.getMovie().getName());
		           	getDataStore().put("decision", true); //paso al estado final avisando que se acepto la pelicula
		           	estado=2;
				} catch (CodecException | OntologyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
            }                
            else
            { 
            	
            	System.out.println(myAgent.getLocalName() + ": recibió un mensaje de rechazo :  ---- BehaviourWaitResponse");
	         	getDataStore().put(Clave, mensaje);  //paso al siguiente estado el mensaje que recibí
	            
	         	estado=3; //pasar a Calcular y enviar Zeuthen         
            } 
           fin = true;
        }
        else
        {
        	//estado = 14; //debo ciclar en el estado esperando la llegada del mensaje de respuesta
            System.out.println(myAgent.getLocalName() + " esta esperando el mansaje de RESPUESTA ---- BehaviourWaitResponse ");
            //block();
            
        }		
		
	}

	@Override
	public boolean done() {
		return fin;
	}
	public int onEnd() {
		return estado ;
	}
}
