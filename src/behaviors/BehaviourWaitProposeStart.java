package behaviors;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class BehaviourWaitProposeStart extends Behaviour	{

	public BehaviourWaitProposeStart(){
		super();
		//estado = 0;
		fin = false;
	} 
	
	private boolean fin = false;
	private int estado;
	private static final Integer Clave = 1;
	
	
	@Override
	public void action() {
		
		System.out.println("El agente " + myAgent.getLocalName() + " esta esperando la propuesta de una pelicula");
		 
		 ACLMessage mensaje = myAgent.receive(); 
		 		
		 if(mensaje != null)
		 {
			 fin = true;
			 getDataStore().put(Clave, mensaje);
			 if(mensaje.getPerformative()==ACLMessage.PROPOSE){ 
				 System.out.println("El agente " + myAgent.getLocalName() + " recibió un mensaje Propose " );
	           	 
				 //Debe pasar al estado Evaluar propuesta y responder
				 estado=0; 
			 }  
         }
         else
         {
        	 //continua esperando recibir un mensaje, cicla en ese estado
        	 estado=1;
             System.out.println(myAgent.getLocalName() +": esta esperando recibir un nueva propuesta");
             block();                 
         }		

	}

	@Override
	public boolean done() {
		return fin;
	}
	
	@Override
	public int onEnd() {
		return estado;
	}

}