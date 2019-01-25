package com.qs.p2p.service.impl;

import com.qs.p2p.dao.DAO;
import com.qs.p2p.dao.SysLogDao;
import com.qs.p2p.model.SysLog;
import com.qs.p2p.service.AbstractBO;
import com.qs.p2p.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by scq on 2018-01-29 14:43:24
 */
@Service(value = "sysLogService")
public class SysLogServiceImpl extends AbstractBO<SysLog, Integer> implements SysLogService {
	@Autowired
	private SysLogDao sysLogDao;

	@Override
	protected DAO<SysLog, Integer> getDAO() {
		return sysLogDao;
	}
}
