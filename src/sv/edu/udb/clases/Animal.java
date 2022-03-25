package sv.edu.udb.clases;

public class Animal extends Biologia{
	//Propiedades
	private float Peso;

	//Constructores
	public Animal() {
		super();
	}

	public Animal(String nombre, String nombreCientifico, String familia, float altura, float peso) {
		super(nombre, nombreCientifico, familia, altura);
		Peso = peso;
	}

	//Getters y Setters
	public float getPeso() {
		return Peso;
	}

	public void setPeso(float peso) {
		Peso = peso;
	}
	
}
