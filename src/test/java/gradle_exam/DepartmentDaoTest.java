package gradle_exam;

import static org.junit.Assert.*;

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

import gradle_exam.dao.DepartmentDao;
import gradle_exam.daoImpl.DepartmentDapImpl;
import gradle_exam.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	static final Logger log = LogManager.getLogger();
	static DepartmentDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new DepartmentDapImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
	}

	@Test
	public void test01SelectDepartmentByAll() throws SQLException {
		List<Department> lists = dao.selectDepartmentByAll();
		for(Department d: lists) {
			log.trace(d);
		}
		Assert.assertNotEquals(0, lists.size());
	}
	
	@Test
	public void test02InsertDepartment() throws SQLException {
		Department newDept = new Department(5, "마케팅", 40);
		int res = dao.insertDepartment(newDept);
		Assert.assertNotEquals(-1, res);
	}
	
	@Test
	public void test03UpdateDepartment() throws SQLException {
		Department newDept = new Department(5, "마케팅2", 6);
		int res = dao.updateDepartment(newDept);
		Assert.assertNotEquals(-1, res);
	}
	
	@Test
	public void test04DeleteDepartment() throws SQLException {
		Department newDept = new Department(5);
		int res = dao.deleteDepartment(newDept);
		Assert.assertNotEquals(-1, res);
	}
}
