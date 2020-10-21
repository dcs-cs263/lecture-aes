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

        try {
            while(true) {
                System.out.print("Plaintext: ");
                String text = System.console().readLine();

                byte[] iv = new byte[16];
                secureRandom.nextBytes(iv);

                AES aes = new AES(keyBytes, iv);

                byte[] ciphertextECB = aes.encryptECB(text);
                System.out.printf(
                    "Ciphertext (ECB): %s\n", 
                    toBase64(ciphertextECB)
                );

                String decryptedPlaintextECB = aes.decryptECB(ciphertextECB);
                System.out.printf(
                    "Decrypted plaintext (ECB): %s\n", 
                    decryptedPlaintextECB
                );

                byte[] ciphertextCBC = aes.encryptCBC(text);
                System.out.printf(
                    "Ciphertext (CBC): %s\n", 
                    toBase64(ciphertextCBC)
                );

                String decryptedPlaintextCBC = aes.decryptCBC(ciphertextCBC);
                System.out.printf(
                    "Decrypted plaintext (CBC): %s\n", 
                    decryptedPlaintextCBC
                );

                System.out.println();
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}