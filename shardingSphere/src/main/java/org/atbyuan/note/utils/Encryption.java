package org.atbyuan.note.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("restriction")
public class Encryption {

    private static final String key = "987654321qazwsxe";

    /**
     * 加密  与PHP端保持一致
     */
    public static String encryptTerminal(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = key.getBytes();
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // 返回结果删除 ==
            return new BASE64Encoder().encode(encrypted).replace("==", "");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解密  与PHP端保持一致
     *
     * @param data
     * @return
     */
    public static String desEncryptTerminal(String data) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(data);
            if (data.length() < 24) {
                for (int i = 0; i <= 24 - data.length(); i++) {
                    builder.append("=");
                }
            }
            data = builder.toString();

            byte[] encrypted1 = Base64.decodeBase64(data.trim());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(key.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);

            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }
}