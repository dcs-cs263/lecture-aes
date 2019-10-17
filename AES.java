
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES
{
    private byte[] key;
    private byte[] iv;

    public AES(byte[] key, byte[] iv)
    {
        this.key = key;
        this.iv = iv;
    }

    public byte[] encrypt(String plainText) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, this.iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);

        return cipher.doFinal(plainText.getBytes());
    }

    public String decrypt(byte[] cipherBytes) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, this.iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);

        return new String(cipher.doFinal(cipherBytes));
    }
}
