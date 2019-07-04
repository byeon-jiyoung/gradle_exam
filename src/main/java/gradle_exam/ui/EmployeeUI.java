package gradle_exam.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradle_exam.dao.DepartmentDao;
import gradle_exam.dao.EmployeeDao;
import gradle_exam.dao.TitleDao;
import gradle_exam.daoImpl.DepartmentDapImpl;
import gradle_exam.daoImpl.EmployeeDaoImpl;
import gradle_exam.daoImpl.TitleDaoImpl;
import gradle_exam.dto.Department;
import gradle_exam.dto.Employee;
import gradle_exam.dto.Title;
import gradle_exam.ui.content.PanelEmployee;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import gradle_exam.ui.content.PanelEmployeeList;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class EmployeeUI extends JFrame {

	private JPanel contentPane;
	private JButton btnAdd;
	private JButton btnCancel;
	private PanelEmployee pEmp;
	private PanelEmployeeList pEmpList;
	
	private EmployeeDao empdao;
	private List<Employee> empList;
	private DepartmentDao deptdao;
	private List<Department> deptList;
	private TitleDao titledao;
	private List<Title> titleList;
	
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	
	public EmployeeUI() {
		empdao = new EmployeeDaoImpl();
		deptdao = new DepartmentDapImpl();
		titledao = new TitleDaoImpl();
		try {
			empList = empdao.selectEmployeeByAll();
			deptList = deptdao.selectDepartmentByAll();
			titleList = titledao.selectTitleByAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initComponents();
	}
	
	private void initComponents() {
		setTitle("사원 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pEmp = new PanelEmployee();
		contentPane.add(pEmp);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
		
		pEmpList = new PanelEmployeeList();
		pEmpList.setEmpList(empList);
		pEmpList.reloadData();
		pEmpList.setParent(this);
		contentPane.add(pEmpList);
		pEmpList.setLayout(new GridLayout(1, 0, 0, 0));
	}

	public void updateEmpUI(Employee searchEmp) {
		pEmp.setEmployee(searchEmp);
		btnAdd.setText("수정");
		////////////////////////////////////////		
	}

	public void deleteEmpUI(Employee searchEmp) {
		try {
			empdao.deleteEmployee(searchEmp);
			JOptionPane.showMessageDialog(null, String.format("%s 사원이 삭제되었습니다.", searchEmp.getEmpName()));
			btnAdd.setText("추가");
			/////////////////////////////////////////////	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
