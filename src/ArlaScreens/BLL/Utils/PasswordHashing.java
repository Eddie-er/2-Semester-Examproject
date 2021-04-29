package ArlaScreens.BLL.Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHashing {
    /**
     * Hashes the given password
     * Link: https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
     * @param password
     * @param salt
     * @return
     */

    public static String hashPassword(String password, byte[] salt) {
        try {
            String base = password;
            MessageDigest messageDigest = null;
            messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt);
            byte[] hash = messageDigest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append(0); {
                    hexString.append(hex);
                }
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }

    /**
     * Returns true if a given text password matches a hashed password
     * @param plainTextPassword
     * @param hashedPassword
     * @param salt
     * @return
     */
    public static boolean verifyPassword(String plainTextPassword, String hashedPassword, byte[] salt) {
        return hashPassword(plainTextPassword, salt).equals(hashedPassword);
    }

    public static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
