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
 * @date: 2020-07-30 16:17
 * @author: corey.yang
 */
@Getter
@AllArgsConstructor
public enum AccessFlagFieldEnum {

    /**
     * Declared public; may be accessed from outside its
     * package.
     */
    ACC_PUBLIC(0x0001),
    /**
     * Declared private; accessible only within the
     * defining class and other classes belonging to the same
     * nest (§5.4.4).
     */
    ACC_PRIVATE(0x0002),
    /**
     * Declared protected; may be accessed within
     * subclasses.
     */
    ACC_PROTECTED(0x0004),
    /**
     * Declared static.
     */
    ACC_STATIC(0x0008),
    /**
     * Declared final; never directly assigned to after
     * object construction (JLS §17.5).
     */
    ACC_FINAL(0x0010),
    /**
     * Declared volatile; cannot be cached.
     */
    ACC_VOLATILE(0x0040),
    /**
     * Declared transient; not written or read by a
     * persistent object manager.
     */
    ACC_TRANSIENT(0x0080),
    /**
     * Declared synthetic; not present in the source code.
     */
    ACC_SYNTHETIC(0x1000),
    /**
     * Declared as an element of an enum.
     */
    ACC_ENUM(0x4000),

    ;

    /**
     * 将 val 转换为相应的权限
     */
    public static List<AccessFlagFieldEnum> getAccess(Integer val) {
        List<AccessFlagFieldEnum> allSeq = Arrays.stream(AccessFlagFieldEnum.values())
                .sorted(Comparator.comparingInt(AccessFlagFieldEnum::getVal).reversed())
                .collect(Collectors.toList());

        List<AccessFlagFieldEnum> list = new ArrayList<>();
        for (AccessFlagFieldEnum anEnum : allSeq) {
            if (val >= anEnum.val) {
                list.add(anEnum);
                val -= anEnum.val;
            }
        }
        return list;
    }


    private int val;
}
