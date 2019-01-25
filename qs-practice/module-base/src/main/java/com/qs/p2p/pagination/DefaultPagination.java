package com.qs.p2p.pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultPagination<T> implements Pagination<T> {
    private List<T> elements;
    private int pageSize;
    private int pageNumber;
    private int totalNumberOfElements;
    private Map<String, Object> model;

    public DefaultPagination(int pageSize, int pageNumber, int totalNumberOfElements, List<T> elements) {
        this(pageSize, pageNumber, totalNumberOfElements, elements, new HashMap<String, Object>());
    }

    public DefaultPagination(int pageSize, int pageNumber, int totalNumberOfElements, List<T> elements, Map<String, Object> model) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalNumberOfElements = totalNumberOfElements;
        this.elements = elements;
        this.model = model;
    }

    @Override
    public boolean isFirstPage() {
        return getPageNumber() == getFirstPageNumber();
    }

    @Override
    public boolean isLastPage() {
        return getPageNumber() == getLastPageNumber();
    }

    @Override
    public boolean hasNextPage() {
        return !isLastPage();
    }

    @Override
    public boolean hasPreviousPage() {
        return !isFirstPage();
    }

    @Override
    public int getFirstPageNumber() {
        return 1;
    }

    @Override
    public int getLastPageNumber() {
        if (getTotalNumberOfElements() == 0) {
            return 1;
        }

        return (getPageSize() + getTotalNumberOfElements() - 1) / getPageSize();
    }

    @Override
    public List<T> getThisPageElements() {
        return elements;
    }

    @Override
    public int getTotalNumberOfElements() {
        return totalNumberOfElements;
    }

    @Override
    public int getThisPageFirstElementNumber() {
        return (getPageNumber() - 1) * getPageSize() + 1;
    }

    @Override
    public int getThisPageLastElementNumber() {
        int thisPageLastElementNumber = getThisPageFirstElementNumber() + getPageSize() - 1;
        return getTotalNumberOfElements() < thisPageLastElementNumber ? getTotalNumberOfElements() : thisPageLastElementNumber;
    }

    @Override
    public int getNextPageNumber() {
        return getPageNumber() + 1;
    }

    @Override
    public int getPreviousPageNumber() {
        return getPageNumber() - 1;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public Map<String, Object> getModel() {
        return model;
    }
}
