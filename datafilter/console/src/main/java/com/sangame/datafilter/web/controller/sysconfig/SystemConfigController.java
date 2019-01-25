package com.sangame.datafilter.web.controller.sysconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.sangame.datafilter.common.persistence.model.SysConfig;
import com.sangame.datafilter.common.util.PageUtil;
import com.sangame.datafilter.constant.ConsoleConstant;
import com.sangame.datafilter.constant.PageConstant;
import com.sangame.datafilter.service.SysConfigService;
import com.sangame.datafilter.util.Render;

@Controller
@RequestMapping("/sysconfig")
public class SystemConfigController {

    private static final Logger log = LoggerFactory.getLogger(SystemConfigController.class);

    @Autowired
    private SysConfigService systemConfigService;

    /**
     * 进入index页
     * @return
     */
    @RequestMapping(value="/index.do", method=RequestMethod.GET)
    public String index() {
        return PageConstant.SYSCONFIG_INDEX_PAGE;
    }

    /**
     * @desc 新增系统规则页面跳转
     * @return
     */
    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    public String insertSystemConfig() {
        return PageConstant.SYSCONFIG_ADD_PAGE;
    }

    /**
     * @param configName
     * @param configKey
     * @param configValue
     * @param remark
     * @return
     * @desc 单条屏蔽规则添加
     */
    @RequestMapping(value = "/insert.do", method = RequestMethod.POST)
    @ResponseBody
    public Render addSystemConfig(
    		@RequestParam(value = "configName", required = true) String configName,
            @RequestParam(value = "configKey", required = true) String configKey,
            @RequestParam(value = "configValue", required = true) String configValue,
            @RequestParam(value = "remark", required = false) String remark) {
        try {
        	SysConfig sysConfig = new SysConfig();
            sysConfig.setShowName(configName);
            sysConfig.setConfigKey(configKey);
            sysConfig.setConfigValue(configValue);
            if (StringUtils.isNotBlank(remark))
            	sysConfig.setRemark(remark);
            systemConfigService.insert(sysConfig);
            return new Render(true, "单条屏蔽规则添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Render(false, "单条屏蔽规则添加失败！");
        }
    }

    /**
     * 单挑规则修改页面跳转
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit.do", method = RequestMethod.GET)
    public String editSystemConfig(Model model,
    		@RequestParam(value = "id", required = true) Long id) {
        try {
            SysConfig sysConfig = systemConfigService.getById(id);
            model.addAttribute("systemConfig", sysConfig);
            return PageConstant.SYSCONFIG_EDIT_PAGE;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return PageConstant.ERROR_PAGE;
    }

    /**
     * @param sysConfigId
     * @param configKey
     * @param configValue
     * @param remark
     * @return
     * @desc 单条规则修改
     */
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public Render updateSystemConfig(
    		@RequestParam(value = "id", required = true) String sysConfigId,
    		@RequestParam(value = "configName", required = true) String configName,
    		@RequestParam(value = "configKey", required = true) String configKey,
            @RequestParam(value = "configValue", required = true) String configValue,
            @RequestParam(value = "remark", required = false) String remark) {
        try {
            SysConfig sysConfig = systemConfigService.getById(Long.valueOf(sysConfigId));
            sysConfig.setShowName(configName);
            sysConfig.setConfigKey(configKey);
            sysConfig.setConfigValue(configValue);
            if (StringUtils.isNotBlank(remark)) {
                sysConfig.setRemark(remark);
            }
            sysConfig.setModifier("");//修改人设置为空，拦截器进行自动处理
            systemConfigService.update(sysConfig);
            return new Render(true, "单条规则修改成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Render(false, "单条规则修改失败！");
        }
    }

    /**
     * 批量删除规则
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batch/del.do", method = RequestMethod.POST)
    public Render deleteBatchRules(@RequestParam(value = "ids") List<Integer> ids,
    		HttpServletRequest request) {
        try {
            systemConfigService.deleteBatchRules(ids);
            return new Render(true, "批量删除规则成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Render(false, "批量删除规则失败！");
        }
    }

    /**
     * 系统配置列表显示
     * @param configName
     * @param configKey
     * @param id
     * @param pageStart
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public Render listSystemInfo(
    		@RequestParam(value = "configName", required = false) String configName,
    		@RequestParam(value = "configKeyLike", required = false) String configKeyLike,
    		@RequestParam(value = "pageStart", required = false, defaultValue = "1") Integer pageStart,
    		@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("deleteFlag", ConsoleConstant.deleteFlagType.FALSE.getValue());
        if (StringUtils.isNotBlank(configName)) {
            parm.put("showName", configName);
        }
        if (StringUtils.isNotBlank(configKeyLike)) {
            parm.put("configKeyLike", configKeyLike);
        }
        PageUtil page = systemConfigService.getListByParm(parm, pageStart, pageSize);
        return new Render(true, "获取列表数据成功！", page.getTotalRecords(), page.getList());
    }

}
