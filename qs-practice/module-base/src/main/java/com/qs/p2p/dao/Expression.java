package com.qs.p2p.dao;

import java.util.List;

public class Expression<T> {
    private int type;
    private String column;
    private String operator;
    private T value;
    private T value1;
    private List<T> values;

    public Expression(int type, String column, String operator) {
        DAOUtils.checkColumn(column);

        this.type = type;
        this.column = column;
        this.operator = operator;
    }

    public Expression(int type, String column, String operator, T value) {
        DAOUtils.checkColumn(column);

        this.type = type;
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    public Expression(int type, String column, String operator, T value, T value1) {
        DAOUtils.checkColumn(column);

        this.type = type;
        this.column = column;
        this.operator = operator;
        this.value = value;
        this.value1 = value1;
    }

    public Expression(int type, String column, String operator, List<T> values) {
        DAOUtils.checkColumn(column);

        this.type = type;
        this.column = column;
        this.operator = operator;
        this.values = values;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        DAOUtils.checkColumn(column);

        this.column = column;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue1() {
        return value1;
    }

    public void setValue1(T value1) {
        this.value1 = value1;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}
