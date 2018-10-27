package phonebookApp;

import java.sql.SQLException;

public interface ContactDAOInterface {

	public void addContact(User user) throws SQLException;

	public void updateContact(Contact contact) throws SQLException;

	public void deleteContact(Contact contact) throws SQLException;

	public void printContacts(User user) throws SQLException;

	public void searchContactByFirstname(User user) throws SQLException;

	public void searchContactByLastname(User user) throws SQLException;

	public Contact getContact(User user) throws SQLException;
}
