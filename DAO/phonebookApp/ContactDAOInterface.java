package phonebookApp;

import java.sql.SQLException;

public interface ContactDAOInterface {
	
	public void addContact() throws SQLException;
	
	public void updateContact(String contactName) throws SQLException;
	
	public void deleteContact(String contactName) throws SQLException;
	
	public void printContacts(User user) throws SQLException;
	
	public void searchContactByFirstname(User user, String name) throws SQLException;
	
	public void searchContactByLastname(User user, String name) throws SQLException;
	
	
}
