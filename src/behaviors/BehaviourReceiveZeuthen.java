package behaviors;

import java.util.Vector;

import agents.PeliVal;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourReceiveZeuthen extends Behaviour {

	public BehaviourReceiveZeuthen(){
		super();
		//estado = 0;
		fin = false;
	} 
	
	private boolean fin = false;
	private int estado;
	private static final Integer Clave = 1;
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		
		
		ACLMessage mensaje = myAgent.blockingReceive(); 
		if(mensaje != null){
			fin = true;
			if(mensaje.getPerformative()==ACLMessage.INFORM){ 
				 System.out.println(myAgent.getLocalName() + " recibió un mensaje INFORM ---- BehaviourReceiveZeuthen " );
	          	 String ObjZeuthen = "Zeuthen";
				 float ZeuthenB = Float.parseFloat(mensaje.getContent());  //Se obtiene el Zeuthen del otro agente
				 //System.out.println("EL ZEUTHEN ES DEL OTRO AGENTE ES: "+mensaje.getContent());
				 float ZeuthenA = (float) getDataStore().get(ObjZeuthen); //Obtengo mi Zeuthen
				 
				 if(ZeuthenA > ZeuthenB){
					 //Debo pasar al estado de esperar Propuesta
					 System.out.println(myAgent.getLocalName() + " su Zeuthen es mayor pasa a WaitPropose ---- BeheaviourReceiveZeuthen ");
					 estado = 6;
				 }
				 else{
					 //Debo pasar al estado de enviar propuesta y debo incrementar contador;
					 
					 estado = 5;
					//Incremento en 1 el contador, esto lo podría hacer directamente en el siguiente estado
					 ObjZeuthen = "contador"; 
					 int cont = (int) getDataStore().get(ObjZeuthen);
					 cont++;
					 getDataStore().put(ObjZeuthen, cont);
					 System.out.println(myAgent.getLocalName() + " su Zeuthen es menor pasa a SendPropose ---- BeheaviourReceiveZeuthen " + cont);
					 
				 }
				 
				 //Debe pasar al estado Evaluar propuesta y responder
				  
			}
		}
		else{
			//estado = 15;
			System.out.println(myAgent.getLocalName() + " esta esperando recibir el Zeuthen ---- BehaviourReceiveZeuthen ");
			//block();
		}
    }

	
	public int onEnd() {
		return estado ;
	}
		
	@Override
	public boolean done() {
		return fin;
	}
	
	public void reset(){
		fin = false;
	}

}
