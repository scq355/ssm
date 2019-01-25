package com.qs.p2p.dao;

import org.mybatis.spring.SqlSessionTemplate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractDAO<T, PK extends Serializable> implements DAO<T, PK> {
    protected abstract SqlSessionTemplate getSqlSessionTemplate();

    protected abstract String getNamespace();

    @Override
    public void insert(T entity) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("entity", entity);

        getSqlSessionTemplate().insert(getNamespace() + ".insert", params);
    }

    @Override
    public T get(T condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);

        return getSqlSessionTemplate().<T> selectOne(getNamespace() + ".get", params);
    }

    @Override
    public T get(PK id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        return getSqlSessionTemplate().<T> selectOne(getNamespace() + ".getById", params);
    }

    @Override
    public List<T> find() {
        return find((T) null);
    }

    @Override
    public List<T> find(OrderBy orderBy) {
        return find(null, orderBy, -1, -1);
    }

    @Override
    public List<T> find(int pageSize, int pageNumber) {
        return find(null, null, pageSize, pageNumber);
    }

    @Override
    public List<T> find(OrderBy orderBy, int pageSize, int pageNumber) {
        return find(null, orderBy, pageSize, pageNumber);
    }

    @Override
    public List<T> find(T condition) {
        return find(condition, -1, -1);
    }

    @Override
    public List<T> find(T condition, OrderBy orderBy) {
        return find(condition, orderBy, -1, -1);
    }

    @Override
    public List<T> find(T condition, int pageSize, int pageNumber) {
        return find(condition, null, pageSize, pageNumber);
    }

    @Override
    public List<T> find(T condition, OrderBy orderBy, int pageSize, int pageNumber) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        if (orderBy != null) {
            params.put("orderBy", orderBy.toString());
        }
        if (pageSize > 0 && pageNumber > 0) {
            int startRow = pageSize * (pageNumber - 1) + 1;
            int endRow = pageSize * pageNumber;
            int offset = pageSize * (pageNumber - 1);

            params.put("startRow", Integer.valueOf(startRow));
            params.put("endRow", Integer.valueOf(endRow));
            params.put("offset", Integer.valueOf(offset));
            params.put("limit", pageSize);
        }

        return getSqlSessionTemplate().<T> selectList(getNamespace() + ".find", params);
    }

    @Override
    public int count() {
        return count("*");
    }

    @Override
    public int count(String property) {
        return count(null, DAOUtils.toColumnName(property));
    }

    @Override
    public int count(T condition) {
        return count(condition, "*");
    }

    @Override
    public int count(T condition, String property) {
        String column = DAOUtils.toColumnName(property);

        DAOUtils.checkColumn(column);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        params.put("count_column", column);

        return getSqlSessionTemplate().<Integer> selectOne(getNamespace() + ".count", params);
    }

    @Override
    public AggregateResult aggregate(String[] functions, String[] properties) {
        return aggregate(null, functions, properties);
    }

    @Override
    public AggregateResult aggregate(T condition, String[] functions, String[] properties) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        params.put("aggregate_sql", DAOUtils.buildAggregateSql(functions, properties));

        Map<String, Object> processedResult = null;
        Map<String, Object> result = getSqlSessionTemplate().<Map<String, Object>> selectOne(getNamespace() + ".aggregate", params);
        if (result != null) {
            processedResult = new HashMap<String, Object>(result.size());
            for (Map.Entry<String, Object> entry : result.entrySet()) {
                processedResult.put(entry.getKey(), entry.getValue());
            }
        }

        return processedResult != null ? new AggregateResult(processedResult) : new AggregateResult();
    }

    @Override
    public int update(T entity, T condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("entity", entity);
        params.put("condition", condition);

        return getSqlSessionTemplate().update(getNamespace() + ".update", params);
    }

    @Override
    public int update(T entity, PK id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("entity", entity);
        params.put("id", id);

        return getSqlSessionTemplate().update(getNamespace() + ".updateById", params);
    }

    @Override
    public int remove(T condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);

        return getSqlSessionTemplate().delete(getNamespace() + ".remove", params);
    }

    @Override
    public int remove(PK id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        return getSqlSessionTemplate().delete(getNamespace() + ".removeById", params);
    }
}
