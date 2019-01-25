package com.qs.p2p.dao;

import java.io.Serializable;
import java.util.*;

public class AbstractPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<ExpressionChain> expressionChainList;
    private Set<String> forceUpdateProperties;
    private Map<String, Object> incrementUpdateMap;

    public AbstractPO() {
        expressionChainList = new ArrayList<ExpressionChain>();
        forceUpdateProperties = new HashSet<String>();
        incrementUpdateMap = new LinkedHashMap<String, Object>();
    }

    public void or(ExpressionChain expressionChain) {
        expressionChainList.add(expressionChain);
    }

    public <T> void or(Expression<T> expression) {
        expressionChainList.add(new ExpressionChain().and(expression));
    }

    public <T> void and(Expression<T> expression) {
        if (expressionChainList.isEmpty()) {
            expressionChainList.add(new ExpressionChain());
        }
        expressionChainList.get(0).and(expression);
    }

    protected void addForceUpdateProperty(String property) {
        if (!forceUpdateProperties.contains(property)) {
            forceUpdateProperties.add(property);
        }
    }

    public void increment(String property, Object value) {
        String column = DAOUtils.toColumnName(property);

        if (!incrementUpdateMap.containsKey(column)) {
            incrementUpdateMap.put(column, value);
        }
    }

    public List<ExpressionChain> getExpressionChainList() {
        return expressionChainList;
    }

    public void setExpressionChainList(List<ExpressionChain> expressionChainList) {
        this.expressionChainList = expressionChainList;
    }

    public Set<String> getForceUpdateProperties() {
        return forceUpdateProperties;
    }

    public void setForceUpdateProperties(Set<String> forceUpdateProperties) {
        this.forceUpdateProperties = forceUpdateProperties;
    }

    public Map<String, Object> getIncrementUpdateMap() {
        return incrementUpdateMap;
    }

    public void setIncrementUpdateMap(Map<String, Object> incrementUpdateMap) {
        this.incrementUpdateMap = incrementUpdateMap;
    }
}
