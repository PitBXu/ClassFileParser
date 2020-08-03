package com.pitb.classFileParser.model.attributes;

import lombok.Data;

import java.util.List;

/**
 * @package: com.improve.jvm.classFileParser.model.attributes
 * @date: 2020-07-30 18:23
 * @author: corey.yang
 */
@Data
public class Code_attribute extends AttributeBaseInfo {
    /*
            Code_attribute {
             u2 attribute_name_index;
             u4 attribute_length;
             u2 max_stack;
             u2 max_locals;
             u4 code_length;
             u1 code[code_length];
             u2 exception_table_length;
             { u2 start_pc;
             u2 end_pc;
             u2 handler_pc;
             u2 catch_type;
             } exception_table[exception_table_length];
             u2 attributes_count;
             attribute_info attributes[attributes_count];
            }
     */
    // 这两部分内容已经在外部了
//    private Integer attribute_name_index;
//    private Integer attribute_length;
    private Integer max_stack;
    private Integer max_locals;
    private Integer code_length;
    private List<Integer> code;
    private Integer exception_table_length;
    private List<Exception_table> exception_table;
    private Integer attributes_count;
    private List<AttributeBaseInfo> attributes;


    @Data
    public static class Exception_table {
        private Integer start_pc;
        private Integer end_pc;
        private Integer handler_pc;
        private Integer catch_type;
    }

}
