
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
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

    public byte[] encryptCBC(String plainText) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec spec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);

        return cipher.doFinal(plainText.getBytes());
    }

    public String decryptCBC(byte[] cipherBytes) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec spec = new IvParameterSpec(this.iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);

        return new String(cipher.doFinal(cipherBytes));
    }

    public byte[] encryptECB(String plainText) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(plainText.getBytes());
    }

    public String decryptECB(byte[] cipherBytes) throws Exception
    {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return new String(cipher.doFinal(cipherBytes));
    }
}
