package gradle_exam.ui.content;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gradle_exam.dto.Department;

@SuppressWarnings("serial")
public class PanelDepartmentList extends JPanel {
	private JTable table;
	
	private List<Department> deptList;

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	public PanelDepartmentList() {
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	public void reloadData() {
		table.setModel(new DefaultTableModel(getRows(), getColumnNames()));
	}

	private Object[][] getRows() {
		Object[][] rows = new Object[deptList.size()][];
		for (int i = 0; i < deptList.size(); i++) {
			rows[i] = deptList.get(i).toArray();
		}
		return rows;
	}

	private String[] getColumnNames() {
		return new String[] { "번호", "부서명", "위치" };
	}

}
