package behaviors;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourInitiatorWait extends Behaviour {

	private boolean end;
	private int state;
	private static final Integer Key = 1;
	
	public BehaviourInitiatorWait(){
	}
	
	@Override
	public void action() {
		//Respuesta del Receptor a la propuesta
		 ACLMessage message = myAgent.receive();
         if ( message!= null )
         {
             getDataStore().put(Key, message); //Almaceno el message que llego 
             if ( message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL )	{ 
            	 state = 1;
             }                
             else	{ 
            	 //Vuelvo a proponer
            	 state = 0;
             } 
            end = true;
         }
         else	{
             	block();
         }		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return end;
	}
	
	public int onEnd() {
		// TODO Auto-generated method stub
		return state ;
	}

}	
	
