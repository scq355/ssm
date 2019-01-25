package com.sangame.datafilter.web.controller.audit;

import com.sangame.datafilter.common.constant.CommonConstant;
import com.sangame.datafilter.common.persistence.model.audit.FilterAuditLog;
import com.sangame.datafilter.common.persistence.model.audit.FilterJiepanAudit;
import com.sangame.datafilter.constant.ConsoleConstant;
import com.sangame.datafilter.constant.PageConstant;
import com.sangame.datafilter.service.FilterBlackUserService;
import com.sangame.datafilter.service.audit.FilterJiepanAuditService;
import com.sangame.datafilter.util.Render;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/jiepan")
public class JiepanAuditController {
    @Autowired
    FilterJiepanAuditService filterJiepanAuditService;
    @Autowired
    FilterBlackUserService filterBlackUserService;

    @RequestMapping(value="/index.do", method= RequestMethod.GET)
    public String index(Model model){
        int check = filterJiepanAuditService.getDatasCountByStatus(CommonConstant.DataStatus.CHECK.getValue());
        int sysPass = filterJiepanAuditService.getDatasCountByStatus(CommonConstant.DataStatus.SYS_PASS.getValue());
        int manualPass = filterJiepanAuditService.getDatasCountByStatus(CommonConstant.DataStatus.MANUAL_PASS.getValue());
        int reject = filterJiepanAuditService.getDatasCountByStatus(CommonConstant.DataStatus.REJECT.getValue());
        model.addAttribute("checkCount",check);
        model.addAttribute("passCount",sysPass+manualPass);
        model.addAttribute("rejectCount",reject);
        return PageConstant.JIEPAN_INDEX_PAGE;
    }

    @RequestMapping(value = "/look")
    public String searchUsers(Model model,Integer userId){
        model.addAttribute("userId",userId);
        model.addAttribute("domain", ConsoleConstant.ProjectType.COMMENT.getKeyWord());
        return PageConstant.COMMON_LOOK_PAGE;
    }

    /**
     * @author MaoRenwei
     * @description 通用的查找方法。
     * @date 2017/5/5
     */
    @ResponseBody
    @RequestMapping(value = "/list.do")
    public Render index(FilterJiepanAudit filterJiepanAudit){
        Render render = filterJiepanAuditService.searchData(filterJiepanAudit);
        return render;
    }

    @ResponseBody
    @RequestMapping(value = "/updateStatus.do")
    public Render updateStatus(Integer status,Long auditId,FilterAuditLog filterAuditLog) throws CloneNotSupportedException{
        //comment 需要 上传 id ，status, log 需要上传，reason,操作
        if(status == null || auditId == null || filterAuditLog == null){
            return new Render(false,"参数不能为空");
        }
        filterJiepanAuditService.updateStatus(status,auditId,filterAuditLog);
        return new Render(true,"更新成功");
    }

    @ResponseBody
    @RequestMapping(value = "/batchUpdateStatus.do")
    public Render batchUpdateStatus(Integer status,String ids, FilterAuditLog filterAuditLog) throws CloneNotSupportedException{
        //comment 需要 上传 id ，status, log 需要上传，reason,操作
        if(status == null || StringUtils.isEmpty(ids) || filterAuditLog == null){
            return new Render(false,"参数不能为空");
        }
        filterJiepanAuditService.batchUpdateStatus(status,ids,filterAuditLog);
        return new Render(true,"批量更新成功");
    }

    @ResponseBody
    @RequestMapping(value = "/batchFreezeId.do")
    public Render batchFreezeId(String users,String userIds,String ids,Boolean dealDataFlag){

        if(StringUtils.isEmpty(users) || StringUtils.isEmpty(userIds) || StringUtils.isEmpty(ids)){
            return new Render(false,"参数不能为空");
        }
        filterBlackUserService.batchFreezeBlackUserByUserId(users);
        filterJiepanAuditService.dealHistoryData(dealDataFlag,userIds,ids);
        return new Render(true,"冻结成功");
    }

    @ResponseBody
    @RequestMapping(value = "/batchFreezeIp.do")
    public Render batchFreezeIp(String users,String userIds,String ids,Boolean dealDataFlag) {

        if(StringUtils.isEmpty(users) || StringUtils.isEmpty(userIds) || StringUtils.isEmpty(ids)){
            return new Render(false,"参数不能为空");
        }
        filterBlackUserService.batchFreezeBlackUserByIP(users);
        filterJiepanAuditService.dealHistoryData(dealDataFlag,userIds,ids);
        return new Render(true,"冻结成功");
    }
}
