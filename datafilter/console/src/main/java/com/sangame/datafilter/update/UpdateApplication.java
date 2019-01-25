package com.sangame.datafilter.update;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sangame.datafilter.common.persistence.mapper.SysVersionMapper;
import com.sangame.datafilter.config.CommonConfig;
import com.sangame.datafilter.util.SpringBeanManager;



/**
 * 升级程序
 * */
public class UpdateApplication {
	
	private final static Logger LOG = LoggerFactory.getLogger(UpdateApplication.class);
	
	private String webRootPath;		// web跟路径
	private String newestVersion;	// 最新版本
	private String currentVersion;	// 当前版本
	
	private SqlSession session;
	private CommonConfig config;
	private SysVersionMapper mapper;
	private SqlSessionFactory sessionFactory;
	
	private List<String> sqlCommands = new ArrayList<String>();		// sql语句
	
	private static class SingletonHolder {
		static final UpdateApplication INSTANCE = new UpdateApplication();
	}
	
	private UpdateApplication(){
	}
	
	public static UpdateApplication getInstance(){
		return SingletonHolder.INSTANCE;
	}
	
	/**
	 * 执行升级
	 * */
	public void executeUpdate(String webRootPath){
		try {
			long _begin = System.currentTimeMillis();
			this.webRootPath = webRootPath;
			config = SpringBeanManager.getBean("commonConfig", CommonConfig.class);
			
			before();
			if(!checkVersion()){
				LOG.info("当前系统是最新版本，无需升级！");
				after();
				return;
			}
			
			readUpdateSQL();
			executeSQL();
			after();
			
			LOG.info("升级成功！耗时{}ms.", new Object[]{System.currentTimeMillis() - _begin});
		} catch (Exception e) {
			try{
				session.rollback();
			}catch(Exception e2){
				LOG.error("升级失败，程序自动关闭............", e);
				System.exit(0);
			}
			after();
			LOG.error("升级失败，程序自动关闭............", e);
			System.exit(0);
		}
	}
	
	/**
	 * 检测版本号
	 * */
	private boolean checkVersion() throws Exception {
		newestVersion = config.getVersion();
		
		if(StringUtils.isBlank(newestVersion)){
			throw new IllegalArgumentException("newest version is missing.....");
		}
		
		try {
			// 可能是版本表不存在，创建版本表。
			currentVersion = mapper.findVersion();
		} catch (Exception e) {
			LOG.error("查询系统版本异常，异常信息：{}", e.getCause().getMessage());
			mapper.execute("CREATE TABLE sys_version (sys_version VARCHAR(50) DEFAULT NULL) ENGINE=INNODB DEFAULT CHARSET=utf8");
			currentVersion = "0.0.0";
			mapper.insert(currentVersion);
			currentVersion = mapper.findVersion();
		}
		
		if(StringUtils.isBlank(currentVersion)){
			throw new IllegalArgumentException("current version is missing.....");
		}
		
		if(currentVersion.equals(newestVersion))
			return false;
		
		LOG.info("检测版本号，数据库版本：{}，程序版本：{}", new Object[]{currentVersion,newestVersion});
		
		String[] newest = newestVersion.split("\\.");
		String[] current = currentVersion.split("\\.");
		int len = newest.length < current.length ? newest.length : current.length;
		for(int i = 0; i < len; i++){
			if(Integer.parseInt(newest[i]) > Integer.parseInt(current[i])){
				return true;
			} else if (Integer.parseInt(newest[i]) < Integer.parseInt(current[i])){
				return false;
			}
		}
		
		if(newest.length > current.length)
			return true;
		
		LOG.info("版本号异常，当前版本：{}，最新版本：{}", new Object[]{currentVersion,newestVersion});
		return false;
	}
	
	/**
	 * 读取升级文件
	 * */
	private void readUpdateSQL() throws Exception {
		String updateFile = config.getValue("update.sql.path");
		
		// 拿到升级文件的绝对路径
		String realPath = webRootPath + updateFile;
		File file = new File(realPath);
		if (!file.exists()) {
			throw new IllegalArgumentException("update file not exist!");
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
		boolean record = false;
		String temp = "",
			   sqlCommand = "";
		while ((temp = reader.readLine()) != null) {
			if(temp.startsWith("-- #")){
				String versionFlag = temp.replace("-- #", "");
				// 从当前版本号开始记录sql命令
				if(versionFlag.equals(currentVersion)){
					record = true;
				}
				// 到最新版本号处结束
				if(versionFlag.equals(newestVersion)){
					break;
				}
			}
			
			// 忽略空行及sql注释
			if(StringUtils.isBlank(temp) || temp.startsWith("--")){
				continue;
			}
			
			if(record){
				// 处理多行sql
				sqlCommand = sqlCommand + " " + temp;
				if(temp.endsWith(";")){
					sqlCommands.add(sqlCommand);
					sqlCommand = "";
				}
			}
		}
		reader.close();
	}
	
	/**
	 * 执行升级脚本，更新系统版本号
	 * */
	private void executeSQL() throws Exception {
		if(!sqlCommands.isEmpty()){
			for(String sql : sqlCommands){
				LOG.info("current executing sql command: {}", new Object[]{sql});
				mapper.execute(sql);
			}
		}
		
		LOG.info("update system version: {}", new Object[]{newestVersion});
		mapper.update(newestVersion);
		session.commit();
	}
	
	private void before(){
		sessionFactory = (SqlSessionFactory) SpringBeanManager.getBean("datafilterSessionFactory");
		session = sessionFactory.openSession();
		mapper = session.getMapper(SysVersionMapper.class);
	}
	
	private void after(){
		if(session != null) {
			session.commit();
			session.close();
		}
	}
}
