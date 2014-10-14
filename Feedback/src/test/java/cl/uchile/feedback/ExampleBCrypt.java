package cl.uchile.feedback;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ExampleBCrypt {

	public static void main(String[] args) {
		int i = 0;
		while (i < 10) {
			String password = "123";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
	 
			System.out.println(hashedPassword);
			i++;
		}

	}

}
