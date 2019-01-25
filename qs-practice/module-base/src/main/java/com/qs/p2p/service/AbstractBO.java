package com.qs.p2p.service;

import com.qs.p2p.dao.DAO;
import com.qs.p2p.dao.OrderBy;
import com.qs.p2p.dao.AggregateResult;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractBO<T, PK extends Serializable> implements BO<T, PK> {
    protected abstract DAO<T, PK> getDAO();

    @Override
    public void insert(T entity) {
        getDAO().insert(entity);
    }

    @Override
    public T get(T condition) {
        return getDAO().get(condition);
    }

    @Override
    public T get(PK id) {
        return getDAO().get(id);
    }

    @Override
    public List<T> find() {
        return getDAO().find();
    }

    @Override
    public List<T> find(OrderBy orderBy) {
        return getDAO().find(orderBy);
    }

    @Override
    public List<T> find(int pageSize, int pageNumber) {
        return getDAO().find(pageSize, pageNumber);
    }

    @Override
    public List<T> find(OrderBy orderBy, int pageSize, int pageNumber) {
        return getDAO().find(orderBy, pageSize, pageNumber);
    }

    @Override
    public List<T> find(T condition) {
        return getDAO().find(condition);
    }

    @Override
    public List<T> find(T condition, OrderBy orderBy) {
        return getDAO().find(condition, orderBy);
    }

    @Override
    public List<T> find(T condition, int pageSize, int pageNumber) {
        return getDAO().find(condition, pageSize, pageNumber);
    }

    @Override
    public List<T> find(T condition, OrderBy orderBy, int pageSize, int pageNumber) {
        return getDAO().find(condition, orderBy, pageSize, pageNumber);
    }

    @Override
    public int count() {
        return getDAO().count();
    }

    @Override
    public int count(String property) {
        return getDAO().count(property);
    }

    @Override
    public int count(T condition) {
        return getDAO().count(condition);
    }

    @Override
    public int count(T condition, String property) {
        return getDAO().count(condition, property);
    }

    @Override
    public AggregateResult aggregate(String[] functions, String[] properties) {
        return getDAO().aggregate(functions, properties);
    }

    @Override
    public AggregateResult aggregate(T condition, String[] functions, String[] properties) {
        return getDAO().aggregate(condition, functions, properties);
    }

    @Override
    public int update(T entity, T condition) {
        return getDAO().update(entity, condition);
    }

    @Override
    public int update(T entity, PK id) {
        return getDAO().update(entity, id);
    }

    @Override
    public int remove(T condition) {
        return getDAO().remove(condition);
    }

    @Override
    public int remove(PK id) {
        return getDAO().remove(id);
    }
}
