package com.base.common.ema.ea.encry.des3;

import java.io.FileInputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.base.common.ema.ea.encry.Coder;

/**
 * DES3 ���ܽ���ʵ����
 * 
 * @author Aaron
 * 
 */
public class Encry_DES3CLt {

    private static Encry_DES3CLt instance;

    public static Encry_DES3CLt getInstance() {
        if (instance == null) {
            instance = new Encry_DES3CLt();
        }
        return instance;
    }

    private Encry_DES3CLt() {
    }

    public static void main(String[] args) throws Exception {

        byte[] key = new BASE64Decoder().decodeBuffer("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4");

        byte[] data = "�й�ABCabc123".getBytes("UTF-8");

        System.out.println("ECB���ܽ���");
        byte[] str3 = des3EncodeECB(key, data);
        byte[] str4 = des3DecodeECB(key, str3);
        System.out.println(new BASE64Encoder().encode(str3));
        System.out.println(new String(str4, "UTF-8"));

        System.out.println();

    }

    /**
     * ������������ʼ��һ����Կ
     * 
     * @param license
     * @return
     * @author: 8521
     * @date: 2012-9-29 ����01:48:42
     */
    public byte[] initKey(String license) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("DESede");
            _generator.init(112, new SecureRandom(license.getBytes()));
            return _generator.generateKey().getEncoded();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * �������������ݽ���
     * 
     * @param strMi
     * @param keyPath
     * @return
     * @author: 8521
     * @date: 2012-9-29 ����01:49:23
     */
    public String getDesString(String strMi, String keyPath) {

        String strMing = "";
        try {
            byte[] strMiby = Coder.decryptBASE64(strMi);
            FileInputStream keyFIS = new FileInputStream(keyPath);
            byte[] keyArray = new byte[keyFIS.available()];
            keyFIS.read(keyArray);
            byte[] byteMing = des3DecodeECB(keyArray, strMiby);
            strMing = new String(byteMing, "UTF8");
            keyFIS.close();
            return strMing;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * �������������ݽ���
     * 
     * @param strMi
     * @param keyPath
     * @return
     * @author: 8521
     * @date: 2012-9-29 ����01:49:23
     */
    public String getDesString(String strMi, byte[] keyArray ) {

        String strMing = "";
        try {
            byte[] strMiby = Coder.decryptBASE64(strMi);
            byte[] byteMing = des3DecodeECB(keyArray, strMiby);
            strMing = new String(byteMing, "UTF8");
            return strMing;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��������
     * 
     * @param generator
     * @param keyStr
     * @param strMing
     * @return
     */
    public String getEncString(String strMing, String keyPath) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        try {
            FileInputStream keyFIS = new FileInputStream(keyPath);
            byte[] keyArray = new byte[keyFIS.available()];
            keyFIS.read(keyArray);
            byteMing = strMing.getBytes("UTF8");
            byteMi = des3EncodeECB(keyArray, byteMing);
            strMi = Coder.encryptBASE64(byteMi);
            keyFIS.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }
    
    /**
     * ��������
     * 
     * @param generator
     * @param keyStr
     * @param strMing
     * @return
     */
    public String getEncString(String strMing, byte[] keyArray) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        try {
            byteMing = strMing.getBytes("UTF8");
            byteMi = des3EncodeECB(keyArray, byteMing);
            strMi = Coder.encryptBASE64(byteMi);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }

    /**
     * ECB����
     * 
     * @param key
     *            ��Կ
     * @param data
     *            ����
     * @return Base64���������
     * @throws Exception
     */
    public static byte[] des3EncodeECB(byte[] key, byte[] data) throws Exception {

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);

        return bOut;
    }

    /**
     * ECB����
     * 
     * @param key
     *            ��Կ
     * @param data
     *            Base64���������
     * @return ����
     * @throws Exception
     */
    public static byte[] des3DecodeECB(byte[] key, byte[] data) throws Exception {

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, deskey);

        byte[] bOut = cipher.doFinal(data);

        return bOut;

    }

}