package behaviors;

import jade.core.behaviours.Behaviour;

public class BehaviourFinalState extends Behaviour {

	private static final String decision = "decision";
	private boolean fin ;
	
	
	public BehaviourFinalState() {
		super();
		this.fin = false;
	}
	
	
	
	@Override
	public void action() {
		//debo obtener el nombre de la pelicula con la cual hubo acuerdo
		Boolean acuerdo = (Boolean) getDataStore().get(decision);
		if(acuerdo){
			String movie = "Pelicula"; //Obtengo el nombre de la película que fueron a ver
			System.out.println("Hubo acuerdo en ir a ver la pelicula: " + getDataStore().get(movie));
		}   
		else
			System.out.println("No hubo acuerdo entre los agentes");
		fin=true;
	}
		
	protected void takeDown() {
	     System.out.println("Agente "+myAgent.getLocalName()+" termino de ejecutarse.");
    }

		@Override
	public boolean done(){
		if(fin==true)
			myAgent.doDelete();
		return fin;
	}
		
		
		// TODO Auto-generated method stub
		
}



