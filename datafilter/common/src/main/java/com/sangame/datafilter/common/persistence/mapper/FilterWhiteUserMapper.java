package com.sangame.datafilter.common.persistence.mapper;

import com.sangame.datafilter.common.persistence.model.FilterWhiteUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterWhiteUserMapper extends BaseMapper {

	/**
	 * 批量删除id
	 * @param ids
	 */
	public void batchDeleteUserById(List<Long> ids);
	
	/**
	 * 批量删除userId
	 * @param userIds
	 */
	public void batchDeleteUserByUserId(List<Long> userIds);

	/**
	 * 批量删除ip
	 * @param ips
	 */
	public void batchDeleteUserByIP(List<String> ips);


	/**
	 * 批量添加白名单用户
	 * @param users
	 * @return
	 */
//	public int batchInsertUser(List<FilterWhiteUser> users);

	/**
	 * 根据用户名或者ip判断白名单用户
	 * @param whiteUser
	 * @return
	 */
	public Integer isWhiteUserByUserIdOrIp(FilterWhiteUser whiteUser);
}
