/**
 * 
 */
package com.ronglian.utils;

/**
 * @author liyang
 * @createTime 2017��12��28��
 */
public class PageCountResult {

	  // ��Ӧҵ��״̬��0��ʾ�ɹ���Ӧ
    private Integer code;

    // ��Ӧ��Ϣ
    private String msg;

   // ��Ӧ�е�����
    private Object data;
    
    //ÿҳ������������
    private int pageSize;
    
    //��ǰҳ��
   private int pageNo;
   
   //������Ŀ
   private int totalNewsCount;
   
   

public int getTotalNewsCount() {
	return totalNewsCount;
}

public void setTotalNewsCount(int totalNewsCount) {
	this.totalNewsCount = totalNewsCount;
}

/**
	 * 
	 */
	public PageCountResult() {
		super();
	}

/**
	 * @param code
	 * @param msg
	 * @param data
	 * @param pageSize
	 * @param pageNo
	 */
	public PageCountResult(Integer code, String msg, Object data, int totalNewsCount,int pageSize, int pageNo) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.totalNewsCount = totalNewsCount;
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
public PageCountResult(Integer code, String msg, int pageSize, int pageNo) {
	super();
	this.code = code;
	this.msg = msg;
	this.pageSize = pageSize;
	this.pageNo = pageNo;
}

public static  PageCountResult build(int code,String msg,int totalNewsCount,int pageNo,int pageSize,Object data){
	  return new PageCountResult(code, msg, data, totalNewsCount, pageSize, pageNo);
  }

  public static PageCountResult error(int code,String msg,int pageNo,int pageSize){
	  return new PageCountResult(code, msg, pageSize, pageNo);
  }
}
