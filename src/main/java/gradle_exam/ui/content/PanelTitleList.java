package gradle_exam.ui.content;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import gradle_exam.dto.Title;
import gradle_exam.ui.TitleUI;

@SuppressWarnings("serial")
public class PanelTitleList extends JPanel implements ActionListener {
	private JTable table;
	
	private List<Title> titleList;
	
	private JPopupMenu popupMenu;
	private JMenuItem mntmPopUpdate;
	private JMenuItem mntmPopDelete;
	
	private TitleUI parent;
	
	private PanelTitle pTitle;
	
	public void setTitleList(List<Title> titleList) {
		this.titleList = titleList;
	}

	public void setParent(TitleUI parent) {
		this.parent = parent;
	}
	
	public PanelTitleList() {
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
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
		
		tableCellAlignment(SwingConstants.CENTER, 0, 1);
		tableSetWidth(100, 200);
	}

	private Object[][] getRows() {
		Object[][] rows = new Object[titleList.size()][];
		for (int i = 0; i < titleList.size(); i++) {
			rows[i] = titleList.get(i).toArray();
		}
		return rows; 
	}

	private String[] getColumnNames() {
		return new String[] {"번호", "직책"};
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
			updateTitleUI();
		}
		if(e.getSource() == mntmPopDelete) {
			deleteTitleUI();
		}
		
	}

	private void deleteTitleUI() {
		int i = table.getSelectedRow();
		System.out.println(i);

		if (table.getModel().getRowCount() == 0) { // 부서정보가 존재하지 않을 경우
			return;
		}
		if (i < 0 || i > table.getModel().getRowCount() - 1) { // 선택하지 않은 경우
			JOptionPane.showMessageDialog(null, "선택된 직책이 없습니다.");
			return;
		}
		
		Title searchTitle = titleList.get(i);
		//System.out.println(parent); 확인용
		//계속 null이 뜬 이유는 내가 parent를 set안해줘서 그렇다. 여기서 set해주고 titleUI가서 호출해줘야됨
		parent.deleteTitleUI(searchTitle);
	}

	private void updateTitleUI() {
		int i = table.getSelectedRow();

		if (table.getModel().getRowCount() == 0) { // 부서정보가 존재하지 않을 경우
			return;
		}
		if (i < 0 || i > table.getModel().getRowCount() - 1) { // 선택하지 않은 경우
			JOptionPane.showMessageDialog(null, "선택된 직책이 없습니다.");
			return;
		}
		
		Title searchTitle = titleList.get(i);
		parent.updateTitleUI(searchTitle);
	}
	
}
