package com.pitb.classFileParser.model.attributes;

import com.pitb.classFileParser.model.ConstantPool;
import com.pitb.classFileParser.util.ClassUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @package: com.pitb.classFileParser.model.attributes
 * @date: 2020-07-31 17:21
 * @author: corey.yang
 */
@Data
public class LineNumberTable_attribute extends AttributeBaseInfo {

    /*
            LineNumberTable_attribute {
             u2 attribute_name_index;
             u4 attribute_length;
             u2 line_number_table_length;
             {  u2 start_pc;
                u2 line_number;
             } line_number_table[line_number_table_length];
            }
     */

    private static final int TABLE_LEN = 4;

    public LineNumberTable_attribute(int[] code, Map<String, ConstantPool.CpInfo> cpInfoMap) {
        ConstantPool.CpInfo cpInfo = cpInfoMap.get("" + ClassUtil.getHex(code, 0, 2));
        ConstantPool.CONSTANT_Utf8_info utf8_info = (ConstantPool.CONSTANT_Utf8_info) cpInfo;
        this.attribute_name_index = utf8_info.getTag();
        this.attribute_length = ClassUtil.getHex(code, 2, 4);
        this.line_number_table_length = ClassUtil.getHex(code, 6, 2);
        List<LineNumberTable> lineNumberTables = new ArrayList<>();
        for (Integer i = 0; i < line_number_table_length; i++) {
            LineNumberTable_attribute.LineNumberTable table = new LineNumberTable_attribute.LineNumberTable();
            table.setStart_pc(ClassUtil.getHex(code, i * TABLE_LEN + 8, 2));
            table.setLine_number(ClassUtil.getHex(code, i * TABLE_LEN + 10, 2));
            lineNumberTables.add(table);
        }
        this.line_number_table = lineNumberTables;
    }

    private Integer line_number_table_length;
    private List<LineNumberTable> line_number_table;


    @Data
    public static class LineNumberTable {
        private Integer start_pc;
        private Integer line_number;
    }

}
