import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HashGenerator {
    public static String encode(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return Hex.encodeHexString(hash).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encodeWithKey(String key, String data){
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8"))).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encode2(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }



    public static void main(String[] args) {
        String apiSecret = "F83D4537A69B3A67D4A27C3B88E7296C6A4D4E2E764E9F9BB8824CC34E28A1B2";
        String data = "campo_a_cifrar";
        System.out.println("Hash SHA-256 con libreria: " + encode(data));

       // System.out.println("Hash SHA-256 con apiSecret: " + encodeWithKey(apiSecret,data));

        System.out.println("Hash SHA-256 sin libreria: " + encode2(data));
    }
}
