package gradle_exam.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import gradle_exam.ui.content.PanelDepartment;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import gradle_exam.ui.content.PanelDepartmentList;
import java.awt.FlowLayout;

public class DepartmentUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentUI frame = new DepartmentUI();
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
	public DepartmentUI() {
		initComponents();
	}
	private void initComponents() {
		setTitle("부서 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		PanelDepartment pDept = new PanelDepartment();
		contentPane.add(pDept, BorderLayout.NORTH);
		
		JPanel pBtns = new JPanel();
		FlowLayout fl_pBtns = (FlowLayout) pBtns.getLayout();
		fl_pBtns.setVgap(10);
		contentPane.add(pBtns, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("New button");
		pBtns.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		pBtns.add(btnNewButton_1);
		
		PanelDepartmentList pList = new PanelDepartmentList();
		contentPane.add(pList, BorderLayout.SOUTH);
	}

}
