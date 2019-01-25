package com.qs.p2p.dao;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class TypeUtils {
    public static final String castToString(Object value) {
        if (value == null) {
            return null;
        }

        return value.toString();
    }

    public static final Byte castToByte(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Byte) {
            return (Byte) value;
        }

        if (value instanceof Number) {
            return ((Number) value).byteValue();
        }

        if (value instanceof String) {
            String s = (String) value;
            if (s.isEmpty()) {
                return null;
            }
            return Byte.valueOf(s);
        }

        throw new RuntimeException("Can not cast to Byte, value: " + value);
    }

    public static final Short castToShort(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Short) {
            return (Short) value;
        }

        if (value instanceof Number) {
            return ((Number) value).shortValue();
        }

        if (value instanceof String) {
            String s = (String) value;
            if (s.isEmpty()) {
                return null;
            }
            return Short.valueOf(s);
        }

        throw new RuntimeException("Can not cast to Short, value: " + value);
    }

    public static final Integer castToInteger(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Integer) {
            return (Integer) value;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue();
        }

        if (value instanceof String) {
            String s = (String) value;
            if (s.isEmpty()) {
                return null;
            }
            return Integer.valueOf(s);
        }

        throw new RuntimeException("Can not cast to Integer, value: " + value);
    }

    public static final Long castToLong(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Long) {
            return (Long) value;
        }

        if (value instanceof Number) {
            return ((Number) value).longValue();
        }

        if (value instanceof String) {
            String s = (String) value;
            if (s.isEmpty()) {
                return null;
            }
            return Long.valueOf(s);
        }

        throw new RuntimeException("Can not cast to Long, value: " + value);
    }

    public static final Float castToFloat(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Float) {
            return (Float) value;
        }

        if (value instanceof Number) {
            return ((Number) value).floatValue();
        }

        if (value instanceof String) {
            String s = (String) value;
            if (s.isEmpty()) {
                return null;
            }
            return Float.valueOf(s);
        }

        throw new RuntimeException("Can not cast to Float, value: " + value);
    }

    public static final Double castToDouble(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Double) {
            return (Double) value;
        }

        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        if (value instanceof String) {
            String s = (String) value;
            if (s.isEmpty()) {
                return null;
            }
            return Double.valueOf(s);
        }

        throw new RuntimeException("Can not cast to Double, value: " + value);
    }

    public static final BigInteger castToBigInteger(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof BigInteger) {
            return (BigInteger) value;
        }

        if (value instanceof Number) {
            return BigInteger.valueOf(((Number) value).longValue());
        }

        if (value instanceof String) {
            String s = (String) value;
            if (s.isEmpty()) {
                return null;
            }
            return new BigInteger(s);
        }

        throw new RuntimeException("Can not cast to BigInteger, value: " + value);
    }

    public static final BigDecimal castToBigDecimal(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }

        if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        }

        if (value instanceof Byte
                || value instanceof Short
                || value instanceof Integer
                || value instanceof Long) {
            return BigDecimal.valueOf(((Number) value).longValue());
        }

        if (value instanceof Float
                || value instanceof Double
                || value instanceof String) {
            String s = value.toString();
            if (s.isEmpty()) {
                return null;
            }
            return new BigDecimal(s);
        }

        throw new RuntimeException("Can not cast to BigDecimal, value: " + value);
    }

    public static final Character castToCharacter(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Character) {
            return (Character) value;
        }

        if (value instanceof String) {
            String s = (String) value;
            if (s.isEmpty()) {
                return null;
            }
            if (s.length() != 1) {
                throw new RuntimeException("Can not cast to Character, value: " + value);
            }
        }

        throw new RuntimeException("Can not cast to Character, value: " + value);
    }

    private TypeUtils() {
    }
}
