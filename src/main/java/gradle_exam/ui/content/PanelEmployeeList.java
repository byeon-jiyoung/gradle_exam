package gradle_exam.ui.content;

import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import gradle_exam.dto.Employee;
import gradle_exam.ui.EmployeeUI;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PanelEmployeeList extends JPanel implements ActionListener{
	private JTable table;
	
	private List<Employee> empList;
	
	private JPopupMenu popupMenu;
	private JMenuItem mntmPopUpdate;
	private JMenuItem mntmPopDelete;
	
	private EmployeeUI parent;
	
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public void setParent(EmployeeUI parent) {
		this.parent = parent;
	}

	public PanelEmployeeList() {
		//System.out.println(empList);
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		popupMenu = new JPopupMenu();
		
		mntmPopUpdate = new JMenuItem("수정");
		mntmPopUpdate.addActionListener(this);
		popupMenu.add(mntmPopUpdate);
		
		mntmPopDelete = new JMenuItem("삭제");
		mntmPopDelete.addActionListener(this);
		popupMenu.add(mntmPopDelete);
		
		table.setComponentPopupMenu(popupMenu);
	}
	
	public void reloadData() {
		table.setModel(new DefaultTableModel(getRows(), getColumnNames()));

		tableCellAlignment(SwingConstants.CENTER, 0,1,2,4,5,6);
		tableCellAlignment(SwingConstants.RIGHT, 3);
		tableSetWidth(50,50,40,70,40,50,70);
	}
	
	private Object[][] getRows() {
		Object[][] rows = new Object[empList.size()][];
		for (int i = 0; i < empList.size(); i++) {
			rows[i] = empList.get(i).toArray();
		}
		return rows;
	}
	
	private String[] getColumnNames() {
		return new String[] {"번호", "사원명", "직책", "급여", "성별", "부서", "입사일"};
	}
	
	// 테이블 셀 내용의 정렬
	protected void tableCellAlignment(int align, int... idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
	
		TableColumnModel model = table.getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			model.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
	
	// 테이블 셀의 폭 설정
	protected void tableSetWidth(int... width) {
		TableColumnModel cModel = table.getColumnModel();
	
		for (int i = 0; i < width.length; i++) {
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mntmPopUpdate) {
			updateEmpUI();
		}
		if(e.getSource() == mntmPopDelete) {
			deleteEmpUI();
		}
		
	}

	private void deleteEmpUI() {
		int i = table.getSelectedRow();

		if (table.getModel().getRowCount() == 0) { // 부서정보가 존재하지 않을 경우
			return;
		}
		if (i < 0 || i > table.getModel().getRowCount() - 1) { // 선택하지 않은 경우
			JOptionPane.showMessageDialog(null, "선택된 직책이 없습니다.");
			return;
		}
		
		Employee searchEmp = empList.get(i);
		parent.deleteEmpUI(searchEmp);
	}

	private void updateEmpUI() {
		int i = table.getSelectedRow();

		if (table.getModel().getRowCount() == 0) { // 부서정보가 존재하지 않을 경우
			return;
		}
		if (i < 0 || i > table.getModel().getRowCount() - 1) { // 선택하지 않은 경우
			JOptionPane.showMessageDialog(null, "선택된 직책이 없습니다.");
			return;
		}
		
		Employee searchEmp = empList.get(i);
		parent.updateEmpUI(searchEmp);
	}
}
