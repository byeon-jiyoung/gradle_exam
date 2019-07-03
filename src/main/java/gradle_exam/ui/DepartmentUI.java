package gradle_exam.ui;

import java.sql.SQLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradle_exam.dao.DepartmentDao;
import gradle_exam.daoImpl.DepartmentDapImpl;
import gradle_exam.dto.Department;
import gradle_exam.ui.content.PanelDepartment;
import gradle_exam.ui.content.PanelDepartmentList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DepartmentUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnCancel;
	private PanelDepartment pDept;
	private PanelDepartmentList pDeptList;
	private JButton btnAdd;
	
	private DepartmentDao dao;
	private List<Department> deptList;
	
	public void setDeptDao(DepartmentDao dao) {
		this.dao = dao;
	}
	
	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	public DepartmentUI() {
		dao = new DepartmentDapImpl();
		try {
			deptList = dao.selectDepartmentByAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initComponents();
	}
	
	private void initComponents() {
		setTitle("부서 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pDept = new PanelDepartment();
		pDept.setDepartment(deptList);
		contentPane.add(pDept);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		pBtn.setLayout(new BoxLayout(pBtn, BoxLayout.X_AXIS));
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
		pDeptList = new PanelDepartmentList();
		pDeptList.setDeptList(deptList);
		pDeptList.reloadData();
		pDeptList.setParent(this);
		contentPane.add(pDeptList);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			if (btnAdd.getText().equals("추가")) {
				actionPerformedBtnAdd(e);
			}else {
				actionPerformedBtnUpdate(e);
			}
		}
		if (e.getSource() == btnCancel) {
			try {
				actionPerformedBtnCancel(e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private void actionPerformedBtnUpdate(ActionEvent e) {
		Department dept = pDept.getUpdateDepartmnet();
		
		int res = -1;
		try {
			res = dao.updateDepartment(dept);
			refresh();
			btnAdd.setText("추가");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	protected void actionPerformedBtnCancel(ActionEvent e) throws SQLException {
		pDept.setDepartment(dao.selectDepartmentByAll()); //텍스트 세팅
	}
	
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department dept = pDept.getDepartmnet();
		
		int res;
		try {
			res = dao.insertDepartment(dept);
			if(res != -1) {
				JOptionPane.showMessageDialog(null, String.format("%s 부서가 추가되었습니다.", dept.getDeptName()));
				refresh();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void refresh() throws SQLException {
		pDept.setDepartment(dao.selectDepartmentByAll()); //텍스트세팅
		pDeptList.setDeptList(dao.selectDepartmentByAll()); //old list -> new list
		pDeptList.reloadData();
	}

	public void updateDepartmentUI(Department searchDept) {
		pDept.setDepartment(searchDept);
		btnAdd.setText("수정");
		pDept.setSearchDeptNo(searchDept);
	}

	public void deleteDepartmentUI(Department searchDept) {
		int res = -1;
		
		try {
			res = dao.deleteDepartment(searchDept);
			JOptionPane.showMessageDialog(null, "삭제완료");
			refresh();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
