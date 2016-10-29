package behaviors;

import java.util.Vector;

import agents.PeliVal;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourSendPropose extends Behaviour {

	private Vector<PeliVal> Coleccion;
	private boolean fin = false;
	private int estado;
	private static final Integer Clave = 1;
	private String decision = "decision";
	
	public BehaviourSendPropose(Vector<PeliVal> mov){
		fin = false;
		Coleccion = mov;
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		
		System.out.println("*********************************************************************************************");
		
		System.out.println("Me fijo que tiene el contador :"+getDataStore().get("contador") + " ---- BehaviourSendPropose ");
		int contador = (int) getDataStore().get("contador");
		ACLMessage mensaje = (ACLMessage) getDataStore().get(Clave); //Obtengo el mensaje de propuesta
		if(mensaje != null)
		{
			fin = true;
			ACLMessage respuesta = mensaje.createReply();
			if(contador < Coleccion.size())
			{
				respuesta.setPerformative( ACLMessage.PROPOSE );
				respuesta.setContent(Coleccion.get(contador).getName()); //mando el nombre de la pelicula para la propuesta
				myAgent.send(respuesta);
				estado = 0; //paso a esperar la respuesta
				System.out.println("El agente " + myAgent.getLocalName() + " propuso la pelicula " + Coleccion.get(contador).getName());
			}
			else
			{
				getDataStore().put(decision, false);
				respuesta.setPerformative( ACLMessage.CANCEL ); //envío mensaje de cancel al otro agente
				myAgent.send(respuesta);
				estado = 1; //paso a estado final para terminar negociación
				System.out.println(myAgent.getLocalName() + " no tiene más peliculas para ofrecer " );
			}		
		}
	}

	public boolean done() {
		return fin;
	}
	
	@Override
	public int onEnd() {
		return estado;
	}
}
