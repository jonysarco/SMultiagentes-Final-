package behaviors;

import java.util.Vector;

import agents.PeliVal;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourSendResponse extends Behaviour{
	
	private Vector<PeliVal> Coleccion;
	private boolean fin = false;
	private int estado;
	private static final Integer Clave = 1;
	private String decision = "decision";
	public BehaviourSendResponse(Vector<PeliVal> mov){
		fin = false;
		Coleccion = mov;
	}

	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		int contador = (int) getDataStore().get("contador");
		ACLMessage mensaje = (ACLMessage) getDataStore().get(Clave); //Obtengo el mensaje de propuesta
		
		System.out.println(myAgent.getLocalName() + " Comienza a evaluar la propuesta: "+ mensaje.getContent() +" ---- BehaviourSendResponse" );
		
		if(mensaje != null){
			fin = true;
			String pelicula_Xj = mensaje.getContent();
						
			int Xj =  valorPelicula(Coleccion, pelicula_Xj);//Obtengo mi puntaje para la pelicula ofrecida
			int Xi = Coleccion.get(contador).getValor(); //Obtengo el puntaje de la pelicula donde tengo el contador
			
			//System.out.println("El valor de la pelicula ofrecida es: " + Xj + " El valor de su pelicula es: " + Xi);
			ACLMessage respuesta = mensaje.createReply();
			if(Xi > Xj){
				//debo calcular el Zeuthen y debo enviar el mensaje que rechazo la propuesta
				respuesta.setPerformative( ACLMessage.REJECT_PROPOSAL );
				System.out.println(myAgent.getLocalName() + " Rechaza la propuesta y envía la película " + Coleccion.get(contador).getName() +" ---- BehaviourSendResponse" );
				respuesta.setContent(Coleccion.get(contador).getName()); //envío la pelicula que tengo en la posición del contador.
				myAgent.send(respuesta); 
				estado = 8;
				
			}
			else{
				//debo pasar al estado final aceptando la propuesta y enviando el mensaje de Acept_propose
				String movie = "Pelicula";
				System.out.println(myAgent.getLocalName() + " Acepta la propuesta ---- BehaviourSendResponse" );
				
				respuesta.setPerformative( ACLMessage.ACCEPT_PROPOSAL );
				respuesta.setContent(pelicula_Xj);
				myAgent.send(respuesta);
				getDataStore().put(movie, pelicula_Xj); //pasa al siguiente estado la película que acepta ver
				getDataStore().put(decision, true);
				estado = 9;
			}
				 
		}
	}

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

}
