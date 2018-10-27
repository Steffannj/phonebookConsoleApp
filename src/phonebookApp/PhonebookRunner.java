package phonebookApp;

import java.util.Scanner;

public class PhonebookRunner {

	public static void main(String[] args) {
		
		UserDAOImplementation userDAO = new UserDAOImplementation();
		Scanner input = new Scanner(System.in);

		int choice = -1;

		while (choice != 3) {

			try {
				System.out.println("1. Sign up\n2. Log in\n3. Exit");
				choice = input.nextInt();

				switch (choice) {
				case 1:
					userDAO.addUser();
					break;
				case 2:
					User user = userDAO.login();

					if (user.getUserId() != 0) {
						userDAO.options(user);
					} else {
						System.out.println("Wrong username or password.");
					}

					break;
				case 3:
					ConnectionManager.getInstance().close();
					input.close();
					break;
				default:
					System.out.println("Wrong input! Choose integer(1-3): ");
				}
			} catch (Exception e) {
				System.out.println("Wrong input!");
				input.next();
			}
		}

	}

}
