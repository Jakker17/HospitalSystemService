package eng.hospitalSystemService.app;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SecurityUtils {
    public static byte[] generateSalt() {
         java.security.SecureRandom random = null;
         try
         {
             random = java.security.SecureRandom.getInstanceStrong();
         }
         catch (NoSuchAlgorithmException e)
         {
             throw new RuntimeException("Failed to get strong algortihm");
         }
         byte[] ret = random.generateSeed(128);
         return ret;
    }


    public static byte[] hashPassword(String password, byte[] salt) {
        char[] passwordBytes = password.toCharArray();
        try
        {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(passwordBytes,salt,100,128);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            return res;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            throw new RuntimeException(e);
        }
    }
}
