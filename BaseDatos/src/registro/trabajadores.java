package registro;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import java.awt.Panel;
import java.awt.Label;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import registro.Conexion;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

public class trabajadores extends JFrame {

	ButtonGroup btnGr;
	
	private JPanel contentPane;
	private JTable tblEmpleados;
	private JTextField tnombres;
	private JTextField tarea;
	private JTextField ttelefono;
	private JTextField temail;
	private JTextField textId;
	private JRadioButton rbactivo;
	private JRadioButton rbinactivo;
	private JPanel panel;
	private JButton btnGuardar;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblTelefono;
	private JLabel lblActivo;
	private JLabel lblNewLabel_3;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	/**
	 * Launch the application.
	 */
	
	private void limpiar() {
		textId.setText("");
		tnombres.setText("");
		tarea.setText("");
		ttelefono.setText("");
		temail.setText("");
		btnGr.clearSelection();
	}

	private void cargarTabla() {
		
		DefaultTableModel modeloTabla = (DefaultTableModel) tblEmpleados.getModel();
		modeloTabla.setRowCount(0);
		
		PreparedStatement ps;
		ResultSet rs;
		ResultSetMetaData rsmd;
		int columnas;
		
		int[] anchos = {12,150,100,30,100};
		for(int i=0; i<tblEmpleados.getColumnCount();i++) {
			tblEmpleados.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
		}
		
		try {
			Connection con = Conexion.getConexion();
			ps = con.prepareStatement("SELECT id, nombre, area, activo, email FROM empleados");
			rs = ps.executeQuery();
			rsmd = (ResultSetMetaData) rs.getMetaData();
			columnas = rsmd.getColumnCount();
			
			while(rs.next()) {
				Object[] fila = new Object[columnas];
				for(int indice=0; indice<columnas;indice++) {
					fila[indice]=rs.getObject(indice+1);
				}
				modeloTabla.addRow(fila);
			}
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trabajadores frame = new trabajadores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public trabajadores() {
		setTitle("EMPRESA PUBLICITARIA");

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 607);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("PERSONAL DE EMPRESA PUBLICITARIA");
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		
		panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DATOS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Nombres:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 33, 67, 14);
		panel.add(lblNewLabel_1);
		
		tnombres = new JTextField();
		tnombres.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tnombres.setBounds(163, 29, 338, 23);
		panel.add(tnombres);
		tnombres.setColumns(10);
		
		tarea = new JTextField();
		tarea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tarea.setBounds(163, 66, 338, 23);
		panel.add(tarea);
		tarea.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Area");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 70, 67, 14);
		panel.add(lblNewLabel_2);
		
		lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblTelefono.setBounds(10, 111, 108, 14);
		panel.add(lblTelefono);
		
		ttelefono = new JTextField();
		ttelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		ttelefono.setBounds(162, 107, 339, 23);
		panel.add(ttelefono);
		ttelefono.setColumns(10);
		
		rbactivo = new JRadioButton("Activo");
		rbactivo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbactivo.setBackground(Color.WHITE);
		rbactivo.setBounds(221, 180, 87, 23);
		panel.add(rbactivo);
		
		rbinactivo = new JRadioButton("Inactivo");
		rbinactivo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbinactivo.setBackground(Color.WHITE);
		rbinactivo.setBounds(348, 180, 100, 23);
		panel.add(rbinactivo);
		
		temail = new JTextField();
		temail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		temail.setBounds(163, 144, 338, 23);
		panel.add(temail);
		temail.setColumns(10);
		
		lblNewLabel_3 = new JLabel("E-mail:");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 148, 46, 14);
		panel.add(lblNewLabel_3);
		
		lblActivo = new JLabel("Activo:");
		lblActivo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblActivo.setBounds(10, 184, 46, 14);
		panel.add(lblActivo);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setForeground(new Color(0, 0, 0));
		btnGuardar.setBorder(null);
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBackground(new Color(250, 240, 230));
		btnGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGuardar.setIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoFinal2\\src\\iconos\\btnagregar.png"));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = tnombres.getText();
				String area = tarea.getText();
				int telefono = Integer.parseInt(ttelefono.getText());
				String email = temail.getText();
				String activo;
				
				if(rbactivo.isSelected()==true) {
					activo = "A";
				} else if (rbinactivo.isSelected()==true) {
					activo = "I";
				} else {
					activo = "A";
				}
				
				try {
					Connection con = Conexion.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO empleados (nombre , area, activo, email, telefono) VALUES (?,?,?,?,?)");
					ps.setString(1, nombre);
					ps.setString(2, area);
					ps.setString(3, activo);
					ps.setString(4, email);
					ps.setInt(5, telefono);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "DATOS GUARDADOS");
					limpiar();
					cargarTabla();
					
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}
			}
		});
		btnGuardar.setBounds(562, 29, 87, 29);
		panel.add(btnGuardar);
		
		btnModificar = new JButton("MODIFICAR");
		btnModificar.setBackground(new Color(250, 240, 230));
		btnModificar.setBorder(null);
		btnModificar.setFocusPainted(false);
		btnModificar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModificar.setIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoFinal2\\src\\iconos\\btneditar.png"));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(textId.getText());
				String nombre = tnombres.getText();
				String area = tarea.getText();
				int telefono = Integer.parseInt(ttelefono.getText());
				String email = temail.getText();
				String activo;
				
				if(rbactivo.isSelected()==true) {
					activo = "A";
				} else if (rbinactivo.isSelected()==true) {
					activo = "I";
				} else {
					activo = "A";
				}
				
				try {
					Connection con = Conexion.getConexion();
					PreparedStatement ps = con.prepareStatement("UPDATE empleados SET nombre=? , area=?, activo=?, email=?, telefono=? WHERE id=?");
					ps.setString(1, nombre);
					ps.setString(2, area);
					ps.setString(3, activo);
					ps.setString(4, email);
					ps.setInt(5, telefono);
					ps.setInt(6, id);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS");
					limpiar();
					cargarTabla();
					
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				
			}
		}});
		btnModificar.setBounds(562, 69, 87, 30);
		panel.add(btnModificar);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBorderPainted(false);
		btnEliminar.setBackground(new Color(250, 240, 230));
		btnEliminar.setFocusPainted(false);
		btnEliminar.setBorder(null);
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar.setIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoFinal2\\src\\iconos\\btnborrar.png"));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(textId.getText());
				
				try {
					Connection con = Conexion.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM empleados WHERE id=?");
					ps.setInt(1, id);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "DATOS ELIMINADOS");
					limpiar();
					cargarTabla();
					
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				
			}
			}
		});
		btnEliminar.setBounds(562, 111, 87, 29);
		panel.add(btnEliminar);
		
		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setBackground(new Color(250, 240, 230));
		btnLimpiar.setBorder(null);
		btnLimpiar.setFocusPainted(false);
		btnLimpiar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLimpiar.setIcon(new ImageIcon("C:\\Users\\Administrador\\eclipse-workspace\\ProyectoFinal2\\src\\iconos\\btnlimpiar.png"));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(562, 151, 87, 29);
		panel.add(btnLimpiar);
		
		textId = new JTextField();
		textId.setBounds(489, 32, 23, 17);
		panel.add(textId);
		textId.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setForeground(Color.BLACK);
		
		JLabel label = new JLabel("");
		
		JButton Logo = new JButton("");
		Logo.setIcon(new ImageIcon("C:\\Users\\Usuario\\Pictures\\publicitaria.png"));
		Logo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Logo.setHorizontalTextPosition(SwingConstants.CENTER);
		Logo.setForeground(Color.BLACK);
		Logo.setFocusPainted(false);
		Logo.setBorder(null);
		Logo.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label)
									.addGap(18, 95, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(Logo, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGap(18)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
							.addGap(43))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(Logo, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addComponent(label)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		tblEmpleados = new JTable();
		tblEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tblEmpleados.setBorder(null);
		tblEmpleados.setSelectionForeground(Color.BLACK);
		tblEmpleados.setSelectionBackground(new Color(245, 245, 220));
		tblEmpleados.setGridColor(Color.GRAY);
		tblEmpleados.setBackground(Color.WHITE);
		tblEmpleados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int fila = tblEmpleados.getSelectedRow();
					int id = Integer.parseInt(tblEmpleados.getValueAt(fila,0).toString());
					PreparedStatement ps;
					ResultSet rs;
					Connection con = Conexion.getConexion();
					ps = con.prepareStatement("SELECT id,nombre, area, activo, email,telefono  FROM empleados WHERE id=?");
					ps.setInt(1, id);
					rs = ps.executeQuery();
					
					while(rs.next()) {
						textId.setText(String.valueOf(id));
						tnombres.setText(rs.getString("nombre"));
						tarea.setText(rs.getString("area"));
						ttelefono.setText(rs.getString("telefono"));
						temail.setText(rs.getString("email"));
						if(rs.getString("activo").equals("A")) {
							rbactivo.setSelected(true);
						}else if(rs.getString("activo").equals("I")) {
							rbinactivo.setSelected(true);
						}
					}
				}catch(SQLException e1){
					JOptionPane.showMessageDialog(null,  e1.toString());
				}
			}
		});
		scrollPane.setViewportView(tblEmpleados);
		tblEmpleados.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Area", "Activo/Inactivo", "E-mail"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		contentPane.setLayout(gl_contentPane);
	
		textId.setVisible(false);
		btnGr = new ButtonGroup();
		btnGr.add(rbactivo);
		btnGr.add(rbinactivo);
		cargarTabla();
	}
	public JRadioButton getRbMaculino() {
		return rbactivo;
	}
	public JRadioButton getRbFemenino() {
		return rbinactivo;
	}
	public JPanel getPanel() {
		return panel;
	}
	public JButton getBtnGuardar() {
		return btnGuardar;
	}
	public JTable getTblAlumnos() {
		return tblEmpleados;
	}
	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}
	public JTextField getTxtMatricula() {
		return tnombres;
	}
	public JTextField getTxtNombre() {
		return tarea;
	}
	public JLabel getLblNewLabel_2() {
		return lblNewLabel_2;
	}
	public JLabel getLblEdad() {
		return lblTelefono;
	}
	public JTextField getTxtEdad() {
		return ttelefono;
	}
	public JTextField getTxtEmail() {
		return temail;
	}
	public JLabel getLblSexo() {
		return lblActivo;
	}
	public JLabel getLblNewLabel_3() {
		return lblNewLabel_3;
	}
	public JButton getBtnModificar() {
		return btnModificar;
	}
	public JButton getBtnEliminar() {
		return btnEliminar;
	}
	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}
}