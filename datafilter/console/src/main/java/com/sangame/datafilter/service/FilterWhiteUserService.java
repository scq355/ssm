package com.sangame.datafilter.service;

import com.sangame.datafilter.common.persistence.mapper.FilterWhiteUserMapper;
import com.sangame.datafilter.common.persistence.model.FilterWhiteUser;
import com.sangame.datafilter.common.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilterWhiteUserService {

    private static final Logger LOG = LoggerFactory.getLogger(FilterWhiteUserService.class);        //LOG

    @Autowired
    private FilterWhiteUserMapper filterWhiteUserMapper;

    public int insert(FilterWhiteUser filterWhiteUser) {
        return filterWhiteUserMapper.insert(filterWhiteUser);
    }

    public int update(FilterWhiteUser filterWhiteUser) {
        return filterWhiteUserMapper.update(filterWhiteUser);
    }

    public int delete(Long id) {
        return filterWhiteUserMapper.delete(id);
    }

    public FilterWhiteUser getById(Long id) {
        return filterWhiteUserMapper.getById(id);
    }

    public int countByParm(FilterWhiteUser filterWhiteUser) {
        Map<String, Object> parm = new HashMap<String, Object>();
        return filterWhiteUserMapper.countByParm(parm);
    }

    public int countByParm(Map<String, Object> parm) {
        return filterWhiteUserMapper.countByParm(parm);
    }

    /**
     * 根据参数获取对象列表
     **/
    public List<FilterWhiteUser> getListByParm(Map<String, Object> parm) {
        return filterWhiteUserMapper.getListByParm(parm, null);
    }

    public PageUtil getListByParm(Map<String, Object> parm, int pageNo, Integer pageSize) {
        PageUtil pageUtil = new PageUtil(pageNo, pageSize);
        int count = filterWhiteUserMapper.countByParm(parm);
        pageUtil.setTotalRecords(count);
        if (count != 0) {
            List<Object> list = filterWhiteUserMapper.getListByParm(parm, pageUtil);
            pageUtil.setList(list);
        }
        return pageUtil;
    }

    /**
     * 批量删除用户通过id
     * @param ids
     */
    public void batchDeleteUserById(List<Long> ids) {
        try {
            filterWhiteUserMapper.batchDeleteUserById(ids);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * 批量删除用户userId
     *
     * @param userIds
     */
    public void batchDeleteUserByUserId(List<Long> userIds) {
        try {
            filterWhiteUserMapper.batchDeleteUserByUserId(userIds);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * 批量删除用户ip
     *
     * @param ips
     */
    public void batchDeleteUserByIP(List<String> ips) {
        try {
            filterWhiteUserMapper.batchDeleteUserByIP(ips);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }


    /**
     * 批量添加
     *
     * @param users
     * @return
     */
    public void batchInsertUser(List<FilterWhiteUser> users) {
        for (FilterWhiteUser whiteUser: users) {
            if (isWhiteUserByUserIdOrIp(whiteUser)) {
                continue;
            } else {
                filterWhiteUserMapper.insert(whiteUser);
            }

        }
    }

    /**
     * 判断是否在白名单用户中
     *
     * @param whiteUser
     * @return
     */
    public boolean isWhiteUserByUserIdOrIp(FilterWhiteUser whiteUser) {
        return !(filterWhiteUserMapper.isWhiteUserByUserIdOrIp(whiteUser) == 0);
    }

}
