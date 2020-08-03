package com.pitb.classFileParser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @package: com.improve.jvm.classFileParser.model
 * @date: 2020-07-29 10:57
 * @author: corey.yang
 */
@Getter
@AllArgsConstructor
public enum AccessFlagClassEnum {


    /**
     * Declared public; may be accessed from outside its
     * package.
     */
    ACC_PUBLIC(0x0001),
    /**
     * Declared final; no subclasses allowed.
     */
    ACC_FINAL(0x0010),
    /**
     * Treat superclass methods specially when invoked by
     * the invokespecial instruction.
     */
    ACC_SUPER(0x0020),
    /**
     * Is an interface, not a class.
     */
    ACC_INTERFACE(0x0200),
    /**
     * Declared abstract; must not be instantiated.
     */
    ACC_ABSTRACT(0x0400),
    /**
     * Declared synthetic; not present in the source code.
     */
    ACC_SYNTHETIC(0x1000),
    /**
     * Declared as an annotation type.
     */
    ACC_ANNOTATION(0x2000),
    /**
     * Declared as an enum type.
     */
    ACC_ENUM(0x4000),
    /**
     * Is a module, not a class or interface.
     */
    ACC_MODULE(0x8000)


    ;

    public static List<AccessFlagClassEnum> getAccess(Integer val) {
        List<AccessFlagClassEnum> allSeq = Arrays.stream(AccessFlagClassEnum.values())
                .sorted(Comparator.comparingInt(AccessFlagClassEnum::getVal).reversed())
                .collect(Collectors.toList());

        List<AccessFlagClassEnum> list = new ArrayList<>();
        for (AccessFlagClassEnum anEnum : allSeq) {
            if (val >= anEnum.val) {
                list.add(anEnum);
                val -= anEnum.val;
            }
        }
        return list;
    }


    private int val;
}
