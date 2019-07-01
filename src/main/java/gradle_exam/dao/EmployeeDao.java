package gradle_exam.dao;

import java.sql.SQLException;
import java.util.List;

import gradle_exam.dto.Employee;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll() throws SQLException;
	int insertEmployee(Employee employee) throws SQLException;
	int updateEmployee(Employee employee) throws SQLException;
	int deleteEmployee(Employee employee) throws SQLException;
}
