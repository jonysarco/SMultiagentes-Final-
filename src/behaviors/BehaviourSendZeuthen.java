package behaviors;

import java.util.Vector;

import agents.PeliVal;
import core.Movie;
import core.MovieOntology;
import core.SeeMovie;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourSendZeuthen extends Behaviour	{
	
	private Vector<PeliVal> Coleccion;
	private boolean fin;
	private int estado;
	private static final Integer Clave = 1;
	private float Zeuthen;
	private static final Integer LastMovie = 3;
	
	public BehaviourSendZeuthen(Vector<PeliVal> mov){
		super();
		Coleccion = mov;
	}
	
	@Override
	public void action() {
		
		int contador = (int) getDataStore().get("contador");
		ACLMessage mensaje = (ACLMessage) getDataStore().get(Clave);
		if(mensaje != null){
			
			
			try {
				ContentElement ce;
				ce = myAgent.getContentManager().extractContent(mensaje);
				Action action = (Action) ce;
				SeeMovie mov = (SeeMovie) action.getAction();
				
				
				//String pelicula_Xj = mov.getMovie().getName();
				String pelicula_Xj = (String) getDataStore().get(LastMovie);
				String Pelicula = "null";
				//Primero calculamos Zeuthen
				//System.out.println(myAgent.getLocalName() + ": Esta calculando su Zeuthen  ---- BehaviourSendZeuthen ");
				
				//System.out.println("pelicula: " + pelicula_Xj + " ---- BEhaviourSendZeuthen ");
				float Xj;
				if(pelicula_Xj.equals(Pelicula))
					Xj = 0;
				else
					Xj = valorPelicula(Coleccion, pelicula_Xj);  //Obtengo mi puntaje para la pelicula ofrecida
						
				float Xi = Coleccion.get(contador).getValor(); //Obtengo el puntaje de la pelicula donde tengo el contador

				System.out.println("El valor de la pelicula Xi: "+Xi+ " --- BehaviourSendZeuthen");
				System.out.println("El valor de la pelicula Xj: "+Xj+"--- BehaviourSendZeuthen");
				
				//Zeuthen = (((float)Xi - (float)Xj) / (float)Xi); //Si no anda bien
				Zeuthen = (Xi-Xj)/Xi;
				
				getDataStore().put("Zeuthen", Zeuthen);  //Debo pasar el Zeuthen al siguiente estado.
							
				//System.out.println(myAgent.getLocalName() + ": ha calculado su Zeuthen " + Zeuthen +" ---- BehaviourSendZeuthen ");
		//---		
				//Envia mensaje con el Zeuthen calculado al otro agente
				ACLMessage respuesta = mensaje.createReply();
	            respuesta.setPerformative( ACLMessage.INFORM ); //Se envia un mensaje de tipo INFORM al otro Agente
	            //Convertimos el Zeuthen a String para poder envíarlo en el contenido del mensaje
	            String SZeuthen = Float.toString(Zeuthen);  
	            respuesta.setContent(SZeuthen);
	            respuesta.setOntology(MovieOntology.ONTOLOGY_NAME);
	            myAgent.send(respuesta); 
	            System.out.println(myAgent.getLocalName() +": Envio su Zeuthen: "+Zeuthen+ "al otro agente---- BehaviourSendZeuthen " );
	            estado = 4; //paso al siguiente estado con la idea de esperar el Zeuthen del siguiente agente.
				fin = true;
			} catch (CodecException | OntologyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	

	
	/*  ACLMessage mensaje = receive();
                if (mensaje!= null)
                {
                    System.out.println(getLocalName() + ": acaba de recibir el siguiente mensaje: ");
                    System.out.println(mensaje.toString());
 
                // Envia constestación
                    System.out.println(getLocalName() +": Enviando contestacion");
                    ACLMessage respuesta = mensaje.createReply();
                    respuesta.setPerformative( ACLMessage.INFORM );
                    respuesta.setContent( "Bien" );
                    send(respuesta);
                    System.out.println(getLocalName() +": Enviando Bien a receptor");
                    System.out.println(respuesta.toString());
                    fin = true;*/
	
	
	public int valorPelicula(Vector<PeliVal> Coleccion, String pelicula_Xj)
	{
		
		for(int i = 0; i < Coleccion.size(); i++)
		{
			if(Coleccion.get(i).getName().equals(pelicula_Xj)){
				return Coleccion.get(i).getValor();
			}
				
		}
		return -1;
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
