package behaviors;

import java.util.Vector;

import agents.PeliVal;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourStartPropose extends Behaviour	{
	
	private int contador,estado;
	private String name;
	private Vector<PeliVal> coleccion;
	private boolean fin;
	public BehaviourStartPropose(Vector<PeliVal> mov, String responder){
		super();
		coleccion = mov;
		name = responder;
		
		fin = false;
	} 
		
	@Override
	public void action() {
		// TODO Auto-generated method stub
		AID id = new AID();
        id.setLocalName(name); // ac� seteo el nombre del agente que recibira el mensaje de Propose
		contador = 0;
        if ( contador < coleccion.size() )	{  
		     //Creo el mensaje 
        	 
        	 getDataStore().put("contador", contador);//env�o la posici�n de la lista que voy recorriendo al siguiente estado
	       	 ACLMessage mensaje = new ACLMessage(ACLMessage.PROPOSE);
	       	 mensaje.setSender(myAgent.getAID());
	       	 mensaje.setLanguage("Espa�ol");
	       	 mensaje.addReceiver(id);
	       	 mensaje.setContent(coleccion.get(contador).getName()); //Envio el nombre de la pel�cula de mi lista
	      	 mensaje.setConversationId("AB-1");
	       	 mensaje.setReplyWith("A-001");
	       	 //Envio el mensaje
	       	 myAgent.send(mensaje);
	       	 estado = 10;
	       	 System.out.println(myAgent.getLocalName() + " envia una propuesta: "+coleccion.get(contador).getName()+" al agente: " + id.getLocalName() + " ---- BehaviourStartPropose ");
	       	 fin = true;
        }
	}

	public int onEnd() {
		return estado ;
	}
	
	
	@Override
	public boolean done() {
		return fin;
	}

}

