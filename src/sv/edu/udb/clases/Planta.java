package sv.edu.udb.clases;

public class Planta extends Biologia{
	//Declara propiedades
	private String TipoPlanta;

	//Constructores
	public Planta() {
		super();
	}

	public Planta(String nombre, String nombreCientifico,
			String familia, float altura, String tipoPlanta) {
		super(nombre, nombreCientifico, familia, altura);
		TipoPlanta = tipoPlanta;
	}
	
	//Getters y Setters
	public String getTipoPlanta() {
		return TipoPlanta;
	}

	public void setTipoPlanta(String tipoPlanta) {
		TipoPlanta = tipoPlanta;
	}
		
}
