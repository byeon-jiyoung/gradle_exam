package gradle_exam.dao;

import java.sql.SQLException;
import java.util.List;

import gradle_exam.dto.Title;

public interface TitleDao {
	List<Title> selectTitleByAll() throws SQLException;
	int insertTitle(Title title) throws SQLException;
	int updateTitle(Title title) throws SQLException;
	int deleteTitle(Title title) throws SQLException;
}
