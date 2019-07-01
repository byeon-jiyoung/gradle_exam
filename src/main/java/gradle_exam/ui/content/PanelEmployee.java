package gradle_exam.ui.content;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gradle_exam.dto.Department;
import gradle_exam.dto.Title;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class PanelEmployee extends JPanel {
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JComboBox<Title> cmbTitle;
	private JComboBox<Department> cmbDept;
	private JTextField tfSalary;
	private JTextField tfDate;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public PanelEmployee() {

		initComponents();
	}
	private void initComponents() {
		setLayout(new GridLayout(0, 2, 10, 5));
		
		JLabel lblEmpNo = new JLabel("번호");
		lblEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEmpNo);
		
		tfEmpNo = new JTextField();
		add(tfEmpNo);
		tfEmpNo.setColumns(10);
		
		JLabel lblEmpName = new JLabel("사원명");
		lblEmpName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEmpName);
		
		tfEmpName = new JTextField();
		tfEmpName.setColumns(10);
		add(tfEmpName);
		
		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitle);
		
		cmbTitle = new JComboBox<Title>();
		add(cmbTitle);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSalary);
		
		tfSalary = new JTextField();
		tfSalary.setColumns(10);
		add(tfSalary);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblGender);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JRadioButton man = new JRadioButton("남");
		buttonGroup.add(man);
		panel.add(man);
		
		JRadioButton woman = new JRadioButton("여");
		buttonGroup.add(woman);
		panel.add(woman);
		
		JLabel lblDept = new JLabel("부서");
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDept);
		
		cmbDept = new JComboBox<Department>();
		add(cmbDept);
		
		JLabel lblDate = new JLabel("입사일");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDate);
		
		tfDate = new JTextField();
		tfDate.setColumns(10);
		add(tfDate);
	}

}
