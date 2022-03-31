package sv.edu.udb.ejecutar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import sv.edu.udb.clases.*;

public class RegistroPlanta {

	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtNombreCientifico;
	private JTextField txtFamilia;
	private JTextField txtAltura;
	private JTextField txtTipoPlanta;
	private JTable tblPlantas;
	private ArrayList<Planta> listaPlantas = new ArrayList<Planta>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroPlanta window = new RegistroPlanta();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistroPlanta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 806, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registro de Plantas");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblTitulo.setBounds(10, 10, 772, 51);
		frame.getContentPane().add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNombre.setBounds(10, 87, 98, 13);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblNombreCientifico = new JLabel("Nombre Cientifico");
		lblNombreCientifico.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNombreCientifico.setBounds(10, 123, 135, 13);
		frame.getContentPane().add(lblNombreCientifico);
		
		JLabel lblFamilia = new JLabel("Familia");
		lblFamilia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblFamilia.setBounds(10, 158, 98, 13);
		frame.getContentPane().add(lblFamilia);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAltura.setBounds(10, 194, 98, 13);
		frame.getContentPane().add(lblAltura);
		
		JLabel lblTipoPlanta = new JLabel("Tipo Planta");
		lblTipoPlanta.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTipoPlanta.setBounds(10, 234, 98, 13);
		frame.getContentPane().add(lblTipoPlanta);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(155, 85, 149, 19);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtNombreCientifico = new JTextField();
		txtNombreCientifico.setBounds(155, 121, 149, 19);
		frame.getContentPane().add(txtNombreCientifico);
		txtNombreCientifico.setColumns(10);
		
		txtFamilia = new JTextField();
		txtFamilia.setBounds(155, 156, 149, 19);
		frame.getContentPane().add(txtFamilia);
		txtFamilia.setColumns(10);
		
		txtAltura = new JTextField();
		txtAltura.setBounds(155, 192, 149, 19);
		frame.getContentPane().add(txtAltura);
		txtAltura.setColumns(10);
		
		txtTipoPlanta = new JTextField();
		txtTipoPlanta.setBounds(155, 232, 149, 19);
		frame.getContentPane().add(txtTipoPlanta);
		txtTipoPlanta.setColumns(10);
		
		JButton btnAgregarPlanta = new JButton("Agregar Planta");
		btnAgregarPlanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//Declaramos un objeto para almacenar
				Planta nuevaPlanta = new Planta();
				
				//Almacenamos los valores de los textbox en un objeto
				nuevaPlanta.setNombre(txtNombre.getText());
				nuevaPlanta.setNombreCientifico(txtNombreCientifico.getText());
				nuevaPlanta.setFamilia(txtFamilia.getText());
				nuevaPlanta.setAltura(Float.parseFloat(txtAltura.getText()));
				nuevaPlanta.setTipoPlanta(txtTipoPlanta.getText());
				
				listaPlantas.add(nuevaPlanta);
				
				//Limpiamos campos
				txtNombre.setText("");
				txtNombreCientifico.setText("");
				txtFamilia.setText("");
				txtAltura.setText("");
				txtTipoPlanta.setText("");
			}
		});
		btnAgregarPlanta.setBounds(10, 292, 98, 21);
		frame.getContentPane().add(btnAgregarPlanta);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Limpiamos campos
				txtNombre.setText("");
				txtNombreCientifico.setText("");
				txtFamilia.setText("");
				txtAltura.setText("");
				txtTipoPlanta.setText("");
			}
		});
		btnLimpiar.setBounds(121, 292, 103, 21);
		frame.getContentPane().add(btnLimpiar);
		
		JButton btnMostrarInformacion = new JButton("Mostrar Informacion");
		btnMostrarInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Obtenemos el modelo de la tabla
				DefaultTableModel modelo = (DefaultTableModel) tblPlantas.getModel();
				//Creamos un objeto generico
				Object plant[] = new Object[5];
				//Contador de filas del objeto
				int contadorFilas = modelo.getRowCount();
				
				//Revisa si hay mas de una fila
				if(contadorFilas>=0) {
					//Entonces que me remueva las filas que encuentre
					for(int i = contadorFilas-1; i>=0; i--) {
						modelo.removeRow(i);
					}
				}
				
				//Dibujamos el arreglo en la tabla
				for(Planta temp : listaPlantas) {
					plant[0] = temp.getNombre();
					plant[1] = temp.getNombreCientifico();
					plant[2] = temp.getFamilia();
					plant[3] = temp.getAltura();
					plant[4] = temp.getTipoPlanta();
					
					modelo.addRow(plant);
				}
			}
		});
		btnMostrarInformacion.setBounds(234, 292, 98, 21);
		frame.getContentPane().add(btnMostrarInformacion);
		
		tblPlantas = new JTable();
		tblPlantas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		tblPlantas.setBounds(378, 87, 375, 226);
		frame.getContentPane().add(tblPlantas);
	}
}
