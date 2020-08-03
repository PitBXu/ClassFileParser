package com.pitb.classFileParser.model;

import com.pitb.classFileParser.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConstantPoolTagEnum {

    /**
     *  常量池中不同 tag 的值
     */

    CONSTANT_Utf8(1),
    CONSTANT_Integer(3),
    CONSTANT_Float(4),
    CONSTANT_Long(5),
    CONSTANT_Double(6),
    CONSTANT_Class(7),
    CONSTANT_String(8),
    CONSTANT_Fieldref(9),

    /**
     *  <init>
     *  <clinit>
     * init is the (or one of the) constructor(s) for the instance, and non-static field initialization.
     * clinit are the static initialization blocks for the class, and static field initialization.
     */
    CONSTANT_Methodref(10),
    CONSTANT_InterfaceMethodref(11),
    CONSTANT_NameAndType(12),
    // @since 51.0 jdk7
    CONSTANT_MethodHandle(15),
    // @since 51.0 jdk7
    CONSTANT_MethodType(16),

    /*
        @since 55.0 jdk11
        The CONSTANT_Dynamic_info structure is used to represent a dynamicallycomputed constant, an arbitrary value that is produced by invocation of a
        bootstrap method in the course of an ldc instruction (§ldc), among others. The
        auxiliary type specified by the structure constrains the type of the dynamicallycomputed constant.
     */
    CONSTANT_Dynamic(17),

    /*
        @since 51.0 jdk7
        The CONSTANT_InvokeDynamic_info structure is used to represent a
        dynamically-computed call site, an instance of java.lang.invoke.CallSite
        that is produced by invocation of a bootstrap method in the course of an
        invokedynamic instruction (§invokedynamic). The auxiliary type specified by the
        structure constrains the method type of the dynamically-computed call site.
     */
    CONSTANT_InvokeDynamic(18),

    // @since 53.0 jdk9
    CONSTANT_Module(19),
    // @since 53.0 jdk9
    CONSTANT_Package(20);

    public static ConstantPoolTagEnum getInstance(int tag) {
        for (ConstantPoolTagEnum value : ConstantPoolTagEnum.values()) {
            if (value.getTag() == tag) {
                return value;
            }
        }
        throw BusinessException.of("No such instance:" + tag);
    }

    private int tag;
}
