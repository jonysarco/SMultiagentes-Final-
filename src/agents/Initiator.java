package agents;

import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import core.MovieOntology;
import fsm.FSM;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.Random;

public class Initiator extends Agent{

	//private Hashtable<String, Integer> peliculas = new Hashtable<String, Integer>();
	private Codec codec = new SLCodec();
	private Ontology ontology = MovieOntology.getInstance();
	
	private Vector<PeliVal> coleccion = new Vector<PeliVal>();

	protected void setup()
	{
		Random rn = new Random();

		int value = rn.nextInt(10) + 1;
		coleccion.addElement(new PeliVal("Rambo 1", value));
		value = rn.nextInt(10) + 1;
		coleccion.addElement(new PeliVal("Rocky I", value));
		value = rn.nextInt(10) + 1;
		coleccion.addElement(new PeliVal("El secreto de sus ojos", value));
		value = rn.nextInt(10) + 1;
		coleccion.addElement(new PeliVal("Matrix", value));
		value = rn.nextInt(10) + 1;
		coleccion.addElement(new PeliVal("Tarzan", value));
		value = rn.nextInt(10) + 1;
		coleccion.addElement(new PeliVal("El libro de la selva", value));

		Collections.sort(coleccion); //acá ordeno las peliculas por puntaje descendentemente
		
		//Defino el lenguaje y la ontologia
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(ontology);
		// Plantilla de descripción que busca el agente
		DFAgentDescription template = new DFAgentDescription();

		// Descripcion de un servicio se proporciona
		ServiceDescription servicio = new ServiceDescription();

		servicio.setType("negociacion");
		servicio.setName("peliculas");

		// Servicio que busca el agente
		template.addServices(servicio);
		try
		{
			// Todas las descripciones que encajan con la plantilla proporcionada en el DF
			DFAgentDescription[] resultados = DFService.search(this, template);

			if (resultados.length == 0){
				System.out.println("Ningun agente ofrece el servicio deseado, por lo tanto me registro");
				// Descripción del agente
				DFAgentDescription descripcion = new DFAgentDescription();
				descripcion.setName(getAID()); 
				// Añade dicho servicio a la lista de servicios de la descripción del agente
				descripcion.addServices(servicio);
				
				// creo que debo pasar a la maquina de estados y quedarme esperando a la llegada un mensaje propose   
				DFService.register(this, template);
				addBehaviour(new FSM(this.coleccion, "Responder", "null"));

			}
			else{    
				for (int i = 0; i < resultados.length; ++i)
				{
					System.out.println(resultados[i].getName().getLocalName()+" ofrece los servicios solicitados ");

					// creo que debo pasar a la maquina de estados, junto con el usuario al que le voy a mandar un mensaje Propose  

					addBehaviour(new FSM(this.coleccion, "Initiator", resultados[i].getName().getLocalName()));
					/*Iterator servicios = resultados[i].getAllServices();
	        		  int j = 1;
	        		  while(servicios.hasNext())
	        		  {
	        			  servicio = (ServiceDescription)servicios.next();
	        			  System.out.println(j+"- "+servicio.getName());
	        			  System.out.println();
	        			  j++;
	        		  }*/
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void imprimirPeliculas(Vector<PeliVal> peliculas)
	{
		for(int i = 0; i < 6; i++)
		{
			System.out.println(peliculas.get(i).getName() + "   "+ peliculas.get(i).getValor());
			
		}
	}
	
}
