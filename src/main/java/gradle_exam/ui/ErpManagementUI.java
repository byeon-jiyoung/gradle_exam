package gradle_exam.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradle_exam.dao.DepartmentDao;
import gradle_exam.dto.Department;
import gradle_exam.ui.content.PanelDepartment;
import gradle_exam.ui.content.PanelTitle;

@SuppressWarnings("serial")
public class ErpManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnEmp;
	private JButton btnDept;
	private JButton btnTitle;
	
	private DepartmentUI frameDept;
	private TitleUI frameTitle;
	private EmployeeUI frameEmp;
	
	
	public ErpManagementUI() {
		initComponents();
	}
	
	private void initComponents() {
		setTitle("ERP관리프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 10, 0));
		
		btnEmp = new JButton("사원관리");
		btnEmp.addActionListener(this);
		contentPane.add(btnEmp);
		
		btnDept = new JButton("부서관리");
		btnDept.addActionListener(this);
		contentPane.add(btnDept);
		
		btnTitle = new JButton("직책관리");
		btnTitle.addActionListener(this);
		contentPane.add(btnTitle);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEmp) {
			actionPerformedBtnEmp(e);
		}
		try {
			if (e.getSource() == btnTitle) {
				actionPerformedBtnTitle(e);
			}
			if (e.getSource() == btnDept) {
				actionPerformedBtnDept(e);
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
	
	protected void actionPerformedBtnDept(ActionEvent e) throws SQLException {
		if(frameDept == null) {
			frameDept = new DepartmentUI();
		}
		frameDept.setClear();
		frameDept.setVisible(true);
	}
	
	protected void actionPerformedBtnTitle(ActionEvent e) throws SQLException {
		if(frameTitle == null) {
			frameTitle = new TitleUI();
		}
		frameTitle.setClear();
		frameTitle.setVisible(true);
	}
	
	protected void actionPerformedBtnEmp(ActionEvent e) {
		if(frameEmp == null) {
			frameEmp = new EmployeeUI();
		}
		frameEmp.clearPanelEmployee();
		frameEmp.setVisible(true);
	}
}
