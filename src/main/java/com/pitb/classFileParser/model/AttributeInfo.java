package com.pitb.classFileParser.model;

import com.pitb.classFileParser.model.attributes.AttributeBaseInfo;
import lombok.Data;

/**
 * 属性
 * @date: 2020-07-29 15:21
 * @author: corey.yang
 */
@Data
@Deprecated
public class AttributeInfo {

    /*
      使用在以下文件中，不同的类型的 属性 info 又不同

      ClassFile, field_info, method_info,
      Code_attribute

    SourceFile                                             ClassFile                     45.3
    InnerClasses                                           ClassFile                     45.3
    EnclosingMethod                                        ClassFile                     49.0
    SourceDebugExtension                                   ClassFile                     49.0
    BootstrapMethods                                       ClassFile                     51.0
    Module, ModulePackages, ModuleMainClass                ClassFile                     53.0
    NestHost, NestMembers                                  ClassFile                     55.0
    ConstantValue                                          field_info                    45.3
    Code                                                   method_info                   45.3
    Exceptions                                             method_info                   45.3
    RuntimeVisibleParameterAnnotations,
    RuntimeInvisibleParameterAnnotations                   method_info                   49.0
    AnnotationDefault                                      method_info                   49.0
    MethodParameters                                       method_info                   52.0
    Synthetic                                              ClassFile,
                                                           field_info,
                                                           method_info                   45.3
    Deprecated                                             ClassFile,
                                                           field_info,
                                                           method_info                   45.3
    Signature                                              ClassFile,
                                                           field_info,
                                                           method_info                   49.0
    RuntimeVisibleAnnotations,
    RuntimeInvisibleAnnotations                            ClassFile,
                                                           field_info,
                                                           method_info                   49.0
    LineNumberTable                                        Code                          45.3
    LocalVariableTable                                     Code                          45.3
    LocalVariableTypeTable                                 Code                          49.0
    StackMapTable                                          Code                          50.0
    RuntimeVisibleTypeAnnotations,
    RuntimeInvisibleTypeAnnotations                        ClassFile,
                                                           field_info,
                                                           method_info,
                                                           Code                          52.0

     */

    /*
            attribute_info {
             u2 attribute_name_index;
             u4 attribute_length;
             u1 info[attribute_length];
            }
     */

    // CONSTANT_Utf8_info
    private Integer attribute_name_index;

    private Integer attribute_length;

    private AttributeBaseInfo info;
}
