package sv.edu.udb.clases;

import java.sql.*;

public class Conexion {
	//Definimos las propiedades para la conexion
	private String Driver="com.mysql.cj.jdbc.Driver";
	private String URL= "jdbc:mysql://localhost:3306/biologia";
	private String usuario = "root";
	private String password = "123456";
	private Connection cn = null;
	private Statement st = null;
	private String query = "";
	
	public boolean createAnimal(Animal animal) {
		
		try {
			//Mandamos a llamar el driver
			Class.forName(Driver);
			
			//El programa prueba si puede realizar una conexion a la db
			cn = DriverManager.getConnection(URL,usuario,password);
			
			//Creamos la query para guardar animales
			query = "INSERT INTO animales(nombre,nombre_cientifico,familia,altura,peso) VALUES"
					+ "('"+animal.getNombre()+"','"+animal.getNombreCientifico()+"','"
						+animal.getFamilia()+"',"+animal.getAltura()+","+animal.getPeso()+")";
			System.out.println(query);
			
			//Creamos la comunicacion que espera una ejecucion
			st = cn.createStatement();
			
			//Excecute Update lo utilizaremos siempre que tengamos que
			//modificar algun valor de bd por ejemplo Insert, Update y Delete
			st.executeUpdate(query);
			
			//Siempre y no olvidarlo, se debe cerrar
			//la conexion a la base de datos
			cn.close();
			
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			
			return false;
		}		
	}
	
}
