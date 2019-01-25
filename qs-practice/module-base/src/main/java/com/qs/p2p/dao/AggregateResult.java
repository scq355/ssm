package com.qs.p2p.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class AggregateResult {
    private final Map<String, Object> result;

    public AggregateResult() {
        result = new HashMap<String, Object>();
    }

    public AggregateResult(Map<String, Object> result) {
        this.result = result;
    }

    public Object get(String key) {
        return result.get(key);
    }

    public String getString(String key) {
        Object value = get(key);

        return TypeUtils.castToString(value);
    }

    public Byte getByte(String key) {
        Object value = get(key);

        if (value == null) {
            return 0;
        }

        return TypeUtils.castToByte(value);
    }

    public byte getByteValue(String key) {
        Object value = get(key);

        if (value == null) {
            return 0;
        }

        return TypeUtils.castToByte(value).byteValue();
    }

    public Short getShort(String key) {
        Object value = get(key);

        if (value == null) {
            return 0;
        }

        return TypeUtils.castToShort(value);
    }

    public short getShortValue(String key) {
        Object value = get(key);

        if (value == null) {
            return 0;
        }

        return TypeUtils.castToShort(value).shortValue();
    }

    public Integer getInteger(String key) {
        Object value = get(key);

        if (value == null) {
            return 0;
        }

        return TypeUtils.castToInteger(value);
    }

    public int getIntValue(String key) {
        Object value = get(key);

        if (value == null) {
            return 0;
        }

        return TypeUtils.castToInteger(value).intValue();
    }

    public Long getLong(String key) {
        Object value = get(key);

        if (value == null) {
            return 0L;
        }

        return TypeUtils.castToLong(value);
    }

    public long getLongValue(String key) {
        Object value = get(key);

        if (value == null) {
            return 0L;
        }

        return TypeUtils.castToLong(value).longValue();
    }

    public Float getFloat(String key) {
        Object value = get(key);

        if (value == null) {
            return 0.0f;
        }

        return TypeUtils.castToFloat(value);
    }

    public float getFloatValue(String key) {
        Object value = get(key);

        if (value == null) {
            return 0.0f;
        }

        return TypeUtils.castToFloat(value).floatValue();
    }

    public Double getDouble(String key) {
        Object value = get(key);

        if (value == null) {
            return 0.0;
        }

        return TypeUtils.castToDouble(value);
    }

    public double getDoubleValue(String key) {
        Object value = get(key);

        if (value == null) {
            return 0.0;
        }

        return TypeUtils.castToDouble(value).doubleValue();
    }

    public BigInteger getBigInteger(String key) {
        Object value = get(key);

        if (value == null) {
            return BigInteger.ZERO;
        }

        return TypeUtils.castToBigInteger(value);
    }

    public BigDecimal getBigDecimal(String key) {
        Object value = get(key);

        if (value == null) {
            return BigDecimal.ZERO;
        }

        return TypeUtils.castToBigDecimal(value);
    }
}
