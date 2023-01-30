package Encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;



public class EncryptDecryptHelper {

    private static SecretKey secretKey;
    private static byte[] key;

    public static void setKey(String myKey){

        try {
            key = myKey.getBytes("UTF-8");

            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key,16);

            secretKey = new SecretKeySpec(key,"AES");

        }catch (NoSuchAlgorithmException e){


            JOptionPane.showMessageDialog(null,"Error"+e.getMessage());

        }catch (UnsupportedEncodingException e){


            JOptionPane.showMessageDialog(null,"Error"+e.getMessage());

        }

    }

    public static String encrypted(String strToEnc,String sec){

        try {
            setKey(sec);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEnc.getBytes("UTF-8")));

        }catch (Exception e){

            JOptionPane.showMessageDialog(null,"Encrypt Error"+e.getMessage());
        }
        return null;
    }

    public static String decrypted(String strToDec,String sec){

        try {
            setKey(sec);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDec)));
        }catch (Exception e){


            JOptionPane.showMessageDialog(null,"Decrypt Error"+e.getMessage());

        }
        return null;
    }
}
