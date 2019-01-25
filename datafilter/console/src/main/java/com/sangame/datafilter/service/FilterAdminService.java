package com.sangame.datafilter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import com.sangame.datafilter.common.persistence.mapper.FilterAdminMapper;
import com.sangame.datafilter.common.persistence.model.FilterAdmin;
import com.sangame.datafilter.common.util.PageUtil;
import com.sangame.datafilter.dto.AdminDto;
import com.sangame.datafilter.util.GoogleAuthenticator;


@Service  
public class FilterAdminService {
	
//	@Autowired
//	private LdapTemplate ldapTemplate;
	@Autowired
    private FilterAdminMapper filterAdminMapper;
    
    public int insert(FilterAdmin filterAdmin) {
		return filterAdminMapper.insert(filterAdmin);
	}
    
    public int update(FilterAdmin filterAdmin) {
		return filterAdminMapper.update(filterAdmin);
	}
	
	public int delete(Long id) {
		return filterAdminMapper.delete(id);
	}

	public FilterAdmin getById(Long id) {
		return filterAdminMapper.getById(id);
	}
	
	public int countByParm(FilterAdmin filterAdmin) {
		Map<String, Object> parm = new HashMap<String, Object>();
		return filterAdminMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return filterAdminMapper.countByParm(parm);
	}
	
	/**
	*根据参数获取对象列表
	**/
	public List<FilterAdmin> getListByParm(Map<String, Object> parm) {
		return filterAdminMapper.getListByParm(parm, null);
	}

	public PageUtil getListByParm(Map<String, Object> parm, int pageStart, Integer pageSize) {
		PageUtil pageUtil = new PageUtil(pageStart, pageSize);
		int count = filterAdminMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if (count != 0) {
			List<Object> list = filterAdminMapper.getListByParm(parm, pageUtil);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	/**
	 * 根据name获取管理员
	 * @param name
	 * @return
	 */
	public FilterAdmin findAdminByName(String name) {
		if (StringUtils.isBlank(name)) return null;
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("adminName", name);
		List<FilterAdmin> list = filterAdminMapper.getListByParm(parm, null);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	/**
	 * 根据uid取得用户信息
	 * 
	 * @param uid
	 * @return
	 */
//	public AdminDto getUserByUid(String loginName) {
//		AndFilter andFilter = new AndFilter();
//		andFilter.and(new EqualsFilter("uid", loginName));
//		andFilter.and(new EqualsFilter("accountStatus", "active"));
//		List<?> list = ldapTemplate.search("", andFilter.encode(), new AttributesMapper() {
//			@Override
//			public Object mapFromAttributes(Attributes attrs) throws NamingException {
//				AdminDto admin = new AdminDto();
//				admin.setName(attrs.get("uid").get().toString());
//				admin.setRealName(attrs.get("cn").get().toString());
//				admin.setEmail(attrs.get("mail").get().toString());
//				if(attrs.get("sgAuth") != null && attrs.get("sgAuth").get() != null){
//					admin.setAuthKey(attrs.get("sgAuth").get().toString());
//				}
//				return admin;
//			}
//		});
//		if (list != null && !list.isEmpty()) {
//			return (AdminDto) list.get(0);
//		}
//		return null;
//	}
	
	/**
	 * 根据用户名和密码验证
	 * 
	 * @param userDn
	 * @param credentials
	 * @return
	 */
//	public boolean authenticate(String loginName, String credentials) {
//		AndFilter andFilter = new AndFilter();
//		andFilter.and(new EqualsFilter("uid", loginName));
//		andFilter.and(new EqualsFilter("accountStatus", "active"));
//		List<?> result = ldapTemplate.search(DistinguishedName.EMPTY_PATH, andFilter.encode(), new AbstractContextMapper() {
//			protected Object doMapFromContext(DirContextOperations ctx) {
//				return ctx.getNameInNamespace();
//			}
//		});
//		if (result.size() != 1)
//			return false;
//		String userDn = (String) result.get(0);
//		DirContext ctx = null;
//		try {
//			ctx = ldapTemplate.getContextSource().getContext(userDn, credentials);
//			return true;
//		} catch (Exception e) {
//			return false;
//		} finally {
//			if (ctx != null)
//				LdapUtils.closeContext(ctx);
//		}
//	}
	
	/**
	 * 谷歌认证
	 * @param admin
	 * @param authCode
	 * @return
	 */
	public boolean checkAuthCode(String secret, Long authCode) {
		if(authCode != null && StringUtils.isNotBlank(secret)) {
			// 获取授权码
	        GoogleAuthenticator ga = new GoogleAuthenticator();
	        ga.setWindowSize(1); //should give 5 * 30 seconds of grace...
	        return ga.check_code(secret, authCode, System.currentTimeMillis());
		}
		return false;
	}
}
