package gradle_exam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gradle_exam.dao.EmployeeDao;
import gradle_exam.dto.Department;
import gradle_exam.dto.Employee;
import gradle_exam.dto.Title;
import gradle_exam.jdbc.ConnectionProvider;

public class EmployeeDaoImpl implements EmployeeDao {
	static final Logger log = LogManager.getLogger();
	
	@Override
	public List<Employee> selectEmployeeByAll() throws SQLException {
		String sql = "select empno, empname, title, manager, salary, dno from employee";
		List<Employee> lists = null;
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();) {
			log.trace(pstmt);
			if(rs.next()) {
				lists = new ArrayList<Employee>();
				do {
					lists.add(getEmployee(rs));
				} while (rs.next());
			}
		}
		return lists;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		return new Employee(rs.getInt("empno"), rs.getString("empname"), new Title(rs.getInt("title")), new Employee(rs.getInt("manager")), rs.getInt("salary"), new Department(rs.getInt("dno")));
	}

	@Override
	public int insertEmployee(Employee employee) throws SQLException {
		String sql = "insert into employee values(?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, employee.getEmpNo());
			pstmt.setString(2, employee.getEmpName());
			pstmt.setInt(3, employee.getTitle().getTitleNo());
			pstmt.setInt(4, employee.getManager().getEmpNo());
			pstmt.setInt(5, employee.getSalary());
			pstmt.setInt(6, employee.getDno().getDeptNo());
			
			log.trace(pstmt);
			return pstmt.executeUpdate();
		}
	}

	@Override
	public int updateEmployee(Employee employee) throws SQLException {
		String sql = "update employee set empname=?, title=?, manager=?, salary=?, dno=? where empno=?";
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, employee.getEmpName());
			pstmt.setInt(2, employee.getTitle().getTitleNo());
			pstmt.setInt(3, employee.getManager().getEmpNo());
			pstmt.setInt(4, employee.getSalary());
			pstmt.setInt(5, employee.getDno().getDeptNo());
			pstmt.setInt(6, employee.getEmpNo());
			
			log.trace(pstmt);
			return pstmt.executeUpdate();
		}
	}

	@Override
	public int deleteEmployee(Employee employee) throws SQLException {
		String sql = "delete from employee where empno = ?";
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, employee.getEmpNo());
			
			log.trace(pstmt);
			return pstmt.executeUpdate();
		}
	}

}
