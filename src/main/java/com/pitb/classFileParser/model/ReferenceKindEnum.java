package com.pitb.classFileParser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.pitb.classFileParser.model.ConstantPoolTagEnum.*;

@Getter
@AllArgsConstructor
public enum ReferenceKindEnum {

    /**
     * ReferenceKind
     * The value of the reference_index item must be a valid index into the
     * constant_pool table. The constant_pool entry at that index must be as
     * follows:
     */
    REF_getField(1, CONSTANT_Fieldref),
    REF_getStatic(2, CONSTANT_Fieldref),
    REF_putField(3, CONSTANT_Fieldref),
    REF_putStatic(4, CONSTANT_Fieldref),

    REF_invokeVirtual(5, CONSTANT_Methodref),
    REF_newInvokeSpecial(8, CONSTANT_Methodref),


    /**
     * jdk 1.7 及以前，必须为 CONSTANT_Methodref
     * jdk 1.8 及以后，除了 CONSTANT_Methodref 还可以为 CONSTANT_InterfaceMethodref
     */
    REF_invokeStatic(6, CONSTANT_Methodref),
    REF_invokeSpecial(7, CONSTANT_Methodref),


    REF_invokeInterface(9, CONSTANT_InterfaceMethodref)

    ;

    private int kind;

    private ConstantPoolTagEnum tag;

}
