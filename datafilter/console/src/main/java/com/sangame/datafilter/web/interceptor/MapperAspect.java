package com.sangame.datafilter.web.interceptor;

import com.sangame.datafilter.common.persistence.model.BaseModel;
import com.sangame.datafilter.util.ConsoleUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

/**   
* @Description: 执行mybatis的mapper前，设置创建人、创建时间、更改人、更新时间
* @author yeqingfeng
* @date 2017年4月27日        
*/
@Aspect
@Component
public class MapperAspect {
	
	private final static Logger Log = LoggerFactory.getLogger(MapperAspect.class);
	
	@SuppressWarnings("rawtypes")
	@Before("execution(public * com.sangame.datafilter.common.persistence.mapper.*.insert*(..)) or execution(public * com.sangame.datafilter.common.persistence.mapper.*.batchInsert*(..))")
    public void insertBefore(JoinPoint point) {
		//当前管理员名称
		String adminName = ConsoleUtil.getCurAdminName();
		Object[] args = point.getArgs();
		if (args == null || args.length == 0) return;
		for (Object object : args) {
			// 单个修改
			if (object instanceof BaseModel) {
				BaseModel baseModel = (BaseModel) object;
				this.addSet(baseModel, adminName);
			}
			// 批量
			if (object instanceof ParamMap) {
				ParamMap paramMap = (ParamMap) object;
				Object obj = paramMap.get("param1");
				if (obj instanceof Collection) {
					Collection list = (Collection) obj;
					Object[] objects = list.toArray();
					if (objects[0] instanceof BaseModel) {
						for(int i=0; i<objects.length; i++) {
							BaseModel baseModel = (BaseModel) objects[i];
							this.addSet(baseModel, adminName);
						}
					}
				}
			}
			//批量
			if (object instanceof Collection) {
				Collection list = (Collection) object;
				Object[] objects = list.toArray();
				if (objects[0] instanceof BaseModel) {
					for(int i=0; i<objects.length; i++) {
						BaseModel baseModel = (BaseModel) objects[i];
						this.addSet(baseModel, adminName);
					}
				}
			}
			
		}
    }
	
	@SuppressWarnings("rawtypes")
	@Before("execution(public * com.sangame.datafilter.common.persistence.mapper.*.update*(..))")
    public void updateBefore(JoinPoint point) {
		//当前管理员名称
		String adminName = ConsoleUtil.getCurAdminName();
		
		Object[] args = point.getArgs();
		if (args == null || args.length == 0) return;
		for (Object object : args) {
			// 单个修改
			if (object instanceof BaseModel) {
				BaseModel baseModel = (BaseModel) object;
				baseModel.setUpdatedAt(new Date());
				if (StringUtils.isBlank(baseModel.getModifier()))
					baseModel.setModifier(adminName);
			}
			// 批量修改
			if (object instanceof ParamMap) {
				ParamMap paramMap = (ParamMap) object;
				Object obj = paramMap.get("param1");
				if (obj instanceof Collection) {
					Collection list = (Collection) obj;
					Object[] objects = list.toArray();
					if (objects[0] instanceof BaseModel) {
						for(int i=0; i<objects.length; i++) {
							BaseModel baseModel = (BaseModel) objects[i];
							baseModel.setUpdatedAt(new Date());
							if (StringUtils.isBlank(baseModel.getModifier()))
								baseModel.setModifier(adminName);
						}
					}
				}
			}
		}
    }
	
	//设置新增
	private void addSet(BaseModel baseModel, String adminName) {
		//设置时间
		baseModel.setCreatedAt(new Date());
		baseModel.setUpdatedAt(new Date());
		String creator = baseModel.getCreator();
		String modifier = baseModel.getModifier();
		//如果已手动设置了创建人，则不再自动设置创建人
		if (StringUtils.isNotBlank(creator)) {
			//如果修改人为空，则把修改人等于创建人
			if (StringUtils.isBlank(modifier))
				baseModel.setModifier(creator);
		} else {
			if (StringUtils.isBlank(adminName)) return;
			//设置创建人和修改人
			baseModel.setCreator(adminName);
			baseModel.setModifier(adminName);
		}
	}
}
