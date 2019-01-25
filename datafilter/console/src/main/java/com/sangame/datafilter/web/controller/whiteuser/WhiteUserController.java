package com.sangame.datafilter.web.controller.whiteuser;

import com.alibaba.fastjson.JSON;
import com.sangame.datafilter.common.persistence.model.FilterWhiteUser;
import com.sangame.datafilter.common.util.PageUtil;
import com.sangame.datafilter.constant.PageConstant;
import com.sangame.datafilter.service.FilterWhiteUserService;
import com.sangame.datafilter.util.Render;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc 白名单
 * @date 2017年4月28日
 */
@Controller
@RequestMapping("/whiteuser")
public class WhiteUserController {
    private static final Logger log = LoggerFactory.getLogger(WhiteUserController.class);

    @Autowired
    private FilterWhiteUserService whiteUserService;

    /**
     * 进入index页
     *
     * @return
     */
    @RequestMapping(value = "/index.do", method = RequestMethod.GET)
    public String index() {
        return PageConstant.WHITEUSER_INDEX_PAGE;
    }

    /**
     * @param userIds
     * @return
     * @desc 删除白名单用户userId(单条/批量)
     */
    @RequestMapping(value = "/batch/userId/del.do", method = RequestMethod.POST)
    @ResponseBody
    public Render batchDeleteUserByUserId(@RequestParam(value = "userIds") List<Long> userIds) {
        try {
            if (!(userIds.isEmpty())) {
                whiteUserService.batchDeleteUserByUserId(userIds);
            }
            return new Render(true, "删除白名单用户成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Render(false, "删除白名单用户失败！");
        }
    }

    /**
     * @param ips
     * @return
     * @desc 删除白名单用户ip(单条/批量)
     */
    @RequestMapping(value = "/batch/ip/del.do", method = RequestMethod.POST)
    @ResponseBody
    public Render batchDeleteUserByIP(@RequestParam(value = "ips") List<String> ips) {
        try {
            if (!(ips.isEmpty())) {
                whiteUserService.batchDeleteUserByIP(ips);
            }
            return new Render(true, "删除白名单用户ip成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Render(false, "删除白名单用户ip失败！");
        }
    }

    /**
     * 根据id批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batch/del.do", method = RequestMethod.POST)
    @ResponseBody
    public Render batchDeleteUserById(@RequestParam(value = "ids") List<Long> ids) {
        try {
            if (!(ids.isEmpty())) {
                whiteUserService.batchDeleteUserById(ids);
            }
            return new Render(true, "删除白名单用户id成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Render(false, "删除白名单用户ip失败！");
        }
    }

    /**
     * @return
     * @desc 添加白名单用户/ip跳转页
     */
    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    public String addUser() {
        return PageConstant.WHITEUSER_ADD;
    }


    /**
     * 批量新增
     *
     * @param users
     * @return
     */
    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public Render batchInsertUser(@RequestParam(value = "users") String users) {
        try {
            List<FilterWhiteUser> filterWhiteUsers = JSON.parseArray(users, FilterWhiteUser.class);
            whiteUserService.batchInsertUser(filterWhiteUsers);       //批量新增
            return new Render(true, "批量新增成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Render(false, "批量新增失败！");
        }
    }


    /**
     * 获取白名单列表(用户/IP)
     *
     * @param whiteType
     * @param userId
     * @param userName
     * @param pageStart
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public Render listIP(
            @RequestParam(value = "whiteType", required = false) Integer whiteType,
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "userNameLike", required = false) String userNameLike,
            @RequestParam(value = "ip", required = false) String ip,
            @RequestParam(value = "pageStart", required = false, defaultValue = "1") Integer pageStart,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        Map<String, Object> parm = new HashMap<String, Object>();
        if (whiteType != null && whiteType > 0) {
            parm.put("whiteType", whiteType);
        }
        if (StringUtils.isNotBlank(userId)) {
            parm.put("userId", userId);
        }
        if (StringUtils.isNotBlank(userNameLike)) {
            parm.put("userNameLike", userNameLike);
        }
        if (StringUtils.isNotBlank(ip)) {
            parm.put("ip", ip);
        }
        PageUtil page = whiteUserService.getListByParm(parm, pageStart, pageSize);
        return new Render(true, "获取列表数据成功！", page.getTotalRecords(), page.getList());
    }

}
