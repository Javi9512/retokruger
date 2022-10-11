package com.jmichay.inventario.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = EcuadorianIdentificationValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EcuadorianIdentification {
    String message() default "Numero de cedula invalido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}