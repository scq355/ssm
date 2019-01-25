package com.qs.p2p.dao;

import java.util.Arrays;
import java.util.List;

public final class Expressions {
    public static <T> Expression<T> isNull(String property) {
        return new Expression<T>(0, DAOUtils.toColumnName(property), "IS NULL");
    }

    public static <T> Expression<T> isNotNull(String property) {
        return new Expression<T>(0, DAOUtils.toColumnName(property), "IS NOT NULL");
    }

    public static <T> Expression<T> eq(String property, T value) {
        return new Expression<T>(1, DAOUtils.toColumnName(property), "=", value);
    }

    public static <T> Expression<T> ne(String property, T value) {
        return new Expression<T>(1, DAOUtils.toColumnName(property), "<>", value);
    }

    public static <T> Expression<T> lt(String property, T value) {
        return new Expression<T>(1, DAOUtils.toColumnName(property), "<", value);
    }

    public static <T> Expression<T> le(String property, T value) {
        return new Expression<T>(1, DAOUtils.toColumnName(property), "<=", value);
    }

    public static <T> Expression<T> gt(String property, T value) {
        return new Expression<T>(1, DAOUtils.toColumnName(property), ">", value);
    }

    public static <T> Expression<T> ge(String property, T value) {
        return new Expression<T>(1, DAOUtils.toColumnName(property), ">=", value);
    }

    public static <T> Expression<T> like(String property, T value) {
        return new Expression<T>(1, DAOUtils.toColumnName(property), "LIKE", value);
    }

    public static <T> Expression<T> notLike(String property, T value) {
        return new Expression<T>(1, DAOUtils.toColumnName(property), "NOT LIKE", value);
    }

    public static <T> Expression<T> between(String property, T value, T value1) {
        return new Expression<T>(2, DAOUtils.toColumnName(property), "BETWEEN", value, value1);
    }

    public static <T> Expression<T> notBetween(String property, T value, T value1) {
        return new Expression<T>(2, DAOUtils.toColumnName(property), "NOT BETWEEN", value, value1);
    }

    @SafeVarargs
    public static <T> Expression<T> in(String property, T... values) {
        return new Expression<T>(3, DAOUtils.toColumnName(property), "IN", Arrays.asList(values));
    }

    public static <T> Expression<T> in(String property, List<T> values) {
        return new Expression<T>(3, DAOUtils.toColumnName(property), "IN", values);
    }

    @SafeVarargs
    public static <T> Expression<T> notIn(String property, T... values) {
        return new Expression<T>(3, DAOUtils.toColumnName(property), "NOT IN", Arrays.asList(values));
    }

    public static <T> Expression<T> notIn(String property, List<T> values) {
        return new Expression<T>(3, DAOUtils.toColumnName(property), "NOT IN", values);
    }

    private Expressions() {
    }
}
