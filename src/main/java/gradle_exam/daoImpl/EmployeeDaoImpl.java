package gradle_exam.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
		String sql = "select e.empno, e.empname, tno, tname, e.manager, m.empname as m_name, e.salary, e.gender, depno, deptname, floor, e.hire_date " + 
				"from employee e join title t on e.title = t.tno join department d on e.dno = d.depno join employee m on e.manager = m.empno " + 
				"union " + 
				"select e.empno, e.empname, tno, tname, e.manager, null, e.salary, e.gender, depno, deptname, floor, e.hire_date " + 
				"from employee e join title t on e.title = t.tno join department d on e.dno = d.depno where manager is null;";
		
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
		return new Employee(rs.getInt("empno"), rs.getString("empname"), new Title(rs.getInt("tno"),rs.getString("tname")), new Employee(rs.getInt("manager")), rs.getInt("salary"), rs.getInt("gender"), new Department(rs.getInt("depno"),rs.getString("deptname"),rs.getInt("floor")), rs.getDate("hire_date"));
	} 

	@Override
	public int insertEmployee(Employee employee) throws SQLException {
		String sql = null;
		if (employee.getManager() != null) {
			sql = "insert into employee(empno, empname, title, salary, gender, dno, hire_date, manager) values(?, ?, ?, ?, ?, ?, ?, ?)";
		}else {
			sql = "insert into employee(empno, empname, title, salary, gender, dno, hire_date, manager) values(?, ?, ?, ?, ?, ?, ?, null)";
		}
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, employee.getEmpNo());
			pstmt.setString(2, employee.getEmpName());
			pstmt.setInt(3, employee.getTitle().getTitleNo());
			pstmt.setInt(4, employee.getSalary());
			pstmt.setInt(5, employee.getGender());
			pstmt.setInt(6, employee.getDno().getDeptNo());
			pstmt.setTimestamp(7, new Timestamp(employee.getHire_date().getTime())); //★★★★★★★★★★★★★★★TIP★★★★★★★★★★★★★★★★★★★★★
			if(employee.getManager() != null) {
				pstmt.setInt(8, employee.getManager().getEmpNo());
			}
			
			log.trace(pstmt);
			return pstmt.executeUpdate();
		}
	}

	@Override
	public int updateEmployee(Employee employee) throws SQLException {
		String sql = "update employee set empname=?, title=?, manager=null, salary=?, gender=?, dno=?, hire_date=? where empno=?";
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, employee.getEmpName());
			pstmt.setInt(2, employee.getTitle().getTitleNo());
			//pstmt.setInt(3, employee.getManager().getEmpNo());
			pstmt.setInt(3, employee.getSalary());
			pstmt.setInt(4, employee.getGender());
			pstmt.setInt(5, employee.getDno().getDeptNo());
			pstmt.setTimestamp(6, new Timestamp(employee.getHire_date().getTime()));
			pstmt.setInt(7, employee.getEmpNo());
			
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
