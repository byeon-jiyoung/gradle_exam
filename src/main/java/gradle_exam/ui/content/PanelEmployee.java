package gradle_exam.ui.content;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import gradle_exam.dao.EmployeeDao;
import gradle_exam.daoImpl.EmployeeDaoImpl;
import gradle_exam.dto.Department;
import gradle_exam.dto.Employee;
import gradle_exam.dto.Title;
import gradle_exam.ui.EmployeeUI;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
	
	private List<Employee> empList;
	private EmployeeDao dao;
	
	Date date = new Date();
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	String date_str = sd.format(date);
	
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	
	public PanelEmployee() {
		dao = new EmployeeDaoImpl();
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
		pgender.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
	
	public void clearEmp() {
		tfEmpName.setText("");
		cmbTitle.setSelectedIndex(-1);
		spSalary.setValue(1500000);
		man.setSelected(true);
		cmbDept.setSelectedIndex(-1);
		tfDate.setText(date_str);
	}
	
	public void setTfEmpNo() {
		SimpleDateFormat sd = new SimpleDateFormat("yy");
		String d = sd.format(date);
		//JOptionPane.showMessageDialog(null,empList);
		
		try {
			empList = dao.selectEmployeeByAll();
			
			if(empList == null) {
				tfEmpNo.setText(String.format("E0"+d+"001"));
			}else {
				int empno = empList.get(empList.size()-1).getEmpNo();
				System.out.println(empno);
				tfEmpNo.setText(String.format("E0"+d+"%03d", empno+1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tfEmpNo.setEnabled(false);
	}
	
	public Employee getEmp() throws Exception {
		validCheck();
		
		//JOptionPane.showMessageDialog(null, tfEmpNo);
		String tfempno = tfEmpNo.getText().substring(4);
		int empno = Integer.parseInt(tfempno);
		String empname = tfEmpName.getText().trim();
		Title title = (Title) cmbTitle.getSelectedItem();
		System.out.println(title);
		int salary = (int) spSalary.getValue();
		int gender = man.isSelected()?1:0;
		Department dept = (Department) cmbDept.getSelectedItem();
		Date date = null;
		try {
			date = sd.parse(tfDate.getText().trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Employee(empno, empname, title, salary, gender, dept, date);
	}

	private void validCheck() throws Exception {
		if (tfEmpName.getText().equals("")) {
			throw new Exception("이름이 비었습니다");
		}
		if(cmbTitle.getSelectedItem() == null) {
			throw new Exception("직책을 선택하세요.");
		}
		if(cmbDept.getSelectedItem() == null) {
			throw new Exception("부서를 선택하세요.");
		}
		try {
			date = sd.parse(tfDate.getText().trim());
		} catch (ParseException e) {
			throw new Exception("날짜가 비었습니다.");
		}
	}

	public void setEmp(Employee emp) {
		SimpleDateFormat sd = new SimpleDateFormat("yy");
		String d = sd.format(date);
		
		tfEmpNo.setText(String.format("E0"+d+"%03d", emp.getEmpNo()));
		tfEmpName.setText(emp.getEmpName());
		cmbTitle.setSelectedItem(emp.getTitle());
		spSalary.setValue(emp.getSalary());
		cmbDept.setSelectedItem(emp.getDno());
		tfDate.setText(String.valueOf(emp.getHire_date()));
		
		//System.out.println(emp.getGender());
		
		if(emp.getGender()==0) {
			woman.setSelected(true);
		}else {
			man.setSelected(true);
		}
	}
}
