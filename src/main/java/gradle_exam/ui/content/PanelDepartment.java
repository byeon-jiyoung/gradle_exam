package gradle_exam.ui.content;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gradle_exam.dto.Department;

@SuppressWarnings("serial")
public class PanelDepartment extends JPanel {
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;
	
	private Department nextDept;
	private Department searchDeptNo;

	
	public void setSearchDeptNo(Department searchDeptNo) {
		this.searchDeptNo = searchDeptNo;
	}

	public PanelDepartment() {
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new GridLayout(0, 2, 10, 5));
		
		JLabel lblDeptNo = new JLabel("번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDeptNo);
		
		tfDeptNo = new JTextField();
		add(tfDeptNo);
		tfDeptNo.setColumns(10);
		
		JLabel lblDeptName = new JLabel("부서명");
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDeptName);
		
		tfDeptName = new JTextField();
		tfDeptName.setColumns(10);
		add(tfDeptName);
		
		JLabel lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFloor);
		
		tfFloor = new JTextField();
		tfFloor.setColumns(10);
		add(tfFloor);
	}
	
	/*
	public void setDepartment(Department department) {
		tfDeptNo.setText(department.getDeptNo()+"");
		tfDeptName.setText(department.getDeptName());
		tfFloor.setText(department.getFloor()+"");
	}
	*/
	
	public Department getDepartmnet() { //insert - panel에 있는 정보 불러와서 객체로 만듦
		int deptno = nextDept.getDeptNo()+1;
		String deptname = tfDeptName.getText().trim();
		int floor = Integer.parseInt(tfFloor.getText().trim());
		
		return new Department(deptno, deptname, floor);
	}
	
	public Department getUpdateDepartmnet() { //update - panel에 있는 정보 불러와서 객체로 만듦
		int deptno = searchDeptNo.getDeptNo();
		String deptname = tfDeptName.getText().trim();
		int floor = Integer.parseInt(tfFloor.getText().trim());
		
		return new Department(deptno, deptname, floor);
	}
	
//	public void clearTf() {
//		tfDeptNo.setText("");
//		tfDeptName.setText("");
//		tfFloor.setText("");
//	}
	
	public void setDepartment(List<Department> list) { //수정버튼 외 세팅
		nextDept = list.get(list.size()-1);
		String no = String.format("D%03d", nextDept.getDeptNo()+1);
		tfDeptNo.setText(no);
		tfDeptNo.setEnabled(false);
		tfDeptName.setText("");
		tfFloor.setText("");
	}
	
	public void setDepartment(Department selDept) { //테이블에서 수정버튼 누르면
		String no = String.format("D%03d", selDept.getDeptNo());
		tfDeptNo.setText(no);
		tfDeptNo.setEnabled(false);
		tfDeptName.setText(selDept.getDeptName());
		tfFloor.setText(selDept.getFloor()+"");
	}
}
