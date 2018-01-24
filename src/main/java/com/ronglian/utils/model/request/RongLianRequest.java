/**
 * 
 */
package com.ronglian.utils.model.request;

import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018Äê1ÔÂ23ÈÕ
 */
public class RongLianRequest<T> {

	private String sign;
	private String tokenId;
	private String accessToken;
	private long timeStamp;
	private  T data;
	

	/**
	 * @param obj
	 */
	public RongLianRequest(T data) {
		this.data = data;
	}

	/**
	 * 
	 */
	public RongLianRequest() {
		super();
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	
	
}
