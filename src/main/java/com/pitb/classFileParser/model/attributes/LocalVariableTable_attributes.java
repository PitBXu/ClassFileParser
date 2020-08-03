package com.pitb.classFileParser.model.attributes;

import com.pitb.classFileParser.model.ConstantPool;
import com.pitb.classFileParser.util.ClassUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @package: com.pitb.classFileParser.model.attributes
 * @date: 2020-07-31 17:38
 * @author: corey.yang
 */
@Data
public class LocalVariableTable_attributes extends AttributeBaseInfo {
    /*
            LocalVariableTable_attribute {
             u2 attribute_name_index;
             u4 attribute_length;
             u2 local_variable_table_length;
             { u2 start_pc;
             u2 length;
             u2 name_index;
             u2 descriptor_index;
             u2 index;
             } local_variable_table[local_variable_table_length];
            }
     */

    private static final int TABLE_LEN = 10;


    public LocalVariableTable_attributes(int[] code, Map<String, ConstantPool.CpInfo> cpInfoMap) {
        ConstantPool.CpInfo cpInfo = cpInfoMap.get("" + ClassUtil.getHex(code, 0, 2));
        ConstantPool.CONSTANT_Utf8_info utf8_info = (ConstantPool.CONSTANT_Utf8_info) cpInfo;
        this.attribute_name_index = utf8_info.getTag();
        this.attribute_length = ClassUtil.getHex(code, 2, 4);
        this.local_variable_table_length = ClassUtil.getHex(code, 6, 2);
        List<LocalVariableTable> localVariableTables = new ArrayList<>();
        for (Integer i = 0; i < local_variable_table_length; i++) {
            LocalVariableTable_attributes.LocalVariableTable table = new LocalVariableTable_attributes.LocalVariableTable();
            table.setStart_pc(ClassUtil.getHex(code, i * TABLE_LEN + 8, 2));
            table.setLength(ClassUtil.getHex(code, i * TABLE_LEN + 10, 2));
            table.setName_index(ClassUtil.getHex(code, i * TABLE_LEN + 12, 2));
            table.setDescriptor_index(ClassUtil.getHex(code, i * TABLE_LEN + 14, 2));
            table.setIndex(ClassUtil.getHex(code, i * TABLE_LEN + 16, 2));
            localVariableTables.add(table);
        }
        this.local_variable_table = localVariableTables;
    }

    private Integer local_variable_table_length;
    private List<LocalVariableTable> local_variable_table;


    @Data
    public static class LocalVariableTable {
        private Integer start_pc;
        private Integer length;
        private Integer name_index;
        private Integer descriptor_index;
        private Integer index;
    }
}
