package behaviors;

import java.util.Vector;

import agents.PeliVal;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourSendZeuthen extends Behaviour	{
	
	private Vector<PeliVal> Coleccion;
	private boolean fin;
	private int estado;
	private static final Integer Clave = 1;
	private float Zeuthen;
	
	public BehaviourSendZeuthen(Vector<PeliVal> mov){
		super();
		Coleccion = mov;
	}
	
	@Override
	public void action() {
		
		int contador = (int) getDataStore().get("contador");
		ACLMessage mensaje = (ACLMessage) getDataStore().get(Clave);
		if(mensaje != null){
			
			//Primero calculamos Zeuthen
			System.out.println(myAgent.getLocalName() + ": Esta calculando su Zeuthen  ---- BehaviourSendZeuthen ");
			String pelicula_Xj = mensaje.getContent();
			System.out.println("pelicula: " + pelicula_Xj + " ---- BEhaviourSendZeuthen ");
			
			
			int Xj = valorPelicula(Coleccion, pelicula_Xj);  //Obtengo mi puntaje para la pelicula ofrecida
					
			int Xi = Coleccion.get(contador).getValor(); //Obtengo el puntaje de la pelicula donde tengo el contador
			
			Zeuthen = (((float)Xi - (float)Xj) / (float)Xi); //Si no anda bien
			
			getDataStore().put("Zeuthen", Zeuthen);  //Debo pasar el Zeuthen al siguiente estado.
						
			System.out.println(myAgent.getLocalName() + ": ha calculado su Zeuthen " + Zeuthen +" ---- BehaviourSendZeuthen ");
	//---
			//Envia mensaje con el Zeuthen calculado al otro agente
			ACLMessage respuesta = mensaje.createReply();
            respuesta.setPerformative( ACLMessage.INFORM ); //Se envia un mensaje de tipo INFORM al otro Agente
            //Convertimos el Zeuthen a String para poder envíarlo en el contenido del mensaje
            String SZeuthen = Float.toString(Zeuthen);  
            respuesta.setContent(SZeuthen);
            myAgent.send(respuesta); 
            System.out.println(myAgent.getLocalName() +": Envio su Zeuthen al otro agente ---- BehaviourSendZeuthen " );
         	
            estado = 4; //paso al siguiente estado con la idea de esperar el Zeuthen del siguiente agente.
			fin = true;
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
	
	

}
