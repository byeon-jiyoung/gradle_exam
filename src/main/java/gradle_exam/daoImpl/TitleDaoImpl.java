package gradle_exam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gradle_exam.dao.TitleDao;
import gradle_exam.dto.Title;
import gradle_exam.jdbc.ConnectionProvider;

public class TitleDaoImpl implements TitleDao {
	
	@Override
	public List<Title> selectTitleByAll() throws SQLException {
		List<Title> lists = new ArrayList<Title>();
		
		String sql = "select tno, tname from title";
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();) {
			
			while(rs.next()) {
				lists.add(getTitle(rs));
			}
		} 
		return lists;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		return new Title(rs.getInt("tno"), rs.getString("tname"));
	}

	@Override
	public int insertTitle(Title title) throws SQLException {
		String sql = "insert into title(tno, tname) values (?,?)";
		
		int res = -1;
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, title.getTitleNo());
			pstmt.setString(2, title.getTitleName());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updateTitle(Title title) throws SQLException {
		String sql = "update title set tname=? where tno=?";
		int res = -1;
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, title.getTitleName());
			pstmt.setInt(2, title.getTitleNo());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int deleteTitle(Title title) throws SQLException {
		String sql = "delete from title where tno=?";
		int res = -1;
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, title.getTitleNo());
			res = pstmt.executeUpdate();
		}
		return res;
	}

}
