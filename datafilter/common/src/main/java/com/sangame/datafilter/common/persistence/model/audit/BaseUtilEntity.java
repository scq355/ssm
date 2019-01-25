package com.sangame.datafilter.common.persistence.model.audit;

import com.sangame.datafilter.common.persistence.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mao Renwei
 * @Description
 * @date 2017/4/25.
 */
public abstract class BaseUtilEntity extends BaseModel implements Serializable{
    private static final long serialVersionUID = -1926581246531376129L;

    // 入参,查询时由控制层或业务层传入数据访问层 ----------------------------------
    int pageSize = 10;			// 分页页面大小
    int pageStart = 0;			// 分页起始页
    Date queryBeginTime;		// 范围查询开始时间
    Date queryEndTime;			// 范围查询结束时间
    String orderBy;				// 排序字段
    String queryKeyword;		// 查询关键字
    private boolean hasImage;   //是否拥有图片
    private int keyWordType;    // 关键字类型

    public boolean getHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public int getKeyWordType() {
        return keyWordType;
    }

    public void setKeyWordType(int keyWordType) {
        this.keyWordType = keyWordType;
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每个分页查询的条数，该值不能小于1，否则可能会导致sql语法错误，如：limit 0,-30
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        if(pageSize < 1){
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }
    }

    public Date getQueryBeginTime() {
        return queryBeginTime;
    }

    public void setQueryBeginTime(Date queryBeginTime) {
        this.queryBeginTime = queryBeginTime;
    }

    public Date getQueryEndTime() {
        return queryEndTime;
    }

    public void setQueryEndTime(Date queryEndTime) {
        this.queryEndTime = queryEndTime;
    }

    public int getPageStart() {
        return pageStart;
    }

    /**
     * 设置分页查询的起始条数，该值不能为负数，否则可能会导致sql语法错误，如：limit -1,30
     * @param pageStart
     */
    public void setPageStart(int pageStart) {
        if(pageStart < 0){
            throw new IllegalArgumentException("pageStart不能为负数");
        }
        this.pageStart = pageStart;
    }

    public String getQueryKeyword() {
        return queryKeyword;
    }

    public void setQueryKeyword(String queryKeyword) {
        this.queryKeyword = queryKeyword;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
