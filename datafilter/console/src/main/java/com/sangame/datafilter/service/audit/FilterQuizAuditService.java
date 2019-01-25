package com.sangame.datafilter.service.audit;

import com.sangame.datafilter.common.persistence.model.audit.FilterAuditLog;
import com.sangame.datafilter.common.persistence.model.audit.FilterQuizAudit;
import com.sangame.datafilter.util.Render;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterQuizAuditService extends BaseAuditService{

    private static final Logger LOG = LoggerFactory.getLogger(FilterQuizAuditService.class);



    public Render searchData(FilterQuizAudit filterQuizAudit){
        Render render = super.searchData(filterQuizAuditMapper,filterQuizAudit);
        return render;
    }

    public void updateStatus(int status, Long id, FilterAuditLog auditLog) {
        String result = super.updateStatus(filterQuizAuditMapper,status,id,auditLog);
        quizResponseProducer.sendMessage(result,true);
    }

    public void batchUpdateStatus(int status, String ids, FilterAuditLog auditLog) throws CloneNotSupportedException{
        String result = super.batchUpdateStatus(filterQuizAuditMapper,status,ids,auditLog);
        quizResponseProducer.sendMessage(result,true);
    }

    public Render dealHistoryData(boolean dealDataFlag, String userIds,String ids){
        Render render = new Render(true,"冻结成功");
        String result = super.dealHistoryData(filterQuizAuditMapper,dealDataFlag,userIds,ids);
        if(result != null){
            quizResponseProducer.sendMessage(result,true);
        }
        return render;
    }

    public List<FilterQuizAudit> getDataByUserId(int userId){
        List<FilterQuizAudit> result = super.getDataByUserId(filterQuizAuditMapper,userId);
        return result;
    }

    public int getDatasCountByStatus(int status){
        int count = super.getDatasCountByStatus(filterQuizAuditMapper,status);
        return count;
    }
}
