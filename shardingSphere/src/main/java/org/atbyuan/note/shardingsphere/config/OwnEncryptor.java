package org.atbyuan.note.shardingsphere.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.encrypt.strategy.spi.Encryptor;
import org.atbyuan.note.shardingsphere.utils.Encryption;

import java.util.Properties;

/**
 * @author atbyuan
 * @since 2021/8/6 22:58
 **/
@Getter
@Setter
@Slf4j
public class OwnEncryptor implements Encryptor {

    private Properties properties;

    @Override
    public void init() {
        properties = new Properties();
    }

    @Override
    public String encrypt(final Object plaintext) {
        if (null == plaintext) {
            return null;
        }
        return Encryption.encryptTerminal(plaintext.toString());
    }

    @Override
    public Object decrypt(final String ciphertext) {
        return Encryption.desEncryptTerminal(ciphertext);
    }

    @Override
    public String getType() {
        return "own";
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}

