package sv.edu.udb.ejecutar;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//Imports para nuestra ejecucion
import sv.edu.udb.clases.*;
import java.util.*;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class RegistroAnimal {

	private JFrame frRegistroAnimales;
	private JTextField txtNombre;
	private JTextField txtNombreCientifico;
	private JTextField txtFamilia;
	private JTextField txtAltura;
	private JTextField txtPeso;
	private ArrayList<Animal> registroAnimales = new ArrayList<Animal>();
	private JTable tblRegistroAnimales;
	private Conexion db = new Conexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroAnimal window = new RegistroAnimal();
					window.frRegistroAnimales.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistroAnimal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frRegistroAnimales = new JFrame();
		frRegistroAnimales.setBounds(100, 100, 805, 396);
		frRegistroAnimales.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frRegistroAnimales.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registro de animales");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTitulo.setBounds(10, 11, 769, 41);
		frRegistroAnimales.getContentPane().add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(32, 68, 146, 14);
		frRegistroAnimales.getContentPane().add(lblNombre);
		
		JLabel lblNombreCientifico = new JLabel("Nombre Cientifico");
		lblNombreCientifico.setBounds(32, 105, 146, 14);
		frRegistroAnimales.getContentPane().add(lblNombreCientifico);
		
		JLabel lblFamilia = new JLabel("Familia");
		lblFamilia.setBounds(32, 144, 146, 14);
		frRegistroAnimales.getContentPane().add(lblFamilia);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(32, 182, 146, 14);
		frRegistroAnimales.getContentPane().add(lblAltura);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(32, 220, 146, 14);
		frRegistroAnimales.getContentPane().add(lblPeso);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(171, 65, 164, 20);
		frRegistroAnimales.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtNombreCientifico = new JTextField();
		txtNombreCientifico.setBounds(171, 102, 164, 20);
		frRegistroAnimales.getContentPane().add(txtNombreCientifico);
		txtNombreCientifico.setColumns(10);
		
		txtFamilia = new JTextField();
		txtFamilia.setBounds(171, 141, 164, 20);
		frRegistroAnimales.getContentPane().add(txtFamilia);
		txtFamilia.setColumns(10);
		
		txtAltura = new JTextField();
		txtAltura.setBounds(171, 179, 164, 20);
		frRegistroAnimales.getContentPane().add(txtAltura);
		txtAltura.setColumns(10);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(171, 217, 164, 20);
		frRegistroAnimales.getContentPane().add(txtPeso);
		txtPeso.setColumns(10);
		
		JButton btnLimpiar = new JButton("Limpiar\r\nFormulario");
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Limpiar el fomrulario
				limpiarCampos();
			}
		});
		btnLimpiar.setBounds(119, 266, 99, 68);
		frRegistroAnimales.getContentPane().add(btnLimpiar);
		
		JButton btnAgregar = new JButton("Agregar\r\nRegistro");
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Animal animal = new Animal();
				
				if(validarNombre()&&validarNombreC()
					&&validarFamilia()&&validarAltura()
					&&validarPeso())
				{
					
					JOptionPane.showMessageDialog(null,
							"Ingreso de datos");
					//Capturamos la información del formulario
					animal.setNombre(txtNombre.getText());
					animal.setNombreCientifico(txtNombreCientifico.getText());
					animal.setFamilia(txtFamilia.getText());
					animal.setAltura(Integer.parseInt(txtAltura.getText()));
					animal.setPeso(Float.parseFloat(txtPeso.getText()));
					
					//Agregamos a la base de datos la información
					if(db.createAnimal(animal)) {
						JOptionPane.showMessageDialog(null, "Valor agregado correctamente");
					}
					
					//La información capturada se manda a la lista
					registroAnimales.add(animal);
					
					//Funcion para limpiar campos
					limpiarCampos();
				}
				
				JOptionPane.showMessageDialog(null,
						"Me encuentro aca");
			}
		});
		
		btnAgregar.setBounds(10, 266, 99, 68);
		frRegistroAnimales.getContentPane().add(btnAgregar);
		
		JButton btnMostrar = new JButton("Mostrar\r\nRegistros");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Tomamos el modelo de la tabla y lo almacenamos
				DefaultTableModel modelo = (DefaultTableModel) tblRegistroAnimales.getModel();
				//Hacemos un contador de filas
				int contadorFilas = modelo.getRowCount();
				//Creamos un objeto especial generico
				Object rowData[] = new Object[5];
				
				//Si hay mas de una fila
				if(contadorFilas>=0) {
					//Entonces que me remueva las filas que encuentre
					for(int i = contadorFilas-1; i>=0; i--) {
						modelo.removeRow(i);
					}
				}
				//Dibujamos en nuestra tabla cada objeto que hemos guardado
				for(Animal an : registroAnimales) {
					rowData[0] = an.getNombre();
					rowData[1] = an.getNombreCientifico();
					rowData[2] = an.getFamilia();
					rowData[3] = an.getAltura();
					rowData[4] = an.getPeso();
					
					//Luego de llenado el objeto, lo manda a la tabla
					modelo.addRow(rowData);
				}
			}
		});
		btnMostrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMostrar.setBounds(228, 266, 99, 68);
		frRegistroAnimales.getContentPane().add(btnMostrar);
		
		tblRegistroAnimales = new JTable();
		tblRegistroAnimales.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Nombre Cientifico", "Familia", "Altura", "Peso"
			}
		));
		tblRegistroAnimales.getColumnModel().getColumn(0).setPreferredWidth(74);
		tblRegistroAnimales.getColumnModel().getColumn(0).setMaxWidth(2147483639);
		tblRegistroAnimales.setBounds(387, 63, 374, 271);
		frRegistroAnimales.getContentPane().add(tblRegistroAnimales);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	private void limpiarCampos() {
		txtNombre.setText("");
		txtNombreCientifico.setText("");
		txtFamilia.setText("");
		txtAltura.setText("");
		txtPeso.setText("");
	}
	
	private boolean validarNombre() {
		try {
			Float.parseFloat(txtNombre.getText());
			
			JOptionPane.showMessageDialog(null,
					"Por favor ingrese un nombre valido");
			
			return false;
		}catch(NumberFormatException ex){
			System.out.println(ex.toString());
			JOptionPane.showMessageDialog(null,
					"Nombre esta correcto");
			return true;
		}
	}
	
	private boolean validarNombreC() {
		try {
			if(txtNombreCientifico.getText() != null) {
				Float.parseFloat(txtNombreCientifico.getText());
				
				JOptionPane.showMessageDialog(null,
						"Por favor ingrese un nombre cientifico valido");
				return false;
			}
			else {
				JOptionPane.showMessageDialog(null,
						"Por favor ingrese un nombre cientifico");
				return false;
			}
			
		}catch(NumberFormatException ex){
			System.out.println(ex.toString());
			JOptionPane.showMessageDialog(null,
					"Nombre Cientifico esta correcto");
			return true;
		}
	}
	
	private boolean validarFamilia() {
		try {
			Float.parseFloat(txtFamilia.getText());
			
			JOptionPane.showMessageDialog(null,
					"Por favor ingrese una familia valida");
			
			return false;
		}catch(NumberFormatException ex){
			System.out.println(ex.toString());
			JOptionPane.showMessageDialog(null,
					"Familia esta correcto");
			return true;
		}
	}
	
	private boolean validarAltura() {
		try {
			//Variable temporal
			float temp;
			temp = Float.parseFloat(txtAltura.getText());
			
			if(temp<=0) {
				JOptionPane.showMessageDialog(null,
						"Ingrese una altura positiva");
				return false;
			}
			
			return true;
		}catch(NumberFormatException ex) {
			System.out.println(ex.toString());
			JOptionPane.showMessageDialog(null,
					"Ingrese un número por favor");
			return false;
		}
	}
	
	private boolean validarPeso() {
		try {
			//Variable temporal
			float temp;
			temp = Float.parseFloat(txtPeso.getText());
			
			if(temp<=0) {
				JOptionPane.showMessageDialog(null,
						"Ingrese un peso positivo");
				return false;
			}
			
			return true;
		}catch(NumberFormatException ex) {
			System.out.println(ex.toString());
			JOptionPane.showMessageDialog(null,
					"Ingrese un número por favor");
			return false;
		}
	}
}
