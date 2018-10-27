package phonebookApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDAOImplementation implements UserDAOInterface {

	Connection conn = ConnectionManager.getInstance().getConnection();
	private java.util.Scanner input;

	@Override
	public void addUser() throws SQLException {
		String query = "INSERT INTO user (username,password,email) VALUES (?,?, ?)";

		input = new Scanner(System.in);

		System.out.println("Enter username: ");
		String username = input.nextLine();

		System.out.println("Enter password: ");
		String password = input.nextLine();

		System.out.println("Enter email: ");
		String email = input.nextLine();

		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, email);

			statement.execute();
			System.out.println("Successfully registered!");
		}

	}

	@Override
	public User login() throws SQLException {
		User user = new User();

		input = new java.util.Scanner(System.in);

		System.out.println("Enter username: ");
		String username = input.nextLine();

		System.out.println("Enter password: ");
		String password = input.nextLine();

		String query = "SELECT * FROM user WHERE username = ? AND password = ?";

		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				user.setUserId(rs.getInt("userId"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));

				System.out.println("Successfully loged in!");
			}
		}

		return user;
	}

	@Override
	public void options(User user) throws SQLException {

		ContactDAOImplementation contactDAO = new ContactDAOImplementation();

		System.out.println("Choose one option: ");
		input = new Scanner(System.in);

		int choice = 0;

		while (choice != 7) {
			try {
				System.out.println("1. Add new contact\n2. Edit contact\n3. Delete contact\n4. List of contats"
						+ "\n5. Search contact by firstname" + "\n6. Search contact by lastname" + "\n7. Log out");

				choice = input.nextInt();

				switch (choice) {
				case 1:
					contactDAO.addContact(user);
					break;
				case 2:
					Contact contact = contactDAO.getContact(user);
					if (contact.getContactId() != 0) {
						contactDAO.updateContact(contact);
					}
					break;
				case 3:
					contact = contactDAO.getContact(user);
					if (contact.getContactId() != 0) {
						contactDAO.deleteContact(contact);
					}
					break;
				case 4:
					contactDAO.printContacts(user);
					break;
				case 5:
					contactDAO.searchContactByFirstname(user);
					break;
				case 6:
					contactDAO.searchContactByLastname(user);
					break;
				case 7:
					break;
				default:
					System.out.println("Wrong input! Choose one option(1-7).");
				}
			} catch (Exception e) {
				System.out.println("Wrong input!");
				input.next();
			}
		}

	}

}
