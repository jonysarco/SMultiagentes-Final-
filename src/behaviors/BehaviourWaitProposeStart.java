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
		
		
		 
		 ACLMessage mensaje = myAgent.receive(); 
		 		
		 if(mensaje != null)
		 {
			 fin = true;
			 getDataStore().put(Clave, mensaje);
			 if(mensaje.getPerformative()==ACLMessage.PROPOSE){ 
				 int contador = 0;
				 System.out.println(myAgent.getLocalName() + " recibió un mensaje Propose ---- START PROPOSE");
				 getDataStore().put("contador", contador);//envío la posición de la lista que voy recorriendo al siguiente estado
				 //Debe pasar al estado Evaluar propuesta y responder
				 estado=17; 
			 }
			 else
			 {
				 estado = 16;
				 System.out.println("ME quedo ciclando ---- ");
			 }
         }
         else
         {
        	 //continua esperando recibir un mensaje, cicla en ese estado
        	
        	 estado=16;
             System.out.println(myAgent.getLocalName() +": se queda ciclando esperando un mensaje PROPOSE ---- START PROPOSE ");
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