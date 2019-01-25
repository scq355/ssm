package com.sangame.datafilter.util;

import com.sangame.datafilter.common.persistence.model.FilterBlackUser;
import com.sangame.datafilter.common.persistence.model.FilterWhiteUser;
import com.sangame.datafilter.common.persistence.model.audit.BaseAuditModel;

/**
 * @author Mao Renwei
 * @Description 审核辅助方法
 * @date 2017/5/8.
 */
public class AuditUtil {

    public static FilterBlackUser getBlackUserFromAudit(BaseAuditModel auditModel){

        if(auditModel == null){
            return null;
        }
        FilterBlackUser blackUser = new FilterBlackUser();
        blackUser.setUserId(auditModel.getUserId());
        blackUser.setUserName(auditModel.getUserName());
        blackUser.setIp(auditModel.getIp());

        return blackUser;
    }

    public static FilterWhiteUser getWhiteUserFromAudit(BaseAuditModel auditModel){
        if(auditModel == null){
            return null;
        }
        FilterWhiteUser whiteUser = new FilterWhiteUser();
        whiteUser.setIp(auditModel.getIp());
        whiteUser.setUserId(auditModel.getUserId());

        return  whiteUser;
    }
}
