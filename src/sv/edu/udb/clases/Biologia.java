package sv.edu.udb.clases;

abstract public class Biologia {
	//Propiedades
	private String Nombre;
	private String NombreCientifico;
	private String Familia;
	private float Altura;
	
	//Constructores
	public Biologia() {
		super();
	}
	
	public Biologia(String nombre, String nombreCientifico, String familia, float altura) {
		super();
		Nombre = nombre;
		NombreCientifico = nombreCientifico;
		Familia = familia;
		Altura = altura;
	}

	//Getters y Setters
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getNombreCientifico() {
		return NombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		NombreCientifico = nombreCientifico;
	}

	public String getFamilia() {
		return Familia;
	}

	public void setFamilia(String familia) {
		Familia = familia;
	}

	public float getAltura() {
		return Altura;
	}

	public void setAltura(float altura) {
		Altura = altura;
	}
	
	
	
	
	
}
