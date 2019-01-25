package com.sangame.datafilter.service;

import com.alibaba.fastjson.JSON;
import com.sangame.datafilter.common.persistence.mapper.FilterBlackUserMapper;
import com.sangame.datafilter.common.persistence.model.FilterBlackUser;
import com.sangame.datafilter.common.util.DateUtil;
import com.sangame.datafilter.common.util.PageUtil;
import com.sangame.datafilter.constant.ConsoleConstant.BlackStatus;
import com.sangame.datafilter.constant.ConsoleConstant.BlackWhiteWay;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 黑名单
 */
@Service
public class FilterBlackUserService {

    private static final Logger log = LoggerFactory.getLogger(FilterBlackUser.class);

    @Autowired
    private FilterBlackUserMapper filterBlackUserMapper;

    public int insert(FilterBlackUser filterBlackUser) {
        return filterBlackUserMapper.insert(filterBlackUser);
    }

    public int update(FilterBlackUser filterBlackUser) {
        return filterBlackUserMapper.update(filterBlackUser);
    }

    public int delete(Long id) {
        return filterBlackUserMapper.delete(id);
    }

    public FilterBlackUser getById(Long id) {
        return filterBlackUserMapper.getById(id);
    }

    public int countByParm(FilterBlackUser filterBlackUser) {
        Map<String, Object> parm = new HashMap<String, Object>();
        return filterBlackUserMapper.countByParm(parm);
    }

    public int countByParm(Map<String, Object> parm) {
        return filterBlackUserMapper.countByParm(parm);
    }

    /**
     * 根据参数获取对象列表
     **/
    public List<FilterBlackUser> getListByParm(Map<String, Object> parm) {
        return filterBlackUserMapper.getListByParm(parm, null);
    }
    public PageUtil getListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
        PageUtil pageUtil = new PageUtil(pageNo, pageSize);
        int count = filterBlackUserMapper.countByParm(parm);
        pageUtil.setTotalRecords(count);
        if (count != 0) {
            List<Object> list = filterBlackUserMapper.getListByParm(parm, pageUtil);
            pageUtil.setList(list);
        }
        return pageUtil;
    }
    
    /**
     * 根据ids获取黑名单列表
     * @param ids
     * @return
     */
    public List<FilterBlackUser> getListByIds(List<Long> ids) {
    	return filterBlackUserMapper.getListByIds(ids);
    }
    
    /**
     * 根据Ip,批量冻结（新增/冻结）
     * saveOrUpdate
     * @param users
     */
    public void batchFreezeBlackUserByIP(String users) {
        //数据结构转换
        List<FilterBlackUser> filterBlackUsers = JSON.parseArray(users,FilterBlackUser.class);
        for (FilterBlackUser user: filterBlackUsers) {      //验证是否存在？更新冻结:冻结插入
        	Long id = isBlackUserByIP(user.getIp());
        	user.setBlackWay(BlackWhiteWay.IP.getValue());
        	user.setBlackState(BlackStatus.BLOCK.getValue());
        	Integer blockDay = user.getFreezeTime();
        	if (blockDay != null && blockDay > 0) {
        		Date endTime = DateUtil.addDay(new Date(), blockDay);	//冻结时间
				user.setBlackEndTime(endTime);
        	}
            if (id == null) {
            	this.insert(user);
            } else {
            	user.setId(id);
            	this.update(user);
            }
        }
    }

    /**
     * 根据用户ID，批量冻结（新增/冻结）
     * saveOrUpdate
     * @param users
     */
    public void batchFreezeBlackUserByUserId(String users){
    	 //数据结构转换
        List<FilterBlackUser> filterBlackUsers = JSON.parseArray(users,FilterBlackUser.class);
        for (FilterBlackUser user: filterBlackUsers) {      //验证是否存在？更新冻结:冻结插入
        	Long id = isBlackUserByUserId(user.getUserId());
        	user.setBlackWay(BlackWhiteWay.USERID.getValue());
        	user.setBlackState(BlackStatus.BLOCK.getValue());
        	Integer blockDay = user.getFreezeTime();
        	if (blockDay != null && blockDay > 0) {
        		Date endTime = DateUtil.addDay(new Date(), blockDay);	//冻结时间
				user.setBlackEndTime(endTime);
        	}
            if (id == null) {
                this.insert(user);
            } else {
            	user.setId(id);
            	this.update(user);
            }
        }
    }

    /**
     * 获取黑名单ID列表
     * @return
     */
    public List<Long> getIdList() {
        return filterBlackUserMapper.getIdList();
    }

    /**
     * 根据ID判断是否为黑名单用户
     * @param id
     * @return
     */
    public boolean isBlackUserById(Long id) {
    	if (this.getById(id) == null)
    		return false;
    	else
    		return true;
    }


    /**
     * 根据用户ID判断是否为黑名单用户，如果是则返回黑名单ID，否则为空
     *
     * @param userId
     * @return
     */
    public Long isBlackUserByUserId(Long userId) {
        return filterBlackUserMapper.isBlackUserByUserId(userId);
    }

    /**
     * 根据IP判断是否为黑名单用户，如果是则返回黑名单ID，否则为空
     *
     * @param ip
     * @return
     */
    public Long isBlackUserByIP(String ip) {
        return filterBlackUserMapper.isBlackUserByIP(ip);
    }

    /**
     * 判断是否是黑名单用户
     * @param ip
     * @param userId
     * @return
     */
    public boolean isBlackUserByUserIdAndIp(Long userId, String ip) {
    	if (isBlackUserByUserId(userId) != null)
    		return true;
    	if (isBlackUserByIP(ip) != null)
    		return true;
    	return false;
    }
    
    /**
     * 根据ids,批量冻结黑名单
     * @param ids
     */
    public void batchBlockById(List<Long> ids) {
        filterBlackUserMapper.batchBlockById(ids);
    }
    
    /**
     * 根据ids,批量解冻黑名单
     * @param ids
     */
    public void batchFreezeById(List<Long> ids) {
        filterBlackUserMapper.batchFreezeById(ids);
    }
    
    /**
     * 根据ids，批量同步数据到redis-暂时数据可能不同步到redis
     * @param ids
     */
    public void redisBatchByIds(List<Long> ids, Integer blackStatus) {
    	Set<Long> userIdSet = new HashSet<Long>();
    	Set<String> IpSet = new HashSet<String>();
    	List<FilterBlackUser> blackList = this.getListByIds(ids);
    	if (blackList == null || blackList.size() < 1) return;
    	for (FilterBlackUser black : blackList) {
    		if (black.getBlackWay() == BlackWhiteWay.USERID.getValue() && black.getUserId() != null) {
    			userIdSet.add(black.getUserId());
    		} else if (black.getBlackWay() == BlackWhiteWay.IP.getValue() && StringUtils.isNotBlank(black.getIp())) {
    			IpSet.add(black.getIp());
    		}
    	}
    	//冻结
    	if (blackStatus == BlackStatus.BLOCK.getValue()) {
    	} else if (blackStatus == BlackStatus.UNBLOCK.getValue()) {	//解冻
    	}
    }

    public List<FilterBlackUser> getBlackUsersBeforeNowTime(Date nowTime) {

        return filterBlackUserMapper.getBlackUsersBeforeNowTime(new java.sql.Date(nowTime.getTime()));
    }
}
