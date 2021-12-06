
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HashAndSQLTest {

	/// sql password check
	public static String passwordcheck(String password) {
		String regEx = "^[A-Za-z0-9@_]+$";
		String okpassword = " ";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(password);
		if (!m.matches()) {
		} else {
			okpassword = password;
		}
		return okpassword;
	}

	/// Hash the password
	public static byte[] salt() {
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	public static String hashGeneration(String password, byte[] salt) {

		String hashpassword = null;
		try {
			MessageDigest MD = MessageDigest.getInstance("SHA-512");
			MD.update(salt);
			byte[] bytes = MD.digest(password.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			hashpassword = sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return hashpassword;
	}

}
