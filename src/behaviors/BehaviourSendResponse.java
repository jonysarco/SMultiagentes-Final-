package behaviors;

import java.util.Vector;

import agents.Initiator;
import agents.PeliVal;
import core.Movie;
import core.MovieOntology;
import core.SeeMovie;
import jade.content.ContentElement;
import jade.content.onto.basic.Action;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourSendResponse extends Behaviour{
	
	private Vector<PeliVal> Coleccion;
	private boolean fin = false;
	private int estado;
	private static final Integer Clave = 1;
	private String decision = "decision";
	private static final Integer LastMovie = 3;
	
	public BehaviourSendResponse(Vector<PeliVal> mov){
		fin = false;
		Coleccion = mov;
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		int contador = (int) getDataStore().get("contador");
		ACLMessage mensaje = (ACLMessage) getDataStore().get(Clave); //Obtengo el mensaje de propuesta
				
		if(mensaje != null){
			fin = true;
			
			//Podria hacerse despues de recibir el mensaje y poner el See movie si queremos
			//Tendriamos que hacer lo del SeeMovie
			try{
				ContentElement ce = myAgent.getContentManager().extractContent(mensaje);
				Action action = (Action) ce;
				SeeMovie mov = (SeeMovie) action.getAction();
				
				
				String pelicula_Xj = mov.getMovie().getName();
			
				System.out.println(myAgent.getLocalName() + " Comienza a evaluar la propuesta: "+ pelicula_Xj +" ---- BehaviourWaitPropose " );
			
				int Xj =  valorPelicula(Coleccion, pelicula_Xj);//Obtengo mi puntaje para la pelicula ofrecida
				int Xi = Coleccion.get(contador).getValor(); //Obtengo el puntaje de la pelicula donde tengo el contador
				
				//System.out.println("El valor de la pelicula ofrecida es: " + Xj + " El valor de su pelicula es: " + Xi);
				ACLMessage respuesta = mensaje.createReply();
				
				
				// En reject y accept tendriamos que mandar el getContent si tomaamos todo el mensaje
				//si tomamos el see movie, extraemos los datos normal.
				
				if(Xi > Xj){   //Debemos generar una antologia   
					//debo calcular el Zeuthen y debo enviar el mensaje que rechazo la propuesta
					
					
					/*respuesta.setPerformative( ACLMessage.REJECT_PROPOSAL );
					System.out.println(myAgent.getLocalName() + " Rechaza la propuesta y envía la película " + Coleccion.get(contador).getName() +" ---- BehaviourSendResponse" );
					respuesta.setContent(Coleccion.get(contador).getName()); //envío la pelicula que tengo en la posición del contador.
					myAgent.send(respuesta);*/
					
					getDataStore().put(LastMovie, pelicula_Xj);		       	 
					System.out.println(myAgent.getLocalName() + " Rechaza la propuesta y envía la película " + Coleccion.get(contador).getName() +" ---- BehaviourSendResponse" );
					respuesta.setPerformative( ACLMessage.REJECT_PROPOSAL );
					respuesta.setLanguage(myAgent.getContentManager().getLanguageNames()[0]);
					respuesta.setOntology(MovieOntology.ONTOLOGY_NAME);
					respuesta.setSender(myAgent.getAID());
					Action action2 = new Action(respuesta.getSender(),mov);
					myAgent.getContentManager().fillContent(respuesta, action2);
					myAgent.send(respuesta);
					estado = 8;
					
				}
				else{
					//debo pasar al estado final aceptando la propuesta y enviando el mensaje de Acept_propose
					String movie = "Pelicula";
					System.out.println(pelicula_Xj);
					getDataStore().put(movie, pelicula_Xj); //pasa al siguiente estado la película que acepta ver*/
					
					System.out.println(myAgent.getLocalName() + " Acepta la propuesta ---- BehaviourSendResponse" );
					respuesta.setPerformative( ACLMessage.ACCEPT_PROPOSAL );
					respuesta.setLanguage(myAgent.getContentManager().getLanguageNames()[0]);
					respuesta.setOntology(MovieOntology.ONTOLOGY_NAME);
					respuesta.setSender(myAgent.getAID());
					Action action2 = new Action(respuesta.getSender(),mov);
					myAgent.getContentManager().fillContent(respuesta, action2);
					myAgent.send(respuesta);
					
					
					/*respuesta.setPerformative( ACLMessage.ACCEPT_PROPOSAL );
					respuesta.setContent(pelicula_Xj);
					myAgent.send(respuesta);
					getDataStore().put(movie, pelicula_Xj); //pasa al siguiente estado la película que acepta ver*/
					getDataStore().put(decision, true);
					estado = 9;
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
				 
		}
	}

	public int valorPelicula(Vector<PeliVal> Coleccion, String pelicula_Xj)
	{	
		boolean encontro = false;
		int value = -1;
		for(int i = 0; i < Coleccion.size(); i++)
		{
			if(Coleccion.get(i).getName().equals(pelicula_Xj)){
				encontro = true;
				value = Coleccion.get(i).getValor();
			}
			
		}
		return value;
		
	}
	
	
	public boolean done() {
		return fin;
	}
	
	@Override
	public int onEnd() {
		return estado;
	}

	public void reset(){
		fin = false;
		
	}
	
}
