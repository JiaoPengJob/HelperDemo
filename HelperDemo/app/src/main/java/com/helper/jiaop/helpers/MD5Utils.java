package com.helper.jiaop.helpers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jiaop on 2016/6/2.
 * MD5
 */
public class MD5Utils {
    /**
     * 將輸入字符串做 md5 編碼
     * @param s
     * @return
     */
    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes("UTF-8"));
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (byte b : messageDigest) {
                if ((b & 0xFF) < 0x10)
                    hexString.append("0");
                hexString.append(Integer.toHexString(b & 0xFF));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
