import java.security.SecureRandom;
import java.util.Base64;

public class Program {

    public static String toBase64(byte[] data) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data);
    }

    public static byte[] fromBase64(String encodedString) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(encodedString);
    }

    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);

        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);

        try {
            AES aes = new AES(keyBytes, iv);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}