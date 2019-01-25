package com.sangame.datafilter.common.persistence.model.pandect;

import java.io.Serializable;

/**
 * @author Mao Renwei
 * @Description 数据统计（默认是10天）
 * @date 2017/5/2.
 */
public class DataCountStatistic implements Serializable{

    private static final long serialVersionUID = 1L;

    private int projectType;
    private String dateTime;
    private int dataStatus;
    private int count;

    public int getProjectType() {
        return projectType;
    }

    public void setProjectType(int projectType) {
        this.projectType = projectType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
