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

import gradle_exam.dao.TitleDao;
import gradle_exam.daoImpl.TitleDaoImpl;
import gradle_exam.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	static final Logger log = LogManager.getLogger();
	static TitleDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new TitleDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
	}

	@Test
	public void test01SelectTitleByAll() throws SQLException {
		List<Title> lists = dao.selectTitleByAll();
		for(Title t : lists) {
			log.trace(t);
		}
		Assert.assertNotEquals(0, lists.size());
	}
	
	@Test
	public void test02InsertTitle() throws SQLException {
		Title title = new Title(6, "인턴");
		int res = dao.insertTitle(title);
		Assert.assertNotEquals(-1, res);
	}
	
	@Test
	public void test03UpdateTitle() throws SQLException {
		Title title = new Title(6, "인턴2");
		int res = dao.updateTitle(title);
		Assert.assertNotEquals(-1, res);
	}
	
	@Test
	public void test04DeleteTitle() throws SQLException {
		Title title = new Title(6);
		int res = dao.deleteTitle(title);
		Assert.assertNotEquals(-1, res);
	}
}
