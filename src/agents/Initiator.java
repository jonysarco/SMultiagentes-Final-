package agents;
import java.util.Iterator;
import java.util.Vector;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Initiator extends Agent{
	
	private Vector<String> movies;
	
	protected void setup()
    {
		try {
			ServiceDescription service = new ServiceDescription();
			service.setType("negociacion");
			service.setName("peliculas");

			//Plantilla de descripción que busca el agente
			DFAgentDescription template = new DFAgentDescription();

			//Servicio que busca el agente
			template.addServices(service);

			//Todas las descripciones que encajan con la plantilla proporcionada en el DF
			DFAgentDescription[] results = DFService.search(this, template);

			if (results.length == 0)
				System.out.println("Ningun agente ofrece el servicio deseado");
			for (int i = 0; i < results.length; ++i)	{
				System.out.println("El agente "+results[i].getName()+" ofrece los siguientes servicios:");
				Iterator services = results[i].getAllServices();
				int j = 1;
				while(services.hasNext())	{
					service = (ServiceDescription)services.next();
					System.out.println(j+"- "+service.getName());
					System.out.println();
					j++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}