import java.util.Arrays;

/**
 * Created by qqy on 15/8/13.
 */
public class SignUtil {

    /**
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     * @Description: 微信权限验证
     */
    private static String TOKEN = "forvioletqqy";

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{TOKEN, timestamp, nonce};
        //按字典排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        //加密并返回验证结果
        return signature == null ? false : signature.equals(Encrypt.encrypt(content.toString(), "SHA-1"));
    }

}
