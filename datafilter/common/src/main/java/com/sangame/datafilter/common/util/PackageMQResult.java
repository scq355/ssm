package com.sangame.datafilter.common.util;

import com.alibaba.fastjson.JSONObject;
import com.sangame.datafilter.common.persistence.model.audit.BaseAuditModel;
import com.sangame.datafilter.common.persistence.model.audit.MQResult;

import java.util.List;

/**
 * @author Mao Renwei
 * @Description 组装返回的数据
 * @date 2017/5/4.
 */
public class PackageMQResult {

    public static String packageWithCommentAudit(BaseAuditModel baseAuditModel){
        MQResult result = new MQResult();
        result.setIds(baseAuditModel.getId());
        result.setStatus(baseAuditModel.getStatus());
        return JSONObject.toJSONString(result);
    }

    public static String packageWithIdAndStatus(Long id, int status){
        MQResult result = new MQResult();
        result.setIds(id);
        result.setStatus(status);
        return JSONObject.toJSONString(result);
    }

    public static String packageWithIdsAndStatus(List<Long> id, int status){
        MQResult result = new MQResult();
        result.setIds(id);
        result.setStatus(status);
        return JSONObject.toJSONString(result);
    }
}
