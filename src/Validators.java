
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {
	static boolean isEmailValid(String emailAddress) {
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(emailAddress);

		return matcher.matches();
	}

	static boolean isPasswordValid(String pass) {
		int count = 0;

		if (8 <= pass.length() && pass.length() <= 32) {
			if (pass.matches(".*\\d.*"))
				count++;
			if (pass.matches(".*[a-z].*"))
				count++;
			if (pass.matches(".*[A-Z].*"))
				count++;
		}

		return count == 3;
	}

	public static void main(String args[]) {
		System.out.println(isEmailValid("user-dn_am23e@domain.com"));
		System.out.println(isPasswordValid("ana123Aweww"));
	}
}