package gradle_exam;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import gradle_exam.jdbc.ConnectionProvider;

public class DBConnectionTest {
	static final Logger log = LogManager.getLogger();
	
	@Test
	public void testConnection() {
		try {
			Connection con = ConnectionProvider.getConnection();
			log.trace("con = " + con);
			Assert.assertNotNull(con); //null이 아니면 성공임
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 정보를 확인하세요.");
		} 
	}

}
