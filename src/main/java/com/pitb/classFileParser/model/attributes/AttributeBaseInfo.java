package com.pitb.classFileParser.model.attributes;


import lombok.Data;

/**
 * @package: com.improve.jvm.classFileParser.model.attributes
 * @date: 2020-07-30 18:32
 * @author: corey.yang
 */
@Data
public class AttributeBaseInfo {

    public static final int LENGTH_INDEX = 2;
    public static final int BASE_LENGTH = 6;

    protected Integer attribute_name_index;

    protected Integer attribute_length;
}
