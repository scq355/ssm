package com.sangame.datafilter.common.persistence.mapper.audit;

import com.sangame.datafilter.common.persistence.model.audit.FilterAuditLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public interface FilterAuditLogMapper {

    int getTodayPassManualCount();

    int getTodayPassSysCount();

    public void insert(FilterAuditLog auditLog);

    public void batchInsert(@Param("auditLogs") List<FilterAuditLog> auditLogs);

}
