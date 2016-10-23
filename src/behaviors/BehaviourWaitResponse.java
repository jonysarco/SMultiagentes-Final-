package behaviors;

import java.util.Vector;

import agents.PeliVal;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourWaitResponse extends Behaviour {
	
	private int contador,estado;
	private boolean fin;
	private static final Integer Clave = 1;
	
	public BehaviourWaitResponse(){
		fin = false;
		//MIrar si esta bien el constructor
	}
		
	@Override
	public void action() {
		ACLMessage mensaje = myAgent.receive();
        if (mensaje!= null)
        {
            System.out.println(myAgent.getLocalName() + ": recibió el mensaje : ");
            System.out.println(mensaje.toString());
            getDataStore().put(Clave, mensaje);
            if(mensaje.getPerformative()==ACLMessage.ACCEPT_PROPOSAL){ 
           	//Pasar a estado final
           	 estado=2;
            }                
            else{ 
            //pasar a Calcular y enviar Zeuthen	
            	
            estado=3;
            } 
           fin = true;
        }
        else
        {
            System.out.println("El agente " +  myAgent.getLocalName() + " esta esperando la respuesta ");
            block();
            
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
