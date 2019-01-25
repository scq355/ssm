package com.sangame.datafilter.service.audit;

import com.sangame.datafilter.common.persistence.model.audit.FilterAuditLog;
import com.sangame.datafilter.common.persistence.model.audit.FilterCommentAudit;
import com.sangame.datafilter.util.Render;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterCommentAuditService extends BaseAuditService{

    private static final Logger LOG = LoggerFactory.getLogger(FilterCommentAuditService.class);

	public void insert(FilterCommentAudit filterCommentAudit){
	    super.insert(filterCommentAuditMapper,filterCommentAudit);
    }

    public Render searchData(FilterCommentAudit filterCommentAudit){
        Render render = super.searchData(filterCommentAuditMapper,filterCommentAudit);
        return render;
    }

    public void updateStatus(int status, Long id, FilterAuditLog auditLog) {
        String result = super.updateStatus(filterCommentAuditMapper,status,id,auditLog);
        commentResponseProducer.sendMessage(result,true);
    }

    public void batchUpdateStatus(int status, String ids, FilterAuditLog auditLog) throws CloneNotSupportedException{
        String result = super.batchUpdateStatus(filterCommentAuditMapper,status,ids,auditLog);
        commentResponseProducer.sendMessage(result,true);
    }

    public Render dealHistoryData(boolean dealDataFlag, String userIds,String ids){
        Render render = new Render(true,"冻结成功");
        String result = super.dealHistoryData(filterCommentAuditMapper,dealDataFlag,userIds,ids);
        if(result != null){
            commentResponseProducer.sendMessage(result,true);
        }
        return render;
    }

    public List<FilterCommentAudit> getDataByUserId(int userId){
        List<FilterCommentAudit> result = super.getDataByUserId(filterCommentAuditMapper,userId);
        return result;
    }

    public int getDatasCountByStatus(int status){
        int count = super.getDatasCountByStatus(filterCommentAuditMapper,status);
        return count;
    }
}
