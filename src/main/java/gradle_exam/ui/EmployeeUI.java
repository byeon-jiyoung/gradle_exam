package gradle_exam.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import gradle_exam.dao.DepartmentDao;
import gradle_exam.dao.EmployeeDao;
import gradle_exam.dao.TitleDao;
import gradle_exam.daoImpl.DepartmentDaoImpl;
import gradle_exam.daoImpl.EmployeeDaoImpl;
import gradle_exam.daoImpl.TitleDaoImpl;
import gradle_exam.dto.Department;
import gradle_exam.dto.Employee;
import gradle_exam.dto.Title;
import gradle_exam.ui.content.PanelEmployee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import gradle_exam.ui.content.PanelEmployeeList;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class EmployeeUI extends JFrame implements ActionListener {

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
	
	private JTable table;

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	
	public EmployeeUI() {
		empdao = new EmployeeDaoImpl();
		deptdao = new DepartmentDaoImpl();
		titledao = new TitleDaoImpl();
		try {
			empList = empdao.selectEmployeeByAll();
			if(empList == null) {
				empList = new ArrayList<Employee>();
			}
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
		pEmp.setEmpList(empList);
		pEmp.setDeptCmbModel(deptList);
		pEmp.setTitleCmbModel(titleList);
		
		pEmp.setTfEmpNo();
		contentPane.add(pEmp);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
		pEmpList = new PanelEmployeeList();
		pEmpList.setEmpList(empList);
		
		pEmpList.reloadData();
		pEmpList.setParent(this);
		contentPane.add(pEmpList);
		pEmpList.setLayout(new GridLayout(1, 0, 0, 0));
	}

	public void updateEmpUI(Employee searchEmp) {
		pEmp.setEmp(searchEmp);
		btnAdd.setText("수정");
		
	}

	public void deleteEmpUI(Employee searchEmp) {
		try {
			empdao.deleteEmployee(searchEmp);
			JOptionPane.showMessageDialog(null, String.format("%s 사원이 삭제되었습니다.", searchEmp.getEmpName()));
			btnAdd.setText("추가");
			
			pEmp.clearEmp();
			pEmpList.setEmpList(empdao.selectEmployeeByAll());
			pEmpList.reloadData();
			
			empList = empdao.selectEmployeeByAll();
			pEmp.setTfEmpNo();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnAdd) {
				if(btnAdd.getText().equals("추가")) {
					actionPerformedBtnAdd(e);
				}else {
					actionPerformedBtnUpdate(e);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
	}
	
	private void actionPerformedBtnUpdate(ActionEvent e) throws Exception {
		Employee newEmp = pEmp.getEmp();
		empdao.updateEmployee(newEmp);
		
		JOptionPane.showMessageDialog(null, String.format("%s 사원이 수정되었습니다.", newEmp.getEmpName()));
		
		pEmp.clearEmp();
		pEmpList.setEmpList(empdao.selectEmployeeByAll());
		pEmpList.reloadData();
		
		empList = empdao.selectEmployeeByAll();
		pEmp.setTfEmpNo();
		
		btnAdd.setText("추가");
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pEmp.clearEmp();
		pEmp.setTfEmpNo();
	}
	
	protected void actionPerformedBtnAdd(ActionEvent e) throws Exception {
		Employee newEmp;
		try {
			newEmp = pEmp.getEmp();
			empdao.insertEmployee(newEmp);
			JOptionPane.showMessageDialog(null,String.format("%s 사원이 추가되었습니다.", newEmp.getEmpName()));
			
			pEmp.clearEmp();
			pEmpList.setEmpList(empdao.selectEmployeeByAll());
			pEmpList.reloadData();
			
			empList = empdao.selectEmployeeByAll();
			pEmp.setTfEmpNo();
			//JOptionPane.showMessageDialog(null,empList+"frame");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
	
	public void clearPanelEmployee() {
		pEmp.clearEmp();
		pEmp.setTfEmpNo();
	}
}
