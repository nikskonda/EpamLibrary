package by.epam.java.training.dao.util;

import org.apache.commons.codec.digest.DigestUtils;

public class EncriptionMD5 {

    public static String encrypt(String msg){
        String md5Hex = DigestUtils.md5Hex(msg);
        return md5Hex;
    }

}
