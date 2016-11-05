package behaviors;

import java.util.Vector;

import agents.PeliVal;
import core.Movie;
import core.MovieOntology;
import core.SeeMovie;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
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
        id.setLocalName(name); // acá seteo el nombre del agente que recibira el mensaje de Propose
		contador = 0;
        if ( contador < coleccion.size() )	{  
		     //Creo el mensaje 
        	 
        	 getDataStore().put("contador", contador);//envío la posición de la lista que voy recorriendo al siguiente estado
	       	 
	       	 
	       	 Movie mov = new Movie(coleccion.get(contador).getName());
	       	 
	       	 
	       	 SeeMovie movies = new SeeMovie(mov);
	       	 ACLMessage mensaje = new ACLMessage(ACLMessage.PROPOSE);

	       	 mensaje.setLanguage(myAgent.getContentManager().getLanguageNames()[0]);
	       	 mensaje.setOntology(MovieOntology.ONTOLOGY_NAME);
	       	 mensaje.setSender(myAgent.getAID());
	       	 mensaje.addReceiver(id);
	       	 try {

	       		myAgent.getContentManager().fillContent(mensaje, movies);
			} catch (Exception e) {
				// TODO: handle exception
			}
	       	myAgent.send(mensaje);
	       	 estado = 10;
	       	 System.out.println(myAgent.getLocalName() + " envia una propuesta: "+mov.getMovie()+" al agente: " + id.getLocalName() + " ---- BehaviourStartPropose ");
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

