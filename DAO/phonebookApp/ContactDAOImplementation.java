package phonebookApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ContactDAOImplementation implements ContactDAOInterface {

	Connection conn = ConnectionManager.getInstance().getConnection();
	private Scanner input;

	@Override
	public void addContact(User user) throws SQLException {
		String query = "INSERT INTO contact(firstname, lastname, phoneNumber, userId) VALUES (?,?,?,?)";

		input = new Scanner(System.in);

		System.out.println("Enter firstname: ");
		String firstname = input.nextLine();

		System.out.println("Enter lastname: ");
		String lastname = input.nextLine();

		System.out.println("Enter phoneNumber: ");
		String phoneNumber = input.nextLine();

		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, firstname);
			statement.setString(2, lastname);
			statement.setString(3, phoneNumber);
			statement.setInt(4, user.getUserId());

			statement.execute();
		}
	}

	@Override
	public void updateContact(Contact contact) throws SQLException {
		String query = "UPDATE contact SET firstname = ?, lastname = ?, phoneNumber = ? WHERE contactId = ?";

		input = new Scanner(System.in);

		System.out.println("Enter new firstname: ");
		String newFirstname = input.nextLine();

		System.out.println("Enter new lastname: ");
		String newLastname = input.nextLine();

		System.out.println("Enter new phone number: ");
		String newPhoneNumber = input.nextLine();

		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, newFirstname);
			statement.setString(2, newLastname);
			statement.setString(3, newPhoneNumber);
			statement.setInt(4, contact.getContactId());

			statement.execute();

			System.out.println("Successfully updated.");
		}

	}

	@Override
	public void deleteContact(Contact contact) throws SQLException {

		String query = "DELETE FROM contact WHERE contactId = ?";

		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setInt(1, contact.getContactId());

			statement.execute();

			System.out.println("Successfully deleted.");
		}
	}

	@Override
	public void printContacts(User user) throws SQLException {
		String query = "SELECT * FROM contact WHERE userId = ?";

		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setInt(1, user.getUserId());

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				System.out.println(user.getUsername() + " list of contacts: ");
				do {
					System.out.println(rs.getInt("contactId") + " " + rs.getString("firstname") + " "
							+ rs.getString("lastname") + " " + rs.getString("phoneNumber"));
				} while (rs.next());
			} else {
				System.out.println("List is empty.");
			}

		}

	}

	@Override
	public void searchContactByFirstname(User user) throws SQLException {

		input = new Scanner(System.in);

		System.out.println("Enter firstname: ");
		String firstname = input.nextLine();

		String query = "SELECT * FROM contact WHERE firstname = ? AND userId = ?";

		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, firstname);
			statement.setInt(2, user.getUserId());

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				do {
					System.out.println(rs.getString("firstname") + " " + rs.getString("lastname") + " "
							+ rs.getString("phoneNumber"));

				} while (rs.next());
			} else {
				System.out.println("No contacts found.");
			}
		}

	}

	@Override
	public void searchContactByLastname(User user) throws SQLException {

		input = new Scanner(System.in);

		System.out.println("Enter lastname: ");
		String lastname = input.nextLine();

		String query = "SELECT * FROM contact WHERE lastname = ? AND userId = ?";

		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, lastname);
			statement.setInt(2, user.getUserId());

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				do {
					System.out.println(rs.getString("firstname") + " " + rs.getString("lastname") + " "
							+ rs.getString("phoneNumber"));

				} while (rs.next());
			} else {
				System.out.println("No contacts found.");
			}

		}
	}

	@Override
	public Contact getContact(User user) throws SQLException {
		Contact contact = new Contact();

		input = new Scanner(System.in);

		System.out.println("Enter contactId: ");
		int contactId = input.nextInt();

		String query = "SELECT *  FROM contact WHERE contactId = ? AND userId = ?";

		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setInt(1, contactId);
			statement.setInt(2, user.getUserId());

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				contact.setContactId(rs.getInt("contactId"));
				contact.setFirstname(rs.getString("firstname"));
				contact.setLastname(rs.getString("lastname"));
				contact.setPhoneNumber(rs.getString("phoneNumber"));
				contact.setUserId(rs.getInt("userId"));
			} else {
				System.out.println("No contacts found.");
			}
		}
		return contact;
	}
}
