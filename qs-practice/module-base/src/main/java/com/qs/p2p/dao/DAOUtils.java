package com.qs.p2p.dao;

import java.util.HashSet;
import java.util.Set;

public final class DAOUtils {
    private static final String COLUMN_REGEX = "^\\*$|^\\w+$";
    private static final Set<String> FUNCTION_SET = new HashSet<String>();

    static {
        FUNCTION_SET.add("count");
        FUNCTION_SET.add("sum");
        FUNCTION_SET.add("max");
        FUNCTION_SET.add("min");
        FUNCTION_SET.add("avg");
    }

    public static String toColumnName(String propertyName) {
        StringBuilder sb = new StringBuilder();
        for (char c : propertyName.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append("_").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String[] toColumnNames(String[] propertyNames) {
        String[] columnNames = new String[propertyNames.length];
        for (int i = 0; i < propertyNames.length; ++i) {
            columnNames[i] = toColumnName(propertyNames[i]);
        }
        return columnNames;
    }

    public static void checkColumn(String column) {
        if (!column.matches(COLUMN_REGEX)) {
            throw new IllegalArgumentException("Illegal column: " + column);
        }
    }

    public static void checkFunction(String function) {
        if (!FUNCTION_SET.contains(function)) {
            throw new IllegalArgumentException("Illegal function: " + function);
        }
    }

    public static String buildAggregateSql(String[] functions, String[] properties) {
        String[] columns = toColumnNames(properties);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < functions.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            String function = functions[i].toLowerCase();
            String column = columns[i].toLowerCase();
            checkFunction(function);
            checkColumn(column);
            sb.append(function.toUpperCase()).append("(").append(column).append(")").append(" AS ");
            sb.append(function).append("_");
            if (!"*".equals(column)) {
                sb.append(properties[i]);
            }
        }
        return sb.toString();
    }

    private DAOUtils() {
    }
}
