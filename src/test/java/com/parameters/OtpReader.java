<<<<<<< HEAD

=======
>>>>>>> 825a72480ba566cd572f1affd3f4e98dc04ca366
package com.parameters;

import java.util.Scanner;

public class OtpReader {

    public static String getOtpFromConsole() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("👉 Please enter OTP manually: ");
			return scanner.nextLine();
		}
    }
}
