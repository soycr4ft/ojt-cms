package com.ojt.cms.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
	public String encrypt(String password) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), getSalt(), 85319, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
    public boolean matches(String rawPassword, String encodedPassword) {
        String encryptedRaw = encrypt(rawPassword);
        return encryptedRaw.equals(encodedPassword);
    }

    private byte[] getSalt()
        throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 고정된 salt 값 (예: "cms-system")
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        return digest.digest("cms-system".getBytes("UTF-8"));
    }
}
