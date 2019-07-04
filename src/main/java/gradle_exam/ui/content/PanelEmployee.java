package gradle_exam.ui.content;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import gradle_exam.dto.Department;
import gradle_exam.dto.Employee;
import gradle_exam.dto.Title;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class PanelEmployee extends JPanel {
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JComboBox<Title> cmbTitle;
	private JComboBox<Department> cmbDept;
	private JTextField tfDate;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JSpinner spSalary;
	private JRadioButton man;
	private JRadioButton woman;
	
	private DefaultComboBoxModel<Title> titleCmbModel;
	private DefaultComboBoxModel<Department> deptCmbModel;
	
	private SpinnerModel numberModel = new SpinnerNumberModel(1500000, 1000000, 5000000, 100000);
	
	public PanelEmployee() {
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pEmp = new JPanel();
		add(pEmp);
		pEmp.setLayout(new GridLayout(0, 2, 10, 5));
		
		JLabel lblEmpNo = new JLabel("번호");
		pEmp.add(lblEmpNo);
		lblEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfEmpNo = new JTextField();
		pEmp.add(tfEmpNo);
		tfEmpNo.setColumns(10);
		
		JLabel lblEmpName = new JLabel("사원명");
		pEmp.add(lblEmpName);
		lblEmpName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfEmpName = new JTextField();
		pEmp.add(tfEmpName);
		tfEmpName.setColumns(10);
		
		JLabel lblTitle = new JLabel("직책");
		pEmp.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cmbTitle = new JComboBox<Title>();
		pEmp.add(cmbTitle);
		
		JLabel lblSalary = new JLabel("급여");
		pEmp.add(lblSalary);
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spSalary = new JSpinner(numberModel);
		pEmp.add(spSalary);
		
		JLabel lblGender = new JLabel("성별");
		pEmp.add(lblGender);
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel pgender = new JPanel();
		pEmp.add(pgender);
		pgender.setLayout(new GridLayout(0, 2, 0, 0));
		
		man = new JRadioButton("남", true);
		buttonGroup.add(man);
		pgender.add(man);
		
		woman = new JRadioButton("여");
		buttonGroup.add(woman);
		pgender.add(woman);
		
		JLabel lblDept = new JLabel("부서");
		pEmp.add(lblDept);
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cmbDept = new JComboBox<Department>();
		pEmp.add(cmbDept);
		
		JLabel lblDate = new JLabel("입사일");
		pEmp.add(lblDate);
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfDate = new JTextField();
		pEmp.add(tfDate);
		tfDate.setColumns(10);
	}
	
	public void setEmployee(Employee employee) {
		tfEmpNo.setText(employee.getEmpNo()+"");
		tfEmpNo.setEnabled(false);
		tfEmpName.setText(employee.getEmpName());
		cmbTitle.setSelectedItem(employee.getTitle());
	}
	
	public void setDeptCmbModel(List<Department> deptList) {
		deptCmbModel = new DefaultComboBoxModel<Department>(new Vector<Department>(deptList));
		cmbDept.setModel(deptCmbModel);
	}
	
	public JComboBox<Department> getCmbDept() {
		return cmbDept;
	}
	
	public void setTitleCmbModel(List<Title> titleList) {
		titleCmbModel = new DefaultComboBoxModel<Title>(new Vector<Title>(titleList));
		cmbTitle.setModel(titleCmbModel);
	}
	
	public JComboBox<Title> getCmbManager() {
		return cmbTitle;
	}
}
