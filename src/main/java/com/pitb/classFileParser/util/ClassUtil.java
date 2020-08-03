package com.pitb.classFileParser.util;

import com.pitb.classFileParser.BusinessException;

import java.math.BigInteger;

/**
 * @package: com.pitb.classFileParser.util
 * @date: 2020-07-31 17:56
 * @author: corey.yang
 */
public class ClassUtil {

    public static String getHexString(int[] code, int start, int size) {
        int end = start + size;
        if (start < 0 || end > code.length) {
            throw BusinessException.of("index out of bounds");
        }
        String res = "";
        for (int i = 0; start + i < end; i++) {
            String val = Integer.toHexString(code[start + i]);
            if (val.length() < 2) {
                val = "0" + val;
            }
            res = res + val;
        }
        return res;
    }

    public static Integer getHex(int[] code, int start, int size) {
        return new BigInteger(getHexString(code, start, size), 16).intValue();
    }
}
