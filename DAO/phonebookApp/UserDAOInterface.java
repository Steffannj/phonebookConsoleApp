package phonebookApp;

import java.sql.SQLException;

public interface UserDAOInterface {
	
	public void addUser() throws SQLException;
	
	public User login() throws SQLException;
	
	public void options(User user) throws SQLException;
}
