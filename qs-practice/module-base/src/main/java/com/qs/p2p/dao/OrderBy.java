package com.qs.p2p.dao;

public class OrderBy {
    private StringBuilder sb = new StringBuilder();

    public OrderBy add(String property) {
        return add(property, true);
    }

    public OrderBy add(String property, boolean ascend) {
        String column = DAOUtils.toColumnName(property);

        DAOUtils.checkColumn(column);

        if (sb.length() > 0) {
            sb.append(", ");
        }
        sb.append(column).append(ascend ? " ASC" : " DESC");

        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
