package com.pitb.classFileParser.model;

import com.pitb.classFileParser.model.attributes.AttributeBaseInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @package: com.improve.jvm.classFileParser.model
 * @date: 2020-07-29 15:14
 * @author: corey.yang
 */
@Data
public class FieldInfo {
    /*
            field_info {
             u2 access_flags;
             u2 name_index;
             u2 descriptor_index;
             u2 attributes_count;
             attribute_info attributes[attributes_count];
            }
     */

    @Getter
    @AllArgsConstructor
    private enum ACCESS_FLAGS {
        /**
         * field_info.access_flags;
         */
        ACC_PUBLIC(0x0001),
        ACC_PRIVATE(0x0002),
        ACC_PROTECTED(0x0004),
        ACC_STATIC(0x0008),
        ACC_FINAL(0x0010),
        ACC_VOLATILE(0x0040),
        ACC_TRANSIENT(0x0080),
        ACC_SYNTHETIC(0x1000),
        ACC_ENUM(0x4000);
        int code;
    }

    /**
     * 上面枚举中多个相与的结果
     */
    private List<AccessFlagFieldEnum> access_flags;

    private Integer name_index;

    private Integer descriptor_index;

    private Integer attributes_count;

    private List<AttributeBaseInfo> attributes;

}
