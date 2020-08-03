package com.pitb.classFileParser.model.attributes;

import com.pitb.classFileParser.model.ConstantPool;
import com.pitb.classFileParser.util.ClassUtil;
import lombok.Getter;

import java.util.Map;

/**
 * @package: com.pitb.classFileParser.model.attributes
 * @date: 2020-07-31 17:48
 * @author: corey.yang
 */
@Getter
public class SourceFile_attribute extends AttributeBaseInfo {
    /*
            SourceFile_attribute {
             u2 attribute_name_index;
             u4 attribute_length;
             u2 sourcefile_index;
            }
     */

    public static final int LENGTH = 8;

    public SourceFile_attribute(int[] code,  Map<String, ConstantPool.CpInfo> cpInfoMap) {
        ConstantPool.CpInfo cpInfo = cpInfoMap.get("" + ClassUtil.getHex(code, 0, 2));
        ConstantPool.CONSTANT_Utf8_info utf8_info = (ConstantPool.CONSTANT_Utf8_info) cpInfo;
        this.attribute_name_index = utf8_info.getTag();
        this.attribute_length = ClassUtil.getHex(code, 2, 4);
        this.sourcefile_index = ClassUtil.getHex(code, 6, 2);
    }


    private Integer sourcefile_index;
}
