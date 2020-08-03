package com.pitb.classFileParser.model;

import com.pitb.classFileParser.model.attributes.AttributeBaseInfo;
import lombok.Data;

import java.util.List;

/**
 * @package: com.improve.jvm.classFileParser.model
 * @date: 2020-07-30 16:35
 * @author: corey.yang
 */
@Data
public class MethodInfo {
    /*
            method_info {
             u2 access_flags;
             u2 name_index;
             u2 descriptor_index;
             u2 attributes_count;
             attribute_info attributes[attributes_count];
            }
     */

    private List<AccessFlagMethodEnum> access_flags;
    private Integer name_index;
    private Integer descriptor_index;
    private Integer attributes_count;
    private List<AttributeBaseInfo> attributes;
}
