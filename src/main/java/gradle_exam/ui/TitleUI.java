package gradle_exam.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import gradle_exam.dao.TitleDao;
import gradle_exam.daoImpl.TitleDaoImpl;
import gradle_exam.dto.Title;
import gradle_exam.ui.content.PanelTitle;
import javax.swing.JButton;
import gradle_exam.ui.content.PanelTitleList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TitleUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	
	private TitleDao dao;
	private List<Title> titleList;

	private PanelTitleList pTitleList;

	private PanelTitle pTitle;
	private JButton btnCancel;
	private JButton btnAdd;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TitleUI frame = new TitleUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TitleUI() throws SQLException {
		dao = new TitleDaoImpl();
		titleList = dao.selectTitleByAll();
		
		initComponents();
	}
	
	private void initComponents() {
		setTitle("직책 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pTitle = new PanelTitle();
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
		pTitleList.reloadData();
		contentPane.add(pTitleList);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			try {
				actionPerformedBtnAdd(e);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
	}
	
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pTitle.clearTf();
	}
	
	protected void actionPerformedBtnAdd(ActionEvent e) throws SQLException {
		Title title = pTitle.getTitle(); //panel에 입력한 정보를 객체로 받아옴
		
		dao.insertTitle(title);
		JOptionPane.showMessageDialog(null, String.format("%s 직책이 추가되었습니다.", title.getTitleName()));
		pTitle.clearTf();
		pTitleList.setTitleList(dao.selectTitleByAll());
		pTitleList.reloadData();
	}
}
