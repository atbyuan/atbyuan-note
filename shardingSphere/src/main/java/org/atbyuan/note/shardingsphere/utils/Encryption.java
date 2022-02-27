package org.atbyuan.note.shardingsphere.utils;

import cn.hutool.core.codec.Base64;
import lombok.experimental.UtilityClass;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@UtilityClass
public class Encryption {

    private static final String KEY = "987654321qazwsxe";

    /**
     * 加密  与PHP端保持一致
     */
    public static String encryptTerminal(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = KEY.getBytes();
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(KEY.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // 返回结果删除 ==
            return Base64.encode(encrypted).replace("==", "");
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

            byte[] encrypted1 = Base64.decode(data.trim());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keyspec = new SecretKeySpec(KEY.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(KEY.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);

            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }
}