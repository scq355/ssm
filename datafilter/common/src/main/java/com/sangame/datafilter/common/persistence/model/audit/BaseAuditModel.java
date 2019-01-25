package com.sangame.datafilter.common.persistence.model.audit;

import java.util.List;

/**
 * @author Mao Renwei
 * @Description 审核通用属性的提取
 * @date 2017/5/8.
 */
public class BaseAuditModel extends BaseUtilEntity{

    private String url; // 文章链接
    private String title; // 标题
    private String imgUrls; // 图片
    private String originalData; //原始数据
    private String filterData; // 过滤数据
    private Integer status; // 状态
    private Integer filterType; // 过滤类型 0：无，1：敏感词，2：非法词
    private Long userId; // 用戶ID
    private String userName; // 用户姓名
    private String ip;
    private List<FilterAuditLog> auditLogs; // 日志信息

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImgUrls() {
        return imgUrls;
    }
    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }
    public String getOriginalData() {
        return originalData;
    }
    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }
    public String getFilterData() {
        return filterData;
    }
    public void setFilterData(String filterData) {
        this.filterData = filterData;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getFilterType() {
        return filterType;
    }
    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public List<FilterAuditLog> getAuditLogs() {
        return auditLogs;
    }
    public void setAuditLogs(List<FilterAuditLog> auditLogs) {
        this.auditLogs = auditLogs;
    }
}
