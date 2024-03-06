package com.jsrss.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 1. Display either login/Register or logout
	2. Display the Role after the username.
	=> Display the following for Authenticated Users
			Home Welcome : sri [ROLE_CUSTOMER] Logout
	=> Display the following for Un-Authenticated Users
			Home Welcome : Login Register
	3. Customize Login Page and Error Messages.
	4. Customize Logout Page
	5. Use the JDBCAuthenticationManager.
	6. Implement Registration and Use PasswordEnoders
	7. Display the Links accordning to Role.
 */
@SpringBootApplication
public class DayalBookstoreGradleApplication {

	public static void main(String[] as) {
		System.out.println("Boot Application - Begin");
		SpringApplication.run(DayalBookstoreGradleApplication.class, as);
		System.out.println("Boot Application - End");
	}
}