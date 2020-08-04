package com.pitb.classFileParser;


import com.alibaba.fastjson.JSON;
import com.pitb.classFileParser.Assert.Assert;
import com.pitb.classFileParser.model.*;
import com.pitb.classFileParser.model.attributes.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pitb.classFileParser.model.ConstantPool.*;

/**
 * @package: com.improve.jvm.classFileParser
 * @date: 2020-07-29 09:55
 * @author: corey.yang
 */
public class Main {
    private static int index = 0;

    private static final String SIGN = "cafebabe";

    /**
     * 一个空类的 class 文件字节码数组
     * package com.improve;
     * public class Test {
     * }
     */
    private static int[] byteCode = new int[]{
            // u4 magic
            0xca, 0xfe, 0xba, 0xbe,
            // u2 minor_version = 0
            0x00, 0x00,
            // u2 major_version = 52
            0x00, 0x34,
            // u2 constant_pool_count = 16
            0x00, 0x10,
            // constant pool
            0x0a, 0x00, 0x03, 0x00, 0x0d, 0x07,
            0x00, 0x0e, 0x07, 0x00, 0x0f, 0x01, 0x00, 0x06, 0x3c, 0x69, 0x6e, 0x69, 0x74, 0x3e, 0x01, 0x00,
            0x03, 0x28, 0x29, 0x56, 0x01, 0x00, 0x04, 0x43, 0x6f, 0x64, 0x65, 0x01, 0x00, 0x0f, 0x4c, 0x69,
            0x6e, 0x65, 0x4e, 0x75, 0x6d, 0x62, 0x65, 0x72, 0x54, 0x61, 0x62, 0x6c, 0x65, 0x01, 0x00, 0x12,
            0x4c, 0x6f, 0x63, 0x61, 0x6c, 0x56, 0x61, 0x72, 0x69, 0x61, 0x62, 0x6c, 0x65, 0x54, 0x61, 0x62,
            0x6c, 0x65, 0x01, 0x00, 0x04, 0x74, 0x68, 0x69, 0x73, 0x01, 0x00, 0x12, 0x4c, 0x63, 0x6f, 0x6d,
            0x2f, 0x69, 0x6d, 0x70, 0x72, 0x6f, 0x76, 0x65, 0x2f, 0x54, 0x65, 0x73, 0x74, 0x3b, 0x01, 0x00,
            0x0a, 0x53, 0x6f, 0x75, 0x72, 0x63, 0x65, 0x46, 0x69, 0x6c, 0x65, 0x01, 0x00, 0x09, 0x54, 0x65,
            0x73, 0x74, 0x2e, 0x6a, 0x61, 0x76, 0x61, 0x0c, 0x00, 0x04, 0x00, 0x05, 0x01, 0x00, 0x10, 0x63,
            0x6f, 0x6d, 0x2f, 0x69, 0x6d, 0x70, 0x72, 0x6f, 0x76, 0x65, 0x2f, 0x54, 0x65, 0x73, 0x74, 0x01,
            0x00, 0x10, 0x6a, 0x61, 0x76, 0x61, 0x2f, 0x6c, 0x61, 0x6e, 0x67, 0x2f, 0x4f, 0x62, 0x6a, 0x65,
            0x63, 0x74, 0x00, 0x21, 0x00, 0x02, 0x00, 0x03, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x01,
            0x00, 0x04, 0x00, 0x05, 0x00, 0x01,
            // #6 = Code
            0x00, 0x06,
            // Code 长度 = 47
            0x00, 0x00, 0x00, 0x2f,
            // max_stacks
            0x00, 0x01,
            // max_locals
            0x00, 0x01,
            // 命令长度
            0x00, 0x00, 0x00, 0x05,
            // 5个命令（实际是3个命令 + 两个参数）
            0x2a, 0xb7, 0x00, 0x01, 0xb1,
            // exception_table 长度
            0x00, 0x00,
            // attribute_count = 2
            0x00, 0x02,

            0x00, 0x07, 0x00, 0x00, 0x00, 0x06,
            // content
            0x00, 0x01, 0x00, 0x00, 0x00, 0x0a,
            0x00, 0x08, 0x00, 0x00, 0x00, 0x0c,
            // content
            0x00, 0x01, 0x00, 0x00, 0x00, 0x05, 0x00, 0x09, 0x00, 0x0a, 0x00, 0x00,

            0x00, 0x01,

            0x00, 0x0b, 0x00,
            0x00, 0x00, 0x02, 0x00, 0x0c
    };

    public static void main(String[] args) {
        ClassFile classFile = new ClassFile();
        classFile.setMagicNumber(Integer.toHexString(nextHex(4)));
        Assert.assertEquals(classFile.getMagicNumber(), SIGN);
        classFile.setMinorVersion(nextHex(2));
        classFile.setMajorVersion(nextHex(2));
        classFile.setConstantPoolCount(nextHex(2));
        Map<String, CpInfo> cpInfoMap = new HashMap<>();
        for (int i = 1; i < classFile.getConstantPoolCount(); i++) {
            ConstantPoolTagEnum tagEnum = ConstantPoolTagEnum.getInstance(nextByte());
            switch (tagEnum) {
                case CONSTANT_Utf8:
                    CONSTANT_Utf8_info utf8Info = new CONSTANT_Utf8_info();
                    utf8Info.setTag(tagEnum.getTag());
                    utf8Info.setLength(nextHex(2));
                    utf8Info.setContent(hexToString(nextHexArr(utf8Info.getLength())));
                    cpInfoMap.put("" + i, utf8Info);
                    break;
                case CONSTANT_Integer:
                case CONSTANT_Float:
                case CONSTANT_Long:
                case CONSTANT_Double:
                    break;
                case CONSTANT_Class:
                    CONSTANT_Class_info classInfo = new CONSTANT_Class_info();
                    classInfo.setTag(tagEnum.getTag());
                    classInfo.setName_index(nextHex(2));
                    cpInfoMap.put("" + i, classInfo);
                    break;
                case CONSTANT_String:
                case CONSTANT_Fieldref:
                    break;
                case CONSTANT_Methodref:
                    CONSTANT_Methodref_info methodInfo = new CONSTANT_Methodref_info();
                    methodInfo.setTag(tagEnum.getTag());
                    methodInfo.setClass_index(nextHex(2));
                    methodInfo.setName_and_type_index(nextHex(2));
                    cpInfoMap.put("" + i, methodInfo);
                    break;
                case CONSTANT_InterfaceMethodref:
                    break;
                case CONSTANT_NameAndType:
                    CONSTANT_NameAndType_info nameAndTypeInfo = new CONSTANT_NameAndType_info();
                    nameAndTypeInfo.setTag(tagEnum.getTag());
                    nameAndTypeInfo.setName_index(nextHex(2));
                    nameAndTypeInfo.setDescriptor_index(nextHex(2));
                    cpInfoMap.put("" + i, nameAndTypeInfo);
                    break;
                case CONSTANT_MethodHandle:
                case CONSTANT_MethodType:
                case CONSTANT_Dynamic:
                case CONSTANT_InvokeDynamic:
                case CONSTANT_Module:
                case CONSTANT_Package:
                default:
                    break;
            }
        }
        classFile.setConstantPool(cpInfoMap);
        classFile.setAccessFlags(AccessFlagClassEnum.getAccess(nextHex(2)));
        classFile.setThisClass(nextHex(2));
        classFile.setSuperClass(nextHex(2));
        classFile.setInterfacesCount(nextHex(2));
        List<Integer> interfaces = new ArrayList<>();
        for (Integer i = 0; i < classFile.getInterfacesCount(); i++) {
            interfaces.add(nextHex(2));
        }
        classFile.setInterfaces(interfaces);
        classFile.setFieldsCount(nextHex(2));
        List<FieldInfo> fieldInfos = new ArrayList<>();
        for (Integer i = 0; i < classFile.getFieldsCount(); i++) {
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setAccess_flags(AccessFlagFieldEnum.getAccess(nextHex(2)));
            fieldInfo.setName_index(nextHex(2));
            fieldInfo.setDescriptor_index(nextHex(2));
            fieldInfo.setAttributes_count(nextHex(2));
            List<AttributeBaseInfo> attributeInfoList = new ArrayList<>();
            for (Integer j = 0; j < fieldInfo.getAttributes_count(); j++) {
                AttributeBaseInfo info = new AttributeBaseInfo();
                info.setAttribute_name_index(nextHex(2));
                info.setAttribute_length(nextHex(4));
                nextHex(info.getAttribute_length());
                attributeInfoList.add(info);
            }
            fieldInfo.setAttributes(attributeInfoList);
            fieldInfos.add(fieldInfo);
        }
        classFile.setFields(fieldInfos);
        classFile.setMethodsCount(nextHex(2));
        List<MethodInfo> methodInfoList = new ArrayList<>();
        for (Integer i = 0; i < classFile.getMethodsCount(); i++) {
            MethodInfo methodInfo = new MethodInfo();
            methodInfo.setAccess_flags(AccessFlagMethodEnum.getAccess(nextHex(2)));
            methodInfo.setName_index(nextHex(2));
            methodInfo.setDescriptor_index(nextHex(2));
            methodInfo.setAttributes_count(nextHex(2));
            List<AttributeBaseInfo> attributeInfoList = new ArrayList<>();
            for (Integer j = 0; j < methodInfo.getAttributes_count(); j++) {
                attributeInfoList.add(getAttributeInfo(cpInfoMap));
            }
            methodInfo.setAttributes(attributeInfoList);
            methodInfoList.add(methodInfo);
        }
        classFile.setMethods(methodInfoList);
        classFile.setAttributesCount(nextHex(2));
        List<AttributeBaseInfo> attributeInfoList = new ArrayList<>();
        for (Integer j = 0; j < classFile.getAttributesCount(); j++) {
            AttributeBaseInfo info = getAttributeInfo(cpInfoMap);
            attributeInfoList.add(info);
        }
        classFile.setAttributes(attributeInfoList);

        System.out.println();
        System.out.println(JSON.toJSONString(classFile));
    }

    /**
     * 根据类型获取一个 AttributeInfo
     */
    private static AttributeBaseInfo getAttributeInfo(Map<String, CpInfo> cpInfoMap) {
        CpInfo cpInfo = cpInfoMap.get("" + peekHex(2));
        CONSTANT_Utf8_info utf8_info = (CONSTANT_Utf8_info) cpInfo;
        AttributeNameEnum attributeNameEnum = AttributeNameEnum.getInstance(utf8_info.getContent());
        switch (attributeNameEnum) {
            case Code:
                Code_attribute code_attribute = new Code_attribute();
                nextHex(2);
                code_attribute.setAttribute_name_index(utf8_info.getTag());
                code_attribute.setAttribute_length(nextHex(4));
                code_attribute.setMax_stack(nextHex(2));
                code_attribute.setMax_locals(nextHex(2));
                code_attribute.setCode_length(nextHex(4));
                List<Integer> codeList = new ArrayList<>();
                for (Integer i = 0; i < code_attribute.getCode_length(); i++) {
                    codeList.add(nextByte());
                }
                code_attribute.setCode(codeList);
                code_attribute.setException_table_length(nextHex(2));
                List<Code_attribute.Exception_table> exceptionTableList = new ArrayList<>();
                for (Integer i = 0; i < code_attribute.getException_table_length(); i++) {
                    Code_attribute.Exception_table exceptionTable = new Code_attribute.Exception_table();
                    exceptionTable.setStart_pc(nextHex(2));
                    exceptionTable.setEnd_pc(nextHex(2));
                    exceptionTable.setHandler_pc(nextHex(2));
                    exceptionTable.setCatch_type(nextHex(2));
                    exceptionTableList.add(exceptionTable);
                }
                code_attribute.setException_table(exceptionTableList);
                code_attribute.setAttributes_count(nextHex(2));
                List<AttributeBaseInfo> attributes = new ArrayList<>();
                for (Integer i = 0; i < code_attribute.getAttributes_count(); i++) {
                    attributes.add(getAttributeInfo(cpInfoMap));
                }
                code_attribute.setAttributes(attributes);
                return code_attribute;
            case LineNumberTable:
                int LNTlength = targetHex(AttributeBaseInfo.LENGTH_INDEX, AttributeBaseInfo.BASE_LENGTH);
                return new LineNumberTable_attribute(subArr(LNTlength + AttributeBaseInfo.BASE_LENGTH), cpInfoMap);
            case LocalVariableTable:
                int LVTlength = targetHex(AttributeBaseInfo.LENGTH_INDEX, AttributeBaseInfo.BASE_LENGTH);
                return new LocalVariableTable_attributes(subArr(LVTlength + AttributeBaseInfo.BASE_LENGTH), cpInfoMap);
            case SourceFile:
                return new SourceFile_attribute(subArr(SourceFile_attribute.LENGTH), cpInfoMap);
            default:
                return null;
        }
    }

    /**
     * 16进制转换成为string类型字符串
     */
    public static String hexToString(Integer[] num) {
        byte[] baKeyword = new byte[num.length];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & num[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            return new String(baKeyword, "UTF-8");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private static int[] subArr(int length) {
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = byteCode[index++];
        }
        return res;
    }

    private static int nextByte() {
        int target = byteCode[index++];
        System.out.print(Integer.toHexString(target) + " ");
        return target;
    }


    private static Integer nextHex(int num) {
        return new BigInteger(nextHexStr(num), 16).intValue();
    }

    private static Integer peekHex(int num) {
        return new BigInteger(peekHexStr(num), 16).intValue();
    }

    private static String nextHexStr(int num) {
        String res = "";
        while (num-- > 0) {
            String val = Integer.toHexString(nextByte());
            if (val.length() < 2) {
                val = "0" + val;
            }
            res = res + val;
        }
        return res;
    }

    private static String peekHexStr(int num) {
        String res = "";
        for (int i = 0; i < num; i++) {
            String val = Integer.toHexString(byteCode[index + i]);
            if (val.length() < 2) {
                val = "0" + val;
            }
            res = res + val;
        }
        return res;
    }

    private static Integer targetHex(int start, int end) {
        String res = "";
        for (int i = start; i < end; i++) {
            String val = Integer.toHexString(byteCode[index + i]);
            if (val.length() < 2) {
                val = "0" + val;
            }
            res = res + val;
        }
        return new BigInteger(res, 16).intValue();
    }

    private static Integer[] nextHexArr(int num) {
        Integer[] res = new Integer[num];
        int i = 0;
        while (num-- > 0) {
            res[i++] = nextByte();
        }
        return res;
    }
}
