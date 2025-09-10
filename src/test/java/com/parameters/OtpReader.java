
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
