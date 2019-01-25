package com.sangame.datafilter.service.audit;

import com.sangame.datafilter.common.persistence.mapper.audit.FilterAuditLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  
public class FilterAuditLogService {

    @Autowired
    private FilterAuditLogMapper filterAuditLogMapper;

    /**
     * @author MaoRenwei
     * @description 查找操作日志
     * @date 2017/5/5
     */

//    public FilterAuditLog getLogInfo(Long auditId) {
//
//    }

}
