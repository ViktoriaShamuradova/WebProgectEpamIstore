package by.epamtc.shamuradova.ishop.service.tool;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Класс, предназначенный для шифрования паролей
 * 
 * Password encrypting class
 * 
 * @author Шамурадова Виктория, 2020
 */
public class MD5Encryptor {

	private static final String ALGORITHN = "MD5";
	private static final String FORMAT = "%032x";
	private static final int POSITIVE = 1;

	private MD5Encryptor() {

	}

	public static String getHashCode(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5Digest = MessageDigest.getInstance(ALGORITHN);
		byte[] passwordByte = password.getBytes();

		byte[] digest = md5Digest.digest(passwordByte);
		BigInteger hash = new BigInteger(POSITIVE, digest);

		return String.format(FORMAT, hash);

	}

}
