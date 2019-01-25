package com.sangame.datafilter.common.persistence.model.pandect;

import java.io.Serializable;

/**
 * @author Mao Rrnwei
 * @Description 数据
 * @date 2017/4/26.
 */
public class DataCount implements Serializable{

    private static final long serialVersionUID = 1L;

    private String projectName; // 项目名称
    private int checkCount; // 待审核数
    private int passManualCount; // 人工通过数
    private int passSysCount; // 系统通过数
    private int participationCount; // 参与数
    private int totalCount; // 总共的数据量

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(int checkCount) {
        this.checkCount = checkCount;
    }

    public int getPassManualCount() {
        return passManualCount;
    }

    public void setPassManualCount(int passManualCount) {
        this.passManualCount = passManualCount;
    }

    public int getPassSysCount() {
        return passSysCount;
    }

    public void setPassSysCount(int passSysCount) {
        this.passSysCount = passSysCount;
    }

    public int getParticipationCount() {
        return participationCount;
    }

    public void setParticipationCount(int participationCount) {
        this.participationCount = participationCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
