package com.pitb.classFileParser.Assert;

import org.apache.commons.lang3.StringUtils;

/**
 * @package: com.improve.jvm.classFileParser.Assert
 * @date: 2020-07-30 10:00
 * @author: corey.yang
 */
public class Assert {

    public static void assertEquals(String source, String target) {
        if (StringUtils.isEmpty(source) && StringUtils.isEmpty(target)) {
            throw new RuntimeException("null of param String");
        }
        if (!source.equals(target)) {
            throw new RuntimeException("not equals|param1:{"+source+"}|param2:{"+target+"}");
        }
    }
}
