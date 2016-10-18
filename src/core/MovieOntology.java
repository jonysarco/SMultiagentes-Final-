package core;

import jade.content.lang.sl.SLCodec;
import jade.content.onto.BasicOntology;
import jade.content.onto.Introspector;
import jade.content.onto.Ontology;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.PrimitiveSchema;

public class MovieOntology extends Ontology {

	public static final String ONTOLOGY_NAME = "movie-ontology";
	
	public static final String MOVIE = "movie";
	public static final String MOVIE_YEAR = "year";
	public static final String MOVIE_NAME = "name";
	public static final String MOVIE_DIRECTOR = "director";
	public static final String MOVIE_ACTOR = "actor";
	private static final String MOVIE_DATE = "date";
	
	private static Ontology ontologyInstance = new MovieOntology();
	private static SLCodec codecInstance = new SLCodec();
	
	public MovieOntology() {
		super(ONTOLOGY_NAME, BasicOntology.getInstance());
		try {
			
			ConceptSchema cSchema= new ConceptSchema(MOVIE);
			cSchema.add(MOVIE_NAME, (PrimitiveSchema)getSchema(BasicOntology.STRING), PrimitiveSchema.MANDATORY);
			cSchema.add(MOVIE_YEAR, (PrimitiveSchema)getSchema(BasicOntology.DATE), PrimitiveSchema.OPTIONAL);
			cSchema.add(MOVIE_DIRECTOR, (PrimitiveSchema)getSchema(BasicOntology.STRING), PrimitiveSchema.OPTIONAL);
			cSchema.add(MOVIE_ACTOR, (PrimitiveSchema)getSchema(BasicOntology.STRING), PrimitiveSchema.OPTIONAL);
			add(cSchema, Movie.class);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Ontology getOntologyInstance() {
		return ontologyInstance;
	}
	
	public SLCodec getCodecInstance()	{
		return codecInstance;
	}
	
	
}
