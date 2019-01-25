package com.sangame.datafilter.service.audit;

import com.sangame.datafilter.common.persistence.model.audit.FilterAuditLog;
import com.sangame.datafilter.common.persistence.model.audit.FilterJiepanAudit;
import com.sangame.datafilter.util.Render;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterJiepanAuditService extends BaseAuditService{

    private static final Logger LOG = LoggerFactory.getLogger(FilterJiepanAuditService.class);



    public Render searchData(FilterJiepanAudit filterJiepanAudit){
        Render render = super.searchData(filterJiepanAuditMapper,filterJiepanAudit);
        return render;
    }

    public void updateStatus(int status, Long id, FilterAuditLog auditLog) {
        String result = super.updateStatus(filterJiepanAuditMapper,status,id,auditLog);
        jiepanResponseProducer.sendMessage(result,true);
    }

    public void batchUpdateStatus(int status, String ids, FilterAuditLog auditLog) throws CloneNotSupportedException{
        String result = super.batchUpdateStatus(filterJiepanAuditMapper,status,ids,auditLog);
        jiepanResponseProducer.sendMessage(result,true);
    }

    public Render dealHistoryData(boolean dealDataFlag, String userIds,String ids){
        Render render = new Render(true,"冻结成功");
        String result = super.dealHistoryData(filterJiepanAuditMapper,dealDataFlag,userIds,ids);
        if(result != null){
            jiepanResponseProducer.sendMessage(result,true);
        }
        return render;
    }

    public List<FilterJiepanAudit> getDataByUserId(int userId){
        List<FilterJiepanAudit> result = super.getDataByUserId(filterJiepanAuditMapper,userId);
        return result;
    }

    public int getDatasCountByStatus(int status){
        int count = super.getDatasCountByStatus(filterJiepanAuditMapper,status);
        return count;
    }

}
