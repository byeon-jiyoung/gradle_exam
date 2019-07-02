package gradle_exam.ui.content;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gradle_exam.dto.Department;

@SuppressWarnings("serial")
public class PanelDepartment extends JPanel {
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;

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
	
	public void setDepartment(Department department) {
		tfDeptNo.setText(department.getDeptNo()+"");
		tfDeptName.setText(department.getDeptName());
		tfFloor.setText(department.getFloor()+"");
	}
	
	public Department getDepartmnet() {
		int deptno = Integer.parseInt(tfDeptNo.getText().trim());
		String deptname = tfDeptName.getText().trim();
		int floor = Integer.parseInt(tfFloor.getText().trim());
		
		return new Department(deptno, deptname, floor);
	}
	
	public void clearTf() {
		tfDeptNo.setText("");
		tfDeptName.setText("");
		tfFloor.setText("");
	}
}
