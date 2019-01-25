package com.sangame.datafilter.common.util;

import java.util.List;  

/** 
 * 封装分页信息 
 * @author Administrator 
 * 
 */  
public class PageUtil {  

	//默认每页10条
	private static int DEFAULT_PAGE_SIZE = 10;
    //结果集  
    private List<Object> list;  
    //查询记录数  
    private int totalRecords;  
    //每页多少条数据  
    private int pageSize;  
    //第几页  
    private int pageNo;
    
    private int startRow;
    private String orderBy;
    
    public PageUtil(Integer pageStart, Integer pageSize) {
    	if(pageStart == null || pageStart < 0){
			pageStart = 0;
		}
		this.startRow = pageStart;
    	if(pageSize==null || pageSize < 1) {
    		pageSize = DEFAULT_PAGE_SIZE;
    	}
    	this.pageSize = pageSize;
	}
    
/*    public PageUtil(int pageNo, Integer pageSize) {
    	if(pageSize==null || pageSize < 1) {
    		pageSize = DEFAULT_PAGE_SIZE;
    	}
    	this.pageSize = pageSize;
		if(pageNo<=0){
			this.pageNo =1;
			this.startRow = 0;
		} else {
			this.startRow = (pageNo-1)*pageSize;
		}
	}*/

	/** 
     * 总页数 
     * @return 
     */  
    public int getTotalPages() {  
        return (totalRecords + pageSize - 1) / pageSize;  
    }  
      
    /** 
     * 取得首页 
     * @return 
     */  
    public int getTopPageNo() {  
        return 1;  
    }  
      
    /** 
     * 上一页 
     * @return 
     */  
    public int getPreviousPageNo() {  
        if (pageNo <= 1) {  
            return 1;  
        }  
        return pageNo - 1;  
    }  
      
    /** 
     * 下一页 
     * @return 
     */  
    public int getNextPageNo() {  
        if (pageNo >= getBottomPageNo()) {  
            return getBottomPageNo();  
        }  
        return pageNo + 1;    
    }  
      
    /** 
     * 取得尾页 
     * @return 
     */  
    public int getBottomPageNo() {  
        return getTotalPages();  
    }  
      
    public List<Object> getList() {  
        return list;  
    }  
  
    public void setList(List<Object> list) {  
        this.list = list;  
    }  
  
    public int getTotalRecords() {  
        return totalRecords;  
    }  
  
    public void setTotalRecords(int totalRecords) {  
        this.totalRecords = totalRecords;  
    }  
  
    public int getPageSize() {  
        return pageSize;  
    }  
  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
  
    public int getPageNo() {  
        return pageNo;  
    }  
  
    public void setPageNo(int pageNo) {  
        this.pageNo = pageNo;  
    }

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}  
}  
