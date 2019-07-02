package gradle_exam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gradle_exam.dao.DepartmentDao;
import gradle_exam.dto.Department;
import gradle_exam.jdbc.ConnectionProvider;

public class DepartmentDapImpl implements DepartmentDao {
	
	@Override
	public List<Department> selectDepartmentByAll() throws SQLException {
		List<Department> lists = new ArrayList<Department>();
		
		String sql = "select depno, deptname, floor from department";
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();) {
			
			while(rs.next()) {
				lists.add(getDepartment(rs));
			}
		}
		return lists;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("depno"), rs.getString("deptname"), rs.getInt("floor"));
	}

	@Override
	public int insertDepartment(Department department) throws SQLException {
		String sql = "insert into department(depno, deptname, floor) values(?, ?, ?)";
		
		int res = -1;
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updateDepartment(Department department) throws SQLException {
		String sql = "update department set deptname=?, floor=? where depno=?;";
		int res = -1;
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, department.getDeptName());
			pstmt.setInt(2, department.getFloor());
			pstmt.setInt(3, department.getDeptNo());
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int deleteDepartment(Department department) throws SQLException {
		String sql = "delete from department where depno=?";
		int res = -1;
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, department.getDeptNo());
			res = pstmt.executeUpdate();
		}
		return res;
	}

}
