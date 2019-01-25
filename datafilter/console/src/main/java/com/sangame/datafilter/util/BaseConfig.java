package com.sangame.datafilter.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class BaseConfig {
	
	private Map<String, String> map = new HashMap<String, String>();
	
	private static final String CONFIG_FILE_NAME = "config.xml";
	
	InputStream inputStream;
	
	public BaseConfig() {
		this(CONFIG_FILE_NAME);
	}

	public BaseConfig(String configFilePath) {
		super();
		inputStream = getClass().getResourceAsStream("/" + configFilePath);
	}

	private synchronized void load() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = dbf.newDocumentBuilder(); 
		Document document = db.parse( inputStream );
		NodeList projectNodes = document.getChildNodes().item(0).getChildNodes();
		
		for(int i = 0; i < projectNodes.getLength(); i++) {
			Node project = projectNodes.item(i);
			if(project.getNodeType() == Node.ELEMENT_NODE) {
				for(int j = 0; j < project.getChildNodes().getLength(); j++) {
					Node n = project.getChildNodes().item(j);
					if(n.getNodeType() == Node.ELEMENT_NODE) {
						String key = n.getAttributes().getNamedItem("key").getNodeValue();
						if(key.startsWith("special")){
							loadSpecial(n);
						} else {
							map.put(key, n.getTextContent());
						}
					}
				}
			}
		}
	}
	
	private String privateGetValue(String key) {
		if(map.isEmpty()) {
			try {
				load();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		return map.get(key);
	}
	
	protected abstract void loadSpecial(Node n);
	
	public String getValue(String key) {
		String str = privateGetValue(key);
		if(str == null) {
			return null;
		} else {
			return str.trim();
		}
	}
	
	public Long getLongValue(String key) {
		String v = getValue(key);
		if(StringUtils.isEmpty(v)) {
			return null;
		} else {
			return Long.parseLong(v);
		}
	}
	
	public Boolean getBooleanValue(String key) {
		String v = getValue(key);
		if(StringUtils.isEmpty(v)) {
			return null;
		} else {
			if("1".equals(v.trim())) {
				return true;
			} else if("true".equalsIgnoreCase(v)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public Integer getIntValue(String key) {
		String v = getValue(key);
		if(StringUtils.isEmpty(v)) {
			return null;
		} else {
			return Integer.parseInt(v);
		}
	}
	
	public Byte getByteValue(String key) {
		String v = getValue(key);
		if(StringUtils.isEmpty(v)) {
			return null;
		} else {
			return Byte.parseByte(v);
		}
	}
}
