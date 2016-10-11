package behaviors;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourResponderWait extends Behaviour {

	private boolean end;
	private int state;
	private static final Integer Key = 1;
	
	public BehaviourResponderWait(){
		end = false;
		state = 1;
	}
	
	@Override
	public void action() {
		System.out.println("El agente " + myAgent.getName() + " esta esperando la propuesta de una pelicula");

		ACLMessage message = myAgent.receive();
		 
		//Obtiene la primer pelicula de la cola de mensajes
		 if ( message != null )	{
			 getDataStore().put(Key, message); //Guardo el mensaje para que se lo pase en el siguiente estado
			 if ( message.getPerformative() == ACLMessage.PROPOSE ) {
				 System.out.println("El agente " + myAgent.getName() + " recibió un mensaje Propose " );
	           	 state = 0; //Paso al siguiente estado para analizar propuesta
			 }
			 else	{
				 	System.out.println("El agente " + myAgent.getName() + " recibió un mensaje Cancel " );
				 	state = 2; // Llego un mensaje Cancel y paso al estado final
			 }
			 end = true;
         }
         else
         {
        	 state = 1;
             block();                 
         }		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return end;
	}
	
	@Override
	public int onEnd() {
	// TODO Auto-generated method stub
		return state;
	}

}
