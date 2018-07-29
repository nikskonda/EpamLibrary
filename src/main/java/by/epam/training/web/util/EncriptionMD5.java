package by.epam.training.web.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * The class is designed to encrypt a message
 *
 * @author  Nikita Shkonda
 */
public class EncriptionMD5 {

    /**
     * The method returns an encrypted message.
     *
     * @param  msg - message for encrypting.
     *
     * @return an encrypted message.
     *
     * @author  Nikita Shkonda
     *
     */
    public static String encrypt(String msg){
        String md5Hex = DigestUtils.md5Hex(msg);
        return md5Hex;
    }

}
