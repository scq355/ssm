package com.sangame.datafilter.common.persistence.mapper;

import com.sangame.datafilter.common.persistence.model.FilterBlackUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 黑名单
 * @author scq
 */
@Repository
public interface FilterBlackUserMapper extends BaseMapper {
	
	/**
	 * 根据ids获取黑名单用户列表
	 * @param ids
	 * @return
	 */
	public List<FilterBlackUser> getListByIds(List<Long> ids);
	
	/**
	 * 根据用户id判断用户是否在黑名单中
	 * @param userId
	 * @return
	 */
	public Long isBlackUserByUserId(@Param("userId") Long userId);

	/**
	 * 根据ip判断用户是否在黑名单中
	 * @param ip
	 * @return
	 */
	public Long isBlackUserByIP(@Param("ip") String ip);


	/**
	 * 获取当前时间已经到期的用户数
	 * @param nowTime
	 * @return
	 */
	public List<FilterBlackUser> getBlackUsersBeforeNowTime(@Param("nowTime") Date nowTime);
	
	
	/**
	 * 根据ids批量冻结
	 * @param ids
	 */
    public void batchBlockById(List<Long> ids);
	
	/**
	 * 根据ids批量解冻
	 * @param ids
	 */
    public void batchFreezeById(List<Long> ids);

	/**
	 * 获取黑名单ID
	 * @return
	 */
	public List<Long> getIdList();
}
