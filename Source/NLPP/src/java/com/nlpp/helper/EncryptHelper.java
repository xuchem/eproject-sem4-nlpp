/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author SVN - Team
 */
public class EncryptHelper {

    public String encriptMd5Password(String passwrord) {
        String md5val = "";
        MessageDigest algorithm = null;
        try {
            algorithm = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsae) {
            System.out.println("Cannot find digest algorithm");
            System.exit(1);
        }
        byte[] defaultBytes = passwrord.getBytes();
        algorithm.reset();
        algorithm.update(defaultBytes);
        byte messageDigest[] = algorithm.digest();
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < messageDigest.length; i++) {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        md5val = hexString.toString();
        System.out.println("MD5 (" + passwrord + ") = " + md5val);
        return md5val;
    }
}
