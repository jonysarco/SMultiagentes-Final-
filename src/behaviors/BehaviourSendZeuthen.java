package behaviors;

import java.util.Vector;

import agents.PeliVal;
import jade.core.behaviours.Behaviour;

public class BehaviourSendZeuthen extends Behaviour	{
	private Vector<PeliVal> Coleccion;
	
	public BehaviourSendZeuthen(Vector<PeliVal> mov){
		super();
		Coleccion = mov;
	}
	
	@Override
	public void action() {
		
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
