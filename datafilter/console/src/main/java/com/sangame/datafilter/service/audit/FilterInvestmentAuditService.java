package com.sangame.datafilter.service.audit;

import com.sangame.datafilter.common.persistence.model.audit.FilterAuditLog;
import com.sangame.datafilter.common.persistence.model.audit.FilterInvestmentAudit;
import com.sangame.datafilter.util.Render;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterInvestmentAuditService extends BaseAuditService{

    private static final Logger LOG = LoggerFactory.getLogger(FilterInvestmentAuditService.class);

    public Render searchData(FilterInvestmentAudit filterInvestmentAudit){
        Render render = super.searchData(filterInvestmentAuditMapper,filterInvestmentAudit);
        return render;
    }

    public void updateStatus(int status, Long id, FilterAuditLog auditLog) {
        String result = super.updateStatus(filterInvestmentAuditMapper,status,id,auditLog);
        investmentResponseProducer.sendMessage(result,true);
    }

    public void batchUpdateStatus(int status, String ids, FilterAuditLog auditLog) throws CloneNotSupportedException{
        String result = super.batchUpdateStatus(filterInvestmentAuditMapper,status,ids,auditLog);
        investmentResponseProducer.sendMessage(result,true);
    }

    public Render dealHistoryData(boolean dealDataFlag, String userIds,String ids){

        Render render = new Render(true,"冻结成功");
        String result = super.dealHistoryData(filterInvestmentAuditMapper,dealDataFlag,userIds,ids);
        if(result != null){
            investmentResponseProducer.sendMessage(result,true);
        }
        return render;
    }

    public List<FilterInvestmentAudit> getDataByUserId(int userId){
        List<FilterInvestmentAudit> result = super.getDataByUserId(filterInvestmentAuditMapper,userId);
        return result;
    }
    public int getDatasCountByStatus(int status){
        int count = super.getDatasCountByStatus(filterInvestmentAuditMapper,status);
        return count;
    }
}
