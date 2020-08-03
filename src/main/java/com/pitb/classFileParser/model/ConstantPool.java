package com.pitb.classFileParser.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @package: com.improve.jvm.classFileParser.model
 * @date: 2020-07-29 10:36
 * @author: corey.yang
 */
@Data
public class ConstantPool {
    /*
        常量池 item 内容
        cp_info {
         u1 tag;
         u1 info[];
        }
     */


    /**
     * 常量池中的内容
     */
    @Data
    public static class CpInfo {

        // u1
        private int tag;

        /*
         * 具体内容由子类实现
         */
        // private int[] info;
    }

    @Getter
    @Setter
    public static class CONSTANT_Utf8_info extends CpInfo {
        /*
               CONSTANT_Utf8_info {
                 u1 tag;
                 u2 length;
                 u1 bytes[length];
                }
         */
        private Integer length;
        private String content;
    }

    @Getter
    @Setter
    public static class CONSTANT_Integer_info extends CpInfo {
        /*
                CONSTANT_Integer_info {
                 u1 tag;
                 u4 bytes;
                }
         */
        private Integer content;
    }

    @Getter
    @Setter
    public static class CONSTANT_Float_info extends CpInfo {
        /*
                CONSTANT_Float_info {
                 u1 tag;
                 u4 bytes;
                }
         */
        private Float content;
    }

    @Getter
    @Setter
    public static class CONSTANT_Long_info extends CpInfo {
        /*
            Long 和 Double 是8字节的，需要将高位和低位分开存储
                CONSTANT_Long_info {
                 u1 tag;
                 u4 high_bytes;
                 u4 low_bytes;
                }
        */
        private Long content;
    }

    @Getter
    @Setter
    public static class CONSTANT_Double_info extends CpInfo {
        /*
                CONSTANT_Double_info {
                 u1 tag;
                 u4 high_bytes;
                 u4 low_bytes;
                }
         */
        private Double content;
    }

    @Getter
    @Setter
    public static class CONSTANT_Class_info extends CpInfo {
        /*
                CONSTANT_Class_info {
                 u1 tag;
                 u2 name_index;
                }
         */
        // 指向常量池中一个 CONSTANT_Utf8_info
        private Integer name_index;
    }

    @Getter
    @Setter
    public static class CONSTANT_String_info extends CpInfo {
        /*
                CONSTANT_String_info {
                 u1 tag;
                 u2 string_index;
                }
         */
        // 指向常量池中一个 CONSTANT_Utf8_info
        private Integer string_index;
    }

    @Getter
    @Setter
    public static class CONSTANT_Fieldref_info extends CpInfo {
        /*
                CONSTANT_Fieldref_info {
                 u1 tag;
                // 指向常量池中一个 CONSTANT_Class_info
                 u2 class_index;
                // 指向常量池中一个 CONSTANT_NameAndType_info
                 u2 name_and_type_index;
                }
         */
        private CONSTANT_Class_info class_index;
        private CONSTANT_NameAndType_info name_and_type_index;
    }

    @Getter
    @Setter
    public static class CONSTANT_Methodref_info extends CpInfo {
        /*
                CONSTANT_Methodref_info {
                 u1 tag;
                 u2 class_index;
                 u2 name_and_type_index;
                }
         */
        private Integer class_index;
        private Integer name_and_type_index;
    }

    @Getter
    @Setter
    public static class CONSTANT_InterfaceMethodref_info extends CpInfo {
        /*
                CONSTANT_InterfaceMethodref_info {
                 u1 tag;
                 u2 class_index;
                 u2 name_and_type_index;
                }
         */
        private CONSTANT_Class_info class_index;
        private CONSTANT_NameAndType_info name_and_type_index;
    }

    @Getter
    @Setter
    public static class CONSTANT_NameAndType_info extends CpInfo {
        /*
                CONSTANT_NameAndType_info {
                 u1 tag;
                 u2 name_index;     // CONSTANT_Utf8_info
                 u2 descriptor_index;       // CONSTANT_Utf8_info
                }
         */
        private Integer name_index;
        private Integer descriptor_index;
    }

    @Getter
    @Setter
    public static class CONSTANT_MethodHandle_info extends CpInfo {
        /*
                CONSTANT_MethodHandle_info {
                 u1 tag;
                 u1 reference_kind;
                 u2 reference_index;
                }
         */
        private ReferenceKindEnum reference_kind;
        // 有规定，要看 ReferenceKindEnum 中的
        private ConstantPoolTagEnum reference_index;
    }

    @Getter
    @Setter
    public static class CONSTANT_MethodType_info extends CpInfo {
        /*
                CONSTANT_MethodType_info {
                 u1 tag;
                 u2 descriptor_index;   // CONSTANT_Utf8_info
                }
         */
        private CONSTANT_Utf8_info descriptor_index;
    }

    @Getter
    @Setter
    public static class CONSTANT_Dynamic_info extends CpInfo {
        /*
                CONSTANT_Dynamic_info {
                 u1 tag;
                 u2 bootstrap_method_attr_index;
                 u2 name_and_type_index;
                }
         */
        // valid index into the bootstrap_methods
        private Integer bootstrap_method_attr_index;
        private CONSTANT_NameAndType_info name_and_type_index;
    }

    @Getter
    @Setter
    public static class CONSTANT_InvokeDynamic_info extends CpInfo {
        /*
                CONSTANT_InvokeDynamic_info {
                 u1 tag;
                 u2 bootstrap_method_attr_index;
                 u2 name_and_type_index;
                }
         */

        // valid index into the bootstrap_methods
        private int bootstrap_method_attr_index;
        private CONSTANT_NameAndType_info name_and_type_index;
    }

    @Getter
    @Setter
    public static class CONSTANT_Module_info extends CpInfo {
        /*
                CONSTANT_Module_info {
                 u1 tag;
                 u2 name_index;     // CONSTANT_Utf8_info
                }
         */
        private CONSTANT_Utf8_info name_index;
    }

    @Getter
    @Setter
    public static class CONSTANT_Package_info extends CpInfo {
        /*
                CONSTANT_Package_info {
                 u1 tag;
                 u2 name_index;     // CONSTANT_Utf8_info
                }
         */
        private CONSTANT_Utf8_info name_index;
    }

}
