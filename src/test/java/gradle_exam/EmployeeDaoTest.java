package gradle_exam;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import gradle_exam.dao.EmployeeDao;
import gradle_exam.daoImpl.EmployeeDaoImpl;
import gradle_exam.dto.Department;
import gradle_exam.dto.Employee;
import gradle_exam.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	static final Logger log = LogManager.getLogger();
	static EmployeeDao dao; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new EmployeeDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
	}

	@Test
	public void test01SelectEmployeeByAll() throws SQLException {
		log.trace("test01SelectEmployeeByAll()");
		List<Employee> list = dao.selectEmployeeByAll();
		Assert.assertNotNull(list);
	}
	
	@Test
	public void test02InsertEmployee() throws SQLException {
		log.trace("test02InsertEmployee()");
		Employee newEmp = new Employee(1004, "김우빈", new Title(3), new Employee(4377), 3500000, new Department(2));
		
		int res = dao.insertEmployee(newEmp);
		Assert.assertNotEquals(-1, res);
	}
	
	@Test
	public void test03UpdateEmployee() throws SQLException {
		log.trace("test03UpdateEmployee()");
		Employee upEmp = new Employee(1004, "김우비이이이인", new Title(3), new Employee(4377), 3000000, new Department(2));
		
		int res = dao.updateEmployee(upEmp);
		Assert.assertNotEquals(-1, res);
	}
	
	@Test
	public void test04DeleteEmployee() throws SQLException {
		log.trace("test04DeleteEmployee()");
		Employee delEmp = new Employee(1004);
		
		int res = dao.deleteEmployee(delEmp);
		Assert.assertNotEquals(-1, res);
	}

}
