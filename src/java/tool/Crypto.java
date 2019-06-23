/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

/**
 *
 * @author Yuri
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

 public class Crypto {
    public static boolean checkSHA512(String password, String hash) {
        String sha = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha.equals(hash);
    }


    public static String sha512(String password) {
        String sha512 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha512 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha512;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
