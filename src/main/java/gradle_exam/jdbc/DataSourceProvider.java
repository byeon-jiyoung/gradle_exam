package gradle_exam.jdbc;

import javax.sql.DataSource;

public class DataSourceProvider {
	public static DataSource getDataSource() {
		return MyDataSource.getInstance().getDataSource();
	}
}
