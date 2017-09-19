/**
 * 上海轩言网络信息科技有限公司
 * Copyright (c) 2016, xuanyan All Rights Reserved.
 */
package com.common.utils.rsa;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.digester.Digester;

/**
 * 
 * <b>Description：</b> 加密解密工具 <br/>
 * <b>ClassName：</b> Encrypter <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月29日 上午10:08:46 <br/>
 * <b>@version: </b>  <br/>
 */
public final class Encrypter {

    private static Cipher       ecipher;

    private static Cipher       dcipher;

    // 必须24个字符
    private static final String key = "*:@7$8!t*:@7$8!t*:@7$8!t";

    private static final String alg = "DESede";

    static {

        try {

            SecretKey skey = new SecretKeySpec(key.getBytes(), alg);

            ecipher = Cipher.getInstance(alg);

            dcipher = Cipher.getInstance(alg);

            ecipher.init(Cipher.ENCRYPT_MODE, skey);

            dcipher.init(Cipher.DECRYPT_MODE, skey);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    /**
     *
     * <pre>
     * 加密
     * </pre>
     *
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        if (str == null)
            return "";
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            
            return  Base64.encode(enc);

        } catch (Exception e) {

            e.printStackTrace();

            return "";
        }

    }

    /**
     *
     * <pre>
     * 解密
     * </pre>
     *
     * @param str
     * @return
     */
    public static String decrypt(String str) {
        if (str == null)
            return "";
        try {
            // Decode base64 to get bytes
            byte[] dec =Base64.decode(str) ;
            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");

        } catch (Exception e) {

            e.printStackTrace();

            return "";
        }

    }
    
    public static void main(String[] args) {
        System.out.println(Encrypter.encrypt("izene123"));
    }

}
