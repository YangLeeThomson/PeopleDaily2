/**
 * 
 */
package com.ronglian.utils;

/**
 * @author liyang
 * @createTime 2017年12月28日
 */
public class PageResult {

	  // 响应业务状态，0表示成功响应
    private Integer code;

    // 响应消息
    private String msg;

   // 响应中的数据
    private Object data;
    
    //每页返回数据条数
    private int pageSize;
    
    //当前页数
   private int pageNo;
   
   

/**
	 * 
	 */
	public PageResult() {
		super();
	}

/**
	 * @param code
	 * @param msg
	 * @param data
	 * @param pageSize
	 * @param pageNo
	 */
	public PageResult(Integer code, String msg, Object data, int pageSize, int pageNo) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}

public Integer getCode() {
	return code;
}

public void setCode(Integer code) {
	this.code = code;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

public Object getData() {
	return data;
}

public void setData(Object data) {
	this.data = data;
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
   
  /**
 * @param code
 * @param msg
 * @param pageSize
 * @param pageNo
 */
public PageResult(Integer code, String msg, int pageSize, int pageNo) {
	super();
	this.code = code;
	this.msg = msg;
	this.pageSize = pageSize;
	this.pageNo = pageNo;
}

public static  PageResult build(int code,String msg,int pageNo,int pageSize,Object data){
	  return new PageResult(code, msg, data, pageSize, pageNo);
  }

  public static PageResult error(int code,String msg,int pageNo,int pageSize){
	  return new PageResult(code, msg, pageSize, pageNo);
  }
}
