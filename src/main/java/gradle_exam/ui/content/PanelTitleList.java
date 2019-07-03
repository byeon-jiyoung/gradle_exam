package gradle_exam.ui.content;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import gradle_exam.dto.Title;

@SuppressWarnings("serial")
public class PanelTitleList extends JPanel {
	private JTable table;
	
	private List<Title> titleList;
	
	public void setTitleList(List<Title> titleList) {
		this.titleList = titleList;
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
}
