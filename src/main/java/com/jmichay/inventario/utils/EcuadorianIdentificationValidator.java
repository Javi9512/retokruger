package com.jmichay.inventario.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EcuadorianIdentificationValidator implements ConstraintValidator<EcuadorianIdentification, String> {
    @Override
    public boolean isValid(String identification, ConstraintValidatorContext context) {
        int suma = 0;
        if (identification == null || identification.length() != 10) {
            return false;
        } else {
            int a[] = new int[identification.length() / 2];
            int b[] = new int[(identification.length() / 2)];
            int c = 0;
            int d = 1;
            for (int i = 0; i < identification.length() / 2; i++) {
                a[i] = Integer.parseInt(String.valueOf(identification.charAt(c)));
                c = c + 2;
                if (i < (identification.length() / 2) - 1) {
                    b[i] = Integer.parseInt(String.valueOf(identification.charAt(d)));
                    d = d + 2;
                }
            }

            for (int i = 0; i < a.length; i++) {
                a[i] = a[i] * 2;
                if (a[i] > 9) {
                    a[i] = a[i] - 9;
                }
                suma = suma + a[i] + b[i];
            }
            int aux = suma / 10;
            int dec = (aux + 1) * 10;
            if ((dec - suma) == Integer.parseInt(String.valueOf(identification.charAt(identification.length() - 1))))
                return true;
            else if (suma % 10 == 0 && identification.charAt(identification.length() - 1) == '0') {
                return true;
            } else {
                return false;
            }
        }
    }
}
