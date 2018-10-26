package phonebookApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDAOImplementation implements UserDAOInterface{
	Connection conn = ConnectionManager.getInstance().getConnection();
	
	
	@Override
	public void addUser() throws SQLException {
		String query = "INSERT INTO user (username,password,email) VALUES (?,?, ?)";
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter username: ");
		String username = input.nextLine();
		
		System.out.println("Enter password: ");
		String password = input.nextLine();
		
		System.out.println("Enter email: ");
		String email = input.nextLine();
		
		input.close();
		
		try(PreparedStatement statement = conn.prepareStatement(query)){
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, email);
			
			statement.execute();
		}
		
	}

}
