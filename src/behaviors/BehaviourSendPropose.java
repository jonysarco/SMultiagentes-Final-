package behaviors;

import java.util.Vector;

import agents.PeliVal;
import core.Movie;
import core.MovieOntology;
import core.SeeMovie;
import jade.content.ContentManager;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
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

		//System.out.println("*********************************************************************************************");

		//System.out.println("Me fijo que tiene el contador :"+getDataStore().get("contador") + " ---- BehaviourSendPropose ");
		int contador = (int) getDataStore().get("contador");
		ACLMessage mensaje = (ACLMessage) getDataStore().get(Clave); //Obtengo el mensaje de propuesta
		if(mensaje != null)
		{
			fin = true;
			ACLMessage respuesta = mensaje.createReply();
			if(contador < Coleccion.size())
				try {
					Movie mov = new Movie(Coleccion.get(contador).getName());		       	 
					SeeMovie movies = new SeeMovie(mov);
					respuesta.setPerformative( ACLMessage.PROPOSE );
					respuesta.setLanguage(myAgent.getContentManager().getLanguageNames()[0]);
					respuesta.setOntology(MovieOntology.ONTOLOGY_NAME);
					respuesta.setSender(myAgent.getAID());
					Action action = new Action(respuesta.getSender(),movies);
					myAgent.getContentManager().fillContent(respuesta, action);
					myAgent.send(respuesta);
					estado = 0; //paso a esperar la respuesta
					System.out.println("El agente " + myAgent.getLocalName() + " propuso la pelicula --- SendPropose " + Coleccion.get(contador).getName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//CodecException | OntologyException e
					e.printStackTrace();
				}
			else
			{
				getDataStore().put(decision, false);
				respuesta.setPerformative( ACLMessage.CANCEL ); //envío mensaje de cancel al otro agente
				myAgent.send(respuesta);
				estado = 1; //paso a estado final para terminar negociación
				System.out.println(myAgent.getLocalName() + " no tiene más peliculas para ofrecer --- SendPropose" );
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
