package com.sangame.datafilter.shiro;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.sangame.datafilter.common.persistence.model.FilterAdmin;
import com.sangame.datafilter.common.persistence.model.FilterPermission;
import com.sangame.datafilter.common.persistence.model.FilterRole;
import com.sangame.datafilter.service.FilterPermissionService;
import com.sangame.datafilter.service.FilterRolePermissionService;
import com.sangame.datafilter.service.FilterRoleService;
import com.sangame.datafilter.util.SpringBeanManager;
public class FilterShiro extends AuthorizingRealm{

	 /**  
     * 权限认证  
     */    
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//从数据库里查询用户信息
		//获取登录时输入的用户名    
        /*String loginName=(String) principalCollection.fromRealm(getName()).iterator().next();
        FilterAdminService filterAdminService = (FilterAdminService) SpringContextUtil.getBean("filterAdminService");*/
		//到数据库查是否有此对象    
        /*FilterAdmin user1=filterAdminService.findByName(loginName);*/
		
		//从subject里获取用户信息
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession(false);  
		FilterAdmin user =  (FilterAdmin) session.getAttribute("userInfo");
		Long roleId = user.getRoleId();
        if(user != null && roleId != null){  
        	FilterRolePermissionService rolePerService = (FilterRolePermissionService) SpringBeanManager.getBean("filterRolePermissionService", FilterRolePermissionService.class);
        	FilterRoleService roleService = (FilterRoleService) SpringBeanManager.getBean("filterRoleService", FilterRoleService.class);
        	FilterPermissionService permissionService = (FilterPermissionService) SpringBeanManager.getBean("filterPermissionService", FilterPermissionService.class);
        	//权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）    
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();    
            //添加用户的角色
            FilterRole role = roleService.getById(roleId);
            if (role == null || StringUtils.isBlank(role.getRoleKey())) return null;
            info.addRole(role.getRoleKey());
            //添加权限
            List<Long> perIdList = rolePerService.getPerIdListByRoleId(roleId);
            List<FilterPermission> perList = permissionService.getPerListByIds(perIdList);
            if (perList == null || perList.size() < 1) return null;
            for (FilterPermission per : perList) {
            	info.addStringPermission(per.getPermissionKey());
            }
            
            return info;    
        }    
        return null;    
	}

	 /**  
     * 登录认证;  
     */   
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		//UsernamePasswordToken对象用来存放提交的登录信息    
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;    
        //查出是否有此用户    
        /*UserService userService = (UserService) SpringContextUtil.getBean("userService");
        User user=userService.findByName(token.getUsername());*/    
        if (token != null) {    
            //若存在，将此用户存放到登录认证info中    
            return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());    
        }    
        return null;    
	}

}
