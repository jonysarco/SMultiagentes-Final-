package agents;

public class PeliVal implements Comparable<PeliVal>{

	private String name;
	private Integer valor;
	
	public PeliVal(String peli, Integer valor){
		name = peli;
		this.valor = valor;
	}
	
	public String getName(){
		return name;
	}
	
	public int getValor(){
		return valor;
	}

	@Override
	public int compareTo(PeliVal o) {
		if (valor < o.valor) {
            return 1;
        }
        if (valor > o.valor) {
            return -1;
        }
        return 0;
    }
	

	@Override
	public boolean equals(Object obj) {
		String o1 = (String) obj;
		if(o1.equals(name))
			return true;
		return false;
	}
}
