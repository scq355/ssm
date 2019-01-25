package com.qs.p2p.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, PK extends Serializable> {
    void insert(T entity);

    T get(T condition);
    T get(PK id);

    List<T> find();
    List<T> find(OrderBy orderBy);
    List<T> find(int pageSize, int pageNumber);
    List<T> find(OrderBy orderBy, int pageSize, int pageNumber);
    List<T> find(T condition);
    List<T> find(T condition, OrderBy orderBy);
    List<T> find(T condition, int pageSize, int pageNumber);
    List<T> find(T condition, OrderBy orderBy, int pageSize, int pageNumber);

    int count();
    int count(String property);
    int count(T condition);
    int count(T condition, String property);

    AggregateResult aggregate(String[] functions, String[] properties);
    AggregateResult aggregate(T condition, String[] functions, String[] properties);

    int update(T entity, T condition);
    int update(T entity, PK id);

    int remove(T condition);
    int remove(PK id);
}
