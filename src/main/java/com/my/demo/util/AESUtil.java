package com.my.demo.util;

import org.springframework.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by Administrator on 2016/1/18.
 */
@SuppressWarnings("unused")
public class AESUtil {

    private final static String HEX = "0123456789ABCDEF";
    private final static int JELLY_BEAN_4_2 = 17;
    private static byte[] iv = "0000000000000000".getBytes();

    /**
     * 生成16位的aeskey
     * @param key
     * @return
     */
    public static String generateAesKey(String key) {
        String aesKey = "";
        try {
            String uuid= UUID.randomUUID().toString();
            if(!StringUtils.isEmpty(uuid)){
                aesKey = uuid.replace("-", "");
                if(aesKey.length()>=16){
                    aesKey = aesKey.substring(0,16) ;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return aesKey;
    }

    /**
     * AES加密字符串
     *
     * @param content
     *            需要被加密的字符串
     * @param password
     *            加密需要的密码
     * @return 密文
     */
    public static String encryptAesNew(String content, String password) {
        try {

            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, key,ivspec);// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return StringUtil.toHex(result);

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解密AES加密过的字符串
     *
     * @param content
     *            AES加密过过的内容
     * @param password
     *            加密时的密码
     * @return 明文
     */
    public static String decryptAesNew(String content, String password) {
        try {
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeySpec secretKey = new SecretKeySpec(password.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKey,ivspec);// 初始化为解密模式的密码器
            byte[] contentByte = StringUtil.hex2Bytes(content);
            byte[] result = cipher.doFinal(contentByte);
            return new String(result); // 明文
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }




}