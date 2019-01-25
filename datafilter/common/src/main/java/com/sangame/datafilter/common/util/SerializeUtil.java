package com.sangame.datafilter.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**   
* @Description: 序列化工具类
* @author yeqingfeng
* @date 2017年4月17日        
*/
public class SerializeUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(SerializeUtil.class);
	
	public static byte[] serialize(Object value) {    
        if (value == null) {    
            throw new NullPointerException("Can't serialize null");    
        }    
        byte[] rv=null;    
        ByteArrayOutputStream bos = null;    
        ObjectOutputStream os = null;    
        try {    
            bos = new ByteArrayOutputStream();    
            os = new ObjectOutputStream(bos);    
            os.writeObject(value);    
            os.close();    
            bos.close();    
            rv = bos.toByteArray();    
        } catch (IOException e) {    
            throw new IllegalArgumentException("Non-serializable object", e);    
        } finally {    
            try {  
                 if(os!=null)os.close();  
                 if(bos!=null)bos.close();  
            }catch (Exception e2) {  
            	LOG.error("serialize error", e2);   
            }    
        }    
        return rv;    
    }    
  
    public static Object deserialize(byte[] in) {    
        Object rv=null;    
        ByteArrayInputStream bis = null;    
        ObjectInputStream is = null;    
        try {    
            if(in != null) {    
                bis=new ByteArrayInputStream(in);    
                is=new ObjectInputStream(bis);    
                rv=is.readObject();    
                is.close();    
                bis.close();    
            }    
        } catch (Exception e) {    
        	LOG.error("deserialize error:", e);
         }finally {    
             try {  
                 if(is!=null)is.close();  
                 if(bis!=null)bis.close();  
             } catch (Exception e2) {  
            	 LOG.error("deserialize error", e2);  
             }  
         }  
        return rv;    
    }
}
