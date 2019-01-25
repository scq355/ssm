package com.sangame.datafilter.service.audit;

import com.sangame.datafilter.common.persistence.model.audit.FilterAuditLog;
import com.sangame.datafilter.common.persistence.model.audit.FilterMarketAudit;
import com.sangame.datafilter.util.Render;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterMarketAuditService extends BaseAuditService{

    private static final Logger LOG = LoggerFactory.getLogger(FilterMarketAuditService.class);



    public Render searchData(FilterMarketAudit filterMarketAudit){
        Render render = super.searchData(filterMarketAuditMapper,filterMarketAudit);
        return render;
    }

    public void updateStatus(int status, Long id, FilterAuditLog auditLog) {
        String result = super.updateStatus(filterMarketAuditMapper,status,id,auditLog);
        marketResponseProducer.sendMessage(result,true);
    }

    public void batchUpdateStatus(int status, String ids, FilterAuditLog auditLog) throws CloneNotSupportedException{
        String result = super.batchUpdateStatus(filterMarketAuditMapper,status,ids,auditLog);
        marketResponseProducer.sendMessage(result,true);
    }

    public Render dealHistoryData(boolean dealDataFlag, String userIds,String ids){
        Render render = new Render(true,"冻结成功");
        String result = super.dealHistoryData(filterMarketAuditMapper,dealDataFlag,userIds,ids);
        if(result != null){
            marketResponseProducer.sendMessage(result,true);
        }
        return render;
    }

    public List<FilterMarketAudit> getDataByUserId(int userId){
        List<FilterMarketAudit> result = super.getDataByUserId(filterMarketAuditMapper,userId);
        return result;
    }

    public int getDatasCountByStatus(int status){
        int count = super.getDatasCountByStatus(filterMarketAuditMapper,status);
        return count;
    }
}
