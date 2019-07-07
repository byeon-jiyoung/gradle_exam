package gradle_exam.ui.content;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradle_exam.dao.DepartmentDao;
import gradle_exam.daoImpl.DepartmentDaoImpl;
import gradle_exam.dto.Department;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class Test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Test() throws SQLException, InterruptedException {
		initComponents();
	}
	
	private void initComponents() throws SQLException, InterruptedException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JComboBox<Department> comboBox = new JComboBox<>();
		DepartmentDao dao = new DepartmentDaoImpl();
		Vector<Department> v = new Vector<Department>(dao.selectDepartmentByAll());
		DefaultComboBoxModel<Department> model = new DefaultComboBoxModel<Department>(v);
		
		
		comboBox.setModel(model);
		contentPane.add(comboBox, BorderLayout.NORTH);
	}

}
