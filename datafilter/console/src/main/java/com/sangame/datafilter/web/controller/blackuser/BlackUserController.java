package com.sangame.datafilter.web.controller.blackuser;

import com.sangame.datafilter.common.persistence.model.FilterBlackUser;
import com.sangame.datafilter.common.util.DateUtil;
import com.sangame.datafilter.common.util.PageUtil;
import com.sangame.datafilter.constant.ConsoleConstant;
import com.sangame.datafilter.constant.ConsoleConstant.BlackStatus;
import com.sangame.datafilter.constant.ConsoleConstant.BlackWhiteWay;
import com.sangame.datafilter.constant.PageConstant;
import com.sangame.datafilter.service.FilterBlackUserService;
import com.sangame.datafilter.util.Render;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc 黑名单控制器
 * @author scq
 */
@Controller
@RequestMapping("/blackuser")
public class BlackUserController {

    private static final Logger log = LoggerFactory.getLogger(BlackUserController.class);

    @Autowired
    private FilterBlackUserService blackUserService;

    /**
     * 进入index页
     * @return
     */
    @RequestMapping(value="/index.do", method=RequestMethod.GET)
    public String index() {
        return PageConstant.BLACKUSER_INDEX_PAGE;
    }

    /**
     * 黑名单用户批量解冻id
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batch/id/unfreeze.do", method = RequestMethod.POST)
    @ResponseBody
    public Render batchUnFreezeById(@RequestParam(value = "ids") List<Long> ids) {
        try {
            blackUserService.batchFreezeById(ids);
            return new Render(true, "黑名单用户批量解冻id成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Render(false, "黑名单用户批量解冻id失败！");
        }
    }

    /**
     * 黑名单修改跳转页(userId/IP)
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit.do", method = RequestMethod.GET)
    public String editBlackUser(
            @RequestParam(value = "id") Long id, Model model) {
        try {
            FilterBlackUser blackUser = blackUserService.getById(id);
            Integer blackWay = blackUser.getBlackWay();
            model.addAttribute("blackUser", blackUser);
            if (blackWay == BlackWhiteWay.USERID.getValue()) {
                return PageConstant.BLACKUSER_EDIT_USERID_PAGE;
            } else if (blackWay == BlackWhiteWay.IP.getValue()) {
                return PageConstant.BLACKUSER_EDIT_IP_PAGE;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return PageConstant.ERROR_PAGE;
    }

    /**
     * 黑名单用户修改(userId/IP)
     * @param id
     * @param userId
     * @param userName
     * @param ip
     * @param timeLimit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public Render updateBlackUser(@RequestParam(value = "id") Long id,
                                  @RequestParam(value = "userId", required = false) Long userId,
                                  @RequestParam(value = "userName", required = false) String userName,
                                  @RequestParam(value = "ip", required = false) String ip,
                                  @RequestParam(value = "timeLimit", required = true) Integer timeLimit) {
        try {
            FilterBlackUser blackUser = blackUserService.getById(id);
            if (StringUtils.isNotBlank(ip)) {
                blackUser.setIp(ip);
            }
            if (userId != null && userId > 0L) {
                blackUser.setUserId(userId);
            }
            if (StringUtils.isNotBlank(userName)) {
                blackUser.setUserName(userName);
            }
            //timeLimit为-1时，表示永久冻结，需要特殊处理，比如设置冻结日期为2099年
            Date newEndTime;
            if (timeLimit != -1) {
                newEndTime = DateUtil.addDay(new Date() , timeLimit);
            } else {
                newEndTime = DateUtil.parse(ConsoleConstant.FOREVER);
            }
            blackUser.setBlackEndTime(newEndTime);
            blackUser.setModifier("");      //修改人设置为空，拦截器进行自动处理
            blackUserService.update(blackUser);
            return new Render(true, "黑名单用户修改(userId/IP)成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Render(false, "黑名单用户修改(userId/IP)失败！");
        }
    }


    /**
     * 获取黑名单列表页
     * @param blackWay
     * @param userId
     * @param userName
     * @param ip
     * @param pageStart
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public Render listBlackUsersByUserId(
            @RequestParam(value = "blackWay", required = false, defaultValue = "1") Integer blackWay,
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "userNameLike", required = false) String userNameLike,
            @RequestParam(value = "ip", required = false) String ip,
            @RequestParam(value = "modifier", required = false) String modifier,
            @RequestParam(value = "pageStart", required = false, defaultValue = "1") Integer pageStart,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Map<String, Object> param = new HashMap<String, Object>();
        if (blackWay != null && blackWay > 0) {
            param.put("blackWay", blackWay);
        }
        if (StringUtils.isNotBlank(userId)) {
            param.put("userId", userId);
        }
        if (StringUtils.isNotBlank(userNameLike)) {
            param.put("userNameLike", userNameLike);
        }
        if (StringUtils.isNotBlank(ip)) {
            param.put("ip", ip);
        }
        if (StringUtils.isNotBlank(modifier)) {
            param.put("modifier", modifier);
        }
        param.put("blackState", BlackStatus.BLOCK.getValue());      //黑名单列表，只展示“正在处于冻结状态”的数据
        PageUtil page = blackUserService.getListByParm(param, pageStart, pageSize);
        return new Render(true, "获取列表数据成功！", page.getTotalRecords(), page.getList());
    }
}
