package behaviors;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class BehaviourResponderConfirm extends Behaviour {

	private boolean end;
	private int state;
	private static final Integer Key = 1;
	public static final Float accept = 0.5f;
	
	public BehaviourResponderConfirm(){
		super();
		state = 0;
		end = false;
	} 
	
	//Seteo los parametros que va a contener el mensaje
	public void setMessage(ACLMessage message, ACLMessage answer)	{
		message.setSender(myAgent.getAID());
		message.setLanguage("Español");
		message.addReceiver(answer.getSender());
		message.setContent(answer.getContent());
		myAgent.send(message);
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		//Obtengo el mensaje de propuesta
		ACLMessage answer = (ACLMessage) getDataStore().get(Key);
		if ( Math.random() > accept )	{
			//Acepta la propuesta
			ACLMessage acceptMessage = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
			setMessage(acceptMessage, answer);
			state = 1;
			end = true;
		}
		else	
		{
			//Rechazo el mensaje
			ACLMessage rejectMessage = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
			setMessage(rejectMessage, answer);
			state = 0;
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
