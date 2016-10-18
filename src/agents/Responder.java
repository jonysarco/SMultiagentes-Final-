package agents;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Responder extends Agent {
	
	protected void setup()
    {
		// Descripción del agente
        DFAgentDescription description = new DFAgentDescription();
        description.setName(getAID());
         
    	// Descripcion de un servicio que  se proporciona
        ServiceDescription service = new ServiceDescription();
        service.setType("negociacion");
        service.setName("peliculas");
 
    	// Añade dicho servicio a la lista de servicios de la descripción del agente
        description.addServices(service);

         try
        {
            DFService.register(this, description);
        }
        catch (FIPAException e)
        {
            e.printStackTrace();
        }
    }


	protected void takeDown()
    {
        try
        {
            DFService.deregister(this);
        }
        catch (FIPAException fe)
        {
            fe.printStackTrace();
        }
        System.out.println("El agente "+ getLocalName() +" ya no ofrece sus servicios.");
    }
}