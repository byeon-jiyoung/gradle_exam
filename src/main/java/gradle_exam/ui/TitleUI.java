package gradle_exam.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradle_exam.dao.TitleDao;
import gradle_exam.daoImpl.TitleDaoImpl;
import gradle_exam.dto.Title;
import gradle_exam.ui.content.PanelTitle;
import gradle_exam.ui.content.PanelTitleList;

@SuppressWarnings("serial")
public class TitleUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	
	private TitleDao dao;
	private List<Title> titleList;

	private PanelTitleList pTitleList;

	private PanelTitle pTitle;
	private JButton btnCancel;
	private JButton btnAdd;
	
	public TitleUI() throws SQLException {
		dao = new TitleDaoImpl();
		titleList = dao.selectTitleByAll(); //이렇게 선언을 해줘야 리스트가 출력이 된다. 안그러면 null뜬다
		initComponents();
	}
	
	private void initComponents() {
		setTitle("직책 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pTitle = new PanelTitle();
		pTitle.setTitle(titleList);
		contentPane.add(pTitle);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		pBtn.setLayout(new BoxLayout(pBtn, BoxLayout.X_AXIS));
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
		pTitleList = new PanelTitleList();
		pTitleList.setTitleList(titleList);
		pTitleList.setParent(this);
		pTitleList.reloadData();
		contentPane.add(pTitleList);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnAdd)  {
				if (btnAdd.getText().equals("추가")) {
					actionPerformedBtnAdd(e);
				}else {
					actionPerformedBtnUpdate(e);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (e.getSource() == btnCancel) {
			try {
				actionPerformedBtnCancel(e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	
	}
	
	private void actionPerformedBtnUpdate(ActionEvent e) throws SQLException {
		Title title = pTitle.getUpdateTitle();
		dao.updateTitle(title);
		refresh();
		btnAdd.setText("추가");
	}

	public void refresh() throws SQLException {
		pTitle.setTitle(dao.selectTitleByAll());
		pTitleList.setTitleList(dao.selectTitleByAll());
		pTitleList.reloadData();
	}

	protected void actionPerformedBtnCancel(ActionEvent e) throws SQLException {
		pTitle.setTitle(dao.selectTitleByAll()); //텍스트세팅
		btnAdd.setText("추가");
	}
	
	protected void actionPerformedBtnAdd(ActionEvent e) throws SQLException {
		Title title = pTitle.getTitle(); //panel에 입력한 정보를 객체로 받아옴
		
		dao.insertTitle(title);
		JOptionPane.showMessageDialog(null, String.format("%s 직책이 추가되었습니다.", title.getTitleName()));
		refresh();
	}

	public void updateTitleUI(Title searchTitle) {
		pTitle.setTitle(searchTitle);
		btnAdd.setText("수정");
		pTitle.setSearchTitleNo(searchTitle);
	}

	public void deleteTitleUI(Title searchTitle) {
		try {
			dao.deleteTitle(searchTitle);
			JOptionPane.showMessageDialog(null, "삭제완료");
			btnAdd.setText("추가");
			refresh();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setClear() {
		pTitle.setTitle(titleList);
		btnAdd.setText("추가");
	}
}
