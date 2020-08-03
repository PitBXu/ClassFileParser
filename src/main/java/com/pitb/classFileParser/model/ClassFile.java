package com.pitb.classFileParser.model;

import com.pitb.classFileParser.model.attributes.AttributeBaseInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 一个 class 文件里的所有内容
 * @date: 2020-07-29 10:26
 * @author: corey.yang
 */
@Data
public class ClassFile {

    /*
        ClassFile {
             u4 magic;
             u2 minor_version;
             u2 major_version;
             u2 constant_pool_count;
             cp_info constant_pool[constant_pool_count-1];
             u2 access_flags;
             u2 this_class;
             u2 super_class;
             u2 interfaces_count;
             u2 interfaces[interfaces_count];
             u2 fields_count;
             field_info fields[fields_count];
             u2 methods_count;
             method_info methods[methods_count];
             u2 attributes_count;
             attribute_info attributes[attributes_count];
        }
     */

    /** u4 - 逻辑号码 */
    private String magicNumber;

    /** u2 - 子版本号 */
    private Integer minorVersion;

    /** u2 - 主版本号 */
    private Integer majorVersion;

    /** u2 - 常量池大小 */
    private Integer constantPoolCount;

    /** 常量池内容 len: constant_pool_count-1 */
    private Map<String, ConstantPool.CpInfo> constantPool;

    /** u2 按位运算计算出多个 AccessFlagClassEnum */
    private List<AccessFlagClassEnum> accessFlags;

    /** u2 this_class -> 指向常量池中的序号, 对应位置必须是一个 CONSTANT_Class_info */
    private Integer thisClass;

    /** u2 super_class -> 指向常量池中的序号, 对应位置必须是一个 CONSTANT_Class_info or 可以为0 */
    private Integer superClass;

    /** u2  */
    private Integer interfacesCount;

    /** 索引，指向 CONSTANT_Class_info */
    private List<Integer> interfaces;

    /** u2  */
    private Integer fieldsCount;

    /** FieldInfo */
    private List<FieldInfo> fields;

    /**
     * The value of the methods_count item gives the number of method_info
     * structures in the methods table.
     */
    private Integer methodsCount;


    private List<MethodInfo> methods;

    /**
     * u2
     */
    private Integer attributesCount;

    private List<AttributeBaseInfo> attributes;
}
