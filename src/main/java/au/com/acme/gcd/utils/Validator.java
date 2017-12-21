package au.com.acme.gcd.utils;

import org.springframework.util.StringUtils;

import java.math.BigInteger;

public class Validator {

    public static BigInteger validateAndConvert(String n) {
        if (n == null || n.trim().length() == 0) {
            throw new IllegalArgumentException("Must not be null");
        }
        try {
            return new BigInteger(n);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Must be a Number");
        }
    }
}
